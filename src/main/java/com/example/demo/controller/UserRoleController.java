package com.example.demo.controller;

import com.example.demo.dto.BasicPageDto;
import com.example.demo.dto.UserRoleinfoDto;
import com.example.demo.dto.UserRolePageDto;
import com.example.demo.entity.TRole;
import com.example.demo.entity.TUser;
import com.example.demo.model.Result;
import com.example.demo.service.UserRoleService;
import com.example.demo.util.Check;
import com.example.demo.vo.KeyValueVo;
import com.example.demo.vo.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author dengjy
 * @version 1.0
 * @date Created in 2020年04月09日 11:17
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/role")
@Api(tags = "UserRoleAPI",description = "角色管理",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;
    @PostMapping(path = "/userPage",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询用户角色列表接口",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,response = Result.class)
    public Result userPage(@RequestBody UserRolePageDto dto){
        try {
            PageResult tUsers = userRoleService.userPage(dto);
            return Result.ok(tUsers);
        }catch (Exception e){
            log.error("查询用户角色列表接口e={}",e,dto);
            return Result.fail("服务器异常");
        }
    }
    @PostMapping(path = "/updateUser",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改用户角色接口",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,response = Result.class)
    public Result updateUserRoleinfo(@RequestBody UserRoleinfoDto dot){
        if (Check.NuNStr(dot.getUuid())){
            return Result.fail("修改用户fid不能为空");
        }
        try {
            String data = userRoleService.updateUserRoleinfo(dot);
            return Result.ok(data);
        } catch (Exception e) {
            log.error("修改用户角色错误e={} params={}",e,dot);
            return Result.fail("修改用户角色错误");
        }

    }
    @PostMapping(path = "/list",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询系统角色接口",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,response = Result.class)
    public Result roleList(@RequestBody BasicPageDto dto){
        try {
            PageResult pageResult = userRoleService.roleList(dto);
            return Result.ok(pageResult);
        }catch (Exception e){
            log.error("查询系统角色错误e={} params={}",e,dto);
            return Result.fail("查询系统角色错误");
        }
    }
    @PostMapping(path = "/listKey",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询系统角色接口",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,response = Result.class)
    public Result roleKey(){
        try {
            List<KeyValueVo> list = userRoleService.roleKeyValeList();
            return Result.ok(list);
        }catch (Exception e){
            log.error("查询系统角色错误e={} params={}",e);
            return Result.fail("查询系统角色错误");
        }
    }
    @PostMapping(path = "/add",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "添加系统角色接口",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,response = Result.class)
    public Result add(@RequestBody TRole role){
        try {
            userRoleService.addRole(role);
        }catch (Exception e){
            log.error("添加系统角色接口e={} params={}",e,role);
            Result.fail("添加失败");
        }

        return Result.ok();
    }

    @PostMapping(path = "/updata",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改系统角色接口",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,response = Result.class)
    public Result updata(@RequestBody TRole role){
        if (Check.NuNObj(role)||Check.NuNStr(role.getFid())){
            return Result.fail("fid不能为空");
        }
        try {
            userRoleService.updata(role);
        }catch (Exception e){
            log.error("修改系统角色接口e={} params={}",e,role);
            Result.fail("修改失败");
        }

        return Result.ok();
    }
    @PostMapping(path = "/delete",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改系统角色接口",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,response = Result.class)
    public Result delete(@RequestBody TRole role){
        if (Check.NuNObj(role)||Check.NuNStr(role.getFid())){
            return Result.fail("fid不能为空");
        }
        try {
            userRoleService.delete(role);
        }catch (Exception e){
            log.error("修改系统角色接口e={} params={}",e,role);
            Result.fail("修改失败");
        }

        return Result.ok();
    }

}
