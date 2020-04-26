package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.TAuthority;
import com.example.demo.entity.TMenu;
import com.example.demo.entity.TRole;
import com.example.demo.model.Result;
import com.example.demo.service.MenuAuthorityService;
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
 * @date Created in 2020年04月26日 11:58
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/menu")
@Api(tags = "MenuAuthorityAPI",description = "菜单管理",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MenuAuthorityController {
    @Autowired
    private MenuAuthorityService menuAuthorityService;
    @PostMapping(path = "/authPage",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询菜单权限列表接口",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,response = Result.class)
    public Result userPage(@RequestBody MenuAuthPageDto dto){
        try {
            PageResult tUsers = menuAuthorityService.authPage(dto);
            return Result.ok(tUsers);
        }catch (Exception e){
            log.error("查询菜单权限列表接口e={}",e,dto);
            return Result.fail("服务器异常");
        }
    }
    @PostMapping(path = "/all",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询菜单接口",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,response = Result.class)
    public Result all(){
        try {
            List<KeyValueVo> menus = menuAuthorityService.all();
            return Result.ok(menus);
        }catch (Exception e){
            log.error("查询菜单接口e={}",e);
            return Result.fail("服务器异常");
        }
    }

    @PostMapping(path = "/updateAuth",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改菜单权限接口",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,response = Result.class)
    public Result updateMenuAuth(@RequestBody MenuAuthinfoDto dot){
        if (Check.NuNStr(dot.getFid())){
            return Result.fail("修改菜单fid不能为空");
        }
        try {
            String data = menuAuthorityService.updateMenuAuth(dot);
            return Result.ok(data);
        } catch (Exception e) {
            log.error("修改菜单权限接口e={} params={}",e,dot);
            return Result.fail("修改菜单权限错误");
        }

    }
    @PostMapping(path = "/list",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询权限接口",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,response = Result.class)
    public Result roleList(@RequestBody BasicPageDto dto){
        try {
            PageResult pageResult = menuAuthorityService.authList(dto);
            return Result.ok(pageResult);
        }catch (Exception e){
            log.error("查询权限接口e={} params={}",e,dto);
            return Result.fail("查询权限接口错误");
        }
    }
    @PostMapping(path = "/listKey",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询所有系统权限接口",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,response = Result.class)
    public Result roleKey(){
        try {
            List<KeyValueVo> list = menuAuthorityService.authKeyValeList();
            return Result.ok(list);
        }catch (Exception e){
            log.error("查询系统权限接口e={} params={}",e);
            return Result.fail("查询系统权限错误");
        }
    }
    @PostMapping(path = "/add",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "添加系统权限接口",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,response = Result.class)
    public Result add(@RequestBody TAuthority auth){
        try {
            menuAuthorityService.addAuth(auth);
        }catch (Exception e){
            log.error("添加系统权限接口e={} params={}",e,auth);
            Result.fail("添加失败");
        }

        return Result.ok();
    }
    @PostMapping(path = "/addMenu",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "添加菜单接口",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,response = Result.class)
    public Result addMenu(@RequestBody TMenu menu){
        try {
            menuAuthorityService.addMenu(menu);
        }catch (Exception e){
            log.error("添加系统权限接口e={} params={}",e,menu);
            Result.fail("添加失败");
        }

        return Result.ok();
    }

    @PostMapping(path = "/updata",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改系统权限接口",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,response = Result.class)
    public Result updata(@RequestBody TAuthority auth){
        if (Check.NuNObj(auth)||Check.NuNStr(auth.getFid())){
            return Result.fail("fid不能为空");
        }
        try {
            menuAuthorityService.updata(auth);
        }catch (Exception e){
            log.error("修改系统角色接口e={} params={}",e,auth);
            Result.fail("修改失败");
        }

        return Result.ok();
    }
    @PostMapping(path = "/delete",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改系统角色接口",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,response = Result.class)
    public Result delete(@RequestBody TAuthority auth){
        if (Check.NuNObj(auth)||Check.NuNStr(auth.getFid())){
            return Result.fail("fid不能为空");
        }
        try {
            menuAuthorityService.delete(auth);
        }catch (Exception e){
            log.error("修改系统角色接口e={} params={}",e,auth);
            Result.fail("修改失败");
        }

        return Result.ok();
    }


}
