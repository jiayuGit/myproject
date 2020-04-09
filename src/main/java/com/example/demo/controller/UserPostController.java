package com.example.demo.controller;

import com.example.demo.dto.BasicPageDto;
import com.example.demo.dto.UserPostInfoDto;
import com.example.demo.dto.UserPostPageDto;
import com.example.demo.entity.TPost;
import com.example.demo.entity.TRole;
import com.example.demo.model.Result;
import com.example.demo.service.UserPostService;
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
 * @date Created in 2020年04月09日 11:55
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping(path = "/post")
@Api(tags = "userPostAPI",description = "职位管理",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserPostController {
    @Autowired
    private UserPostService userPostService;
    @PostMapping(path = "/userPage",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询用户职位列表接口",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,response = Result.class)
    public Result userPage(@RequestBody UserPostPageDto dto){
        String data = userPostService.userPage(dto);
        return Result.ok();
    }
    @PostMapping(path = "/updateUser",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改用户职位接口",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,response = Result.class)
    public Result updateUser(@RequestBody UserPostInfoDto dto){
        String data = userPostService.updateUserPostinfo(dto);
        return Result.ok();
    }
    @PostMapping(path = "/list",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询系统职位接口",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,response = Result.class)
    public Result page(){
        List<TPost> data = userPostService.postList();
        return Result.ok();
    }
    @PostMapping(path = "/add",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "添加职位接口",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,response = Result.class)
    public Result add(@RequestBody TPost post){
        int i = userPostService.addPost(post);
        return Result.ok();
    }

}
