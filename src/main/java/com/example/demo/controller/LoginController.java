package com.example.demo.controller;

import com.example.demo.dao.TMenuMapper;
import com.example.demo.dto.BasicPageDto;
import com.example.demo.dto.FlowPageDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.entity.TMenu;
import com.example.demo.entity.TUser;
import com.example.demo.model.AuthUserInfoVo;
import com.example.demo.model.Result;
import com.example.demo.service.LoginService;
import com.example.demo.util.AuthUtil;
import com.example.demo.util.Check;
import com.example.demo.vo.PageResult;
import com.example.demo.vo.UserInfoVo;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/login")
@Api(tags = "LoginAPI", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Resource(name = "redisTemplateSerializable")
    private RedisTemplate<String, Serializable> redisTemplate;

    @Autowired
    private FlowController flowController;

    @PostMapping(path = "/test")
    public String test() {
        stringRedisTemplate.opsForValue().set("aaaa", "1111");
        stringRedisTemplate.opsForValue().get("aaaa");
        return stringRedisTemplate.opsForValue().get("aaaa");
    }

    @PostMapping(path = "/userInfo",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "用户信息接口", notes = "用户信息接口", response = Result.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result userInfo() {

        try {

            AuthUserInfoVo authUserInfoVo = AuthUtil.getAuthUserInfoVo();
            if (Check.NuNObj(authUserInfoVo)) {
                return Result.fail("请重新登录");
            }
            UserInfoVo userInfoVo = new UserInfoVo();
            BeanUtils.copyProperties(authUserInfoVo,userInfoVo);
            Result result = flowController.selectPage(new FlowPageDto());
            PageResult pageResult = (PageResult)result.getData();
            userInfoVo.setMyPending(pageResult.getTotal());
            Result result1 = flowController.nodePage(new FlowPageDto());
            PageResult pageResult1 = (PageResult)result1.getData();
            userInfoVo.setOtherPending(pageResult1.getTotal());
            return Result.ok(userInfoVo);
        } catch (Exception e) {
            log.error("查询菜单失败e={}", e);
            return Result.fail("查询菜单失败");
        }

    }
    @PostMapping(path = "/clockIn",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "员工签到接口", notes = "员工签到接口", response = Result.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result clockIn() {

        try {

            AuthUserInfoVo authUserInfoVo = AuthUtil.getAuthUserInfoVo();
            if (Check.NuNObj(authUserInfoVo)) {
                return Result.fail("请重新登录");
            }
            loginService.clockIn(authUserInfoVo)
            ;
            return Result.ok();
        } catch (Exception e) {
            log.error("员工签到失败e={}", e);
            return Result.fail("员工签到失败");
        }

    }
    @PostMapping(path = "/clockOut",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "员工签退接口", notes = "员工签退接口", response = Result.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result clockOut() {

        try {

            AuthUserInfoVo authUserInfoVo = AuthUtil.getAuthUserInfoVo();
            if (Check.NuNObj(authUserInfoVo)) {
                return Result.fail("请重新登录");
            }
            loginService.clockOut(authUserInfoVo);
            return Result.ok();
        } catch (Exception e) {
            log.error("员工签退失败e={}", e);
            return Result.fail("员工签退失败");
        }

    }

    @PostMapping(path = "/menu",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "用户菜单接口", notes = "用户菜单接口", response = Result.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result menu() {

        try {
            AuthUserInfoVo authUserInfoVo = AuthUtil.getAuthUserInfoVo();
            if (Check.NuNObj(authUserInfoVo)) {
                return Result.fail("请重新登录");
            }

            List<TMenu> menuList = authUserInfoVo.getMenuList();
            if (Check.NuNCollection(menuList)) {
                return Result.fail("您没有任何权限");
            }
            return Result.ok(menuList);
        } catch (Exception e) {
            log.error("查询菜单失败e={}", e);
            return Result.fail("查询菜单失败");
        }

    }

    @PostMapping(path = "/out",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "退出登录接口", notes = "退出登录接口", response = Result.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result outlogin() {

        try {
            AuthUserInfoVo authUserInfoVo = AuthUtil.getAuthUserInfoVo();
            if (Check.NuNObj(authUserInfoVo)) {
                return Result.ok("已经退出登录");
            }
            AuthUtil.outAuthUserInfoVo();
            return Result.ok();
        } catch (Exception e) {
            log.error("退出登录失败e={}", e);
            return Result.fail("退出登录失败");
        }

    }

    @PostMapping(path = "/logoing",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "登录接口", notes = "登录接口", response = Result.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result logoing(@RequestBody TUser user) {

        if (Check.NuNObj(user) || Check.NuNStr(user.getEmaill()) || Check.NuNStr(user.getPwd())) {
            return Result.fail("账号密码不能为空");
        }

        return Result.ok(loginService.login(user));
    }

    @PostMapping(path = "/registerCheck",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "注册判断是否可用账户接口", notes = "注册判断是否可用账户接口", response = Result.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result registerCheck(@RequestBody RegisterDto registerDto) {
        try {
            return loginService.registerCheck(registerDto);
        } catch (Exception e) {
            log.error("注册验证失败入参{} e={}", registerDto, e);
            return Result.fail("注册验证失败");
        }
    }

    @PostMapping(path = "/registerUser",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "注册接口", notes = "注册接口", response = Result.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result registerUser(@RequestBody RegisterDto registerDto) {
        try {
            return loginService.addUser(registerDto);
        } catch (Exception e) {
            log.error("用户注册失败入参{} e={}", registerDto, e);
            return Result.fail("注册失败");
        }
    }

    @PostMapping(path = "/selectUserPage",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result selectUserPage(@RequestBody BasicPageDto pageDto) {
        PageHelper.startPage(pageDto.getStartPage(), pageDto.getPageSize());
        String s = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(s, pageDto, 1000, TimeUnit.SECONDS);
        BasicPageDto s1 = (BasicPageDto) redisTemplate.opsForValue().get(s);
        log.info(s1 + " " + s1);
        return Result.ok(loginService.selectUserPage());
    }

}
