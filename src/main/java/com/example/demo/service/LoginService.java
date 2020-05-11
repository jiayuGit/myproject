package com.example.demo.service;

import com.example.demo.constant.Constants;
import com.example.demo.dao.TMenuMapper;
import com.example.demo.dao.TUserMapper;
import com.example.demo.dao.TUserRoleMapper;
import com.example.demo.dto.RegisterDto;
import com.example.demo.entity.TMenu;
import com.example.demo.entity.TRoleMenu;
import com.example.demo.entity.TUser;
import com.example.demo.model.AuthUserInfoVo;
import com.example.demo.model.Result;
import com.example.demo.util.AuthUtil;
import com.example.demo.util.Check;
import com.example.demo.vo.UserRolePo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LoginService {
    @Autowired
    private TUserMapper tUserMapper;
    
    @Resource(name = "redisTemplateSerializable")
    private RedisTemplate<String, Serializable> redisTemplate;

    @Autowired
    private TMenuMapper menuMapper;

    @Autowired
    private TUserRoleMapper userRoleMapper;
    @Transactional
    public Result addUser(RegisterDto registerDto) {
        String data =(String) redisTemplate.opsForValue().get(Constants.REGISTERKEY+registerDto.getEmaill());
        if (Check.NuNStr(data)||!data.equals(registerDto.getAuthCode())){
            return Result.fail("验证码不正确");
        }
        TUser tUser = new TUser();
        tUser.setUuid(UUID.randomUUID().toString());
        tUser.setEmaill(registerDto.getEmaill());
        tUser.setPwd(registerDto.getPwd());
        tUser.setIsDel(false);
        tUserMapper.insert(tUser);
        return Result.ok("添加成功");

    }


    public List<TUser> selectUserPage() {
        return tUserMapper.selectUserPage();
    }

    public Result login(TUser user) {
        List<TUser> tUsers = tUserMapper.selectByUser(user);
        if (Check.NuNCollection(tUsers)) {
            return Result.fail("账号不存在或密码错误");
        }
        List<String> userfid = new ArrayList<>(Arrays.asList(tUsers.get(0).getUuid()));
        List<UserRolePo> userRoleList =  userRoleMapper.selectUserRoleIfList(userfid);
        List<TRoleMenu> collect = userRoleList.stream().map(v -> TRoleMenu.builder().roleFid(v.getRoleFid()).build()).collect(Collectors.toList());
        ArrayList<TMenu> list = new ArrayList<>();
        collect.stream().forEach(v->{
            list.addAll(menuMapper.selectMenuInfo(v));
        });
        Map<String, TMenu> menuMap = list.stream().collect(Collectors.toMap(v -> v.getFid(), v -> v, (k1, k2) -> k1));

        AuthUserInfoVo userInfoVo = AuthUserInfoVo.builder()
                .accessToken(UUID.randomUUID().toString())
                .name(tUsers.get(0).getName())
                .emaill(tUsers.get(0).getEmaill())
                .rolePoList(userRoleList)
                .menuList(new ArrayList<>(menuMap.values()))
                .fid(tUsers.get(0).getUuid())
                .expiresIn(60 * 60 * 1000L+System.currentTimeMillis())
                .build();
        String token = AuthUtil.setAuthUserInfoVo(userInfoVo);
        return Result.ok(token);
    }
    public Result registerCheck(RegisterDto registerDto) {
        TUser user = new TUser();
        user.setEmaill(registerDto.getEmaill());
        List<TUser> tUsers = tUserMapper.selectByUser(user);
        if (!Check.NuNCollection(tUsers)) {
            return Result.fail("账户已注册");
        }
        return Result.ok();
    }
}
