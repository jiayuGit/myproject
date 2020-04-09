package com.example.demo.controller;

import com.example.demo.dto.DepartmentInfoDto;
import com.example.demo.dto.DepartmentPageDto;
import com.example.demo.entity.TDepartment;
import com.example.demo.entity.TPost;
import com.example.demo.model.Result;
import com.example.demo.service.DepartmentService;
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
 * @date Created in 2020年04月09日 15:17
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/department")
@Api(tags = "DepartmentAPI",description = "部门管理",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DepartmentCotroller {
    @Autowired
    private DepartmentService departmentService;
    @PostMapping(path = "/userPage",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询部门用户列表接口",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,response = Result.class)
    public Result userPage(@RequestBody DepartmentPageDto dto){
        String data = departmentService.userPage(dto);
        return Result.ok();
    }
    @PostMapping(path = "/updateUser",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改用户部门接口",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,response = Result.class)
    public Result updateUser(@RequestBody DepartmentInfoDto dto){
        String data = departmentService.updateDepartmentInfo(dto);
        return Result.ok();
    }
    @PostMapping(path = "/list",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询系统部门接口",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,response = Result.class)
    public Result page(){
        List<TDepartment> data = departmentService.departmentList();
        return Result.ok();
    }
    @PostMapping(path = "/add",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "添加系统部门接口",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,response = Result.class)
    public Result add(@RequestBody TDepartment department){
        int i = departmentService.addDepartment(department);
        return Result.ok();
    }

}
