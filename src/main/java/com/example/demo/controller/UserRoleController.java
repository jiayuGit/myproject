package com.example.demo.controller;

import com.example.demo.dto.UserRoleinfoDto;
import com.example.demo.dto.UserRolePageDto;
import com.example.demo.entity.TRole;
import com.example.demo.entity.TUser;
import com.example.demo.model.Result;
import com.example.demo.service.UserRoleService;
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
            return Result.fail("服务器异常");
        }
    }
    @PostMapping(path = "/updateUser",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改用户角色接口",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,response = Result.class)
    public Result updateUserRoleinfo(@RequestBody UserRoleinfoDto dot){
        String data = userRoleService.updateUserRoleinfo(dot);
        return Result.ok();
    }
    @PostMapping(path = "/list",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询系统角色接口",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,response = Result.class)
    public Result roleList(){
        List<TRole> data = userRoleService.roleList();
        return Result.ok();
    }
    @PostMapping(path = "/add",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "添加系统角色接口",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,response = Result.class)
    public Result add(@RequestBody TRole role){
        int i = userRoleService.addRole(role);
        return Result.ok();
    }


}
