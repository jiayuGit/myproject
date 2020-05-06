package com.example.demo.controller;


import com.example.demo.dto.BasicPageDto;
import com.example.demo.dto.FlowInfoDto;
import com.example.demo.dto.FlowNodeDto;
import com.example.demo.dto.FlowPageDto;
import com.example.demo.model.Result;
import com.example.demo.service.FlowService;
import com.example.demo.util.AuthUtil;
import com.example.demo.util.Check;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/flow")
@Api(tags = "FlowAPI",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class FlowController {
    @Autowired
    private FlowService flowService;

    @PostMapping(path = "/create",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建流程接口",notes = "创建流程接口",response = Result.class,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result createFlow(@RequestBody FlowInfoDto dto){
//        String fid = AuthUtil.getAuthUserInfoVo().getFid();
        String fid = "aa";
        if (Check.NuNStr(fid)){
            return Result.fail("用户fid不能为空");
        }
        dto.setUserFid(fid);
        if (Check.NuNCollection(dto.getList())){
            return Result.fail("审批角色不能为空");
        }

        try {
            int i = flowService.createFlow(dto);
        } catch (Exception e) {
            log.error("创建流程失败e={} params={} fid={}",e,dto,fid);
            return Result.fail("创建流程失败");
        }
        return Result.ok();
    }

    @PostMapping(path = "/selectPage",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询流程接口",notes = "查询流程接口",response = Result.class,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result selectPage(@RequestBody FlowPageDto dto){
        String fid = "1c165e4a-0842-402b-8fb9-663131b986db";//AuthUtil.getAuthUserInfoVo().getFid();
        if (Check.NuNStr(fid)){
            return Result.fail("用户fid不能为空");
        }
        dto.setUserFid(fid);
        try {
            PageResult result = flowService.selectFlow(dto);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("查询流程失败e={} params={} fid={}",e,dto,fid);
            return Result.fail("查询流程失败");
        }

    }
    @PostMapping(path = "/nodePage",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询角色审批的节点列表接口",notes = "查询角色审批的节点列表接口",response = Result.class,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result nodePage(@RequestBody BasicPageDto dto){
        String fid = "aa";//AuthUtil.getAuthUserInfoVo().getFid();
        List<String> list = new ArrayList<>(Arrays.asList("b9908d93-4e08-412b-a131-037f5d06426d"));
        if (Check.NuNCollection(list)){
            return Result.fail("您没有任何权限");
        }
        try {
            PageResult result = flowService.selectFlowNode(dto,list);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("查询角色审批的节点列表接口e={} params={} fid={}",e,dto,fid);
            return Result.fail("查询角色审批的节点列表接口失败");
        }

    }

    @PostMapping(path = "/delete",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "删除流程接口",notes = "删除流程接口",response = Result.class,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result delete(@RequestBody FlowPageDto dto){
        //AuthUtil.getAuthUserInfoVo().getFid();
        if (Check.NuNStr(dto.getFid())){
            return Result.fail("用户fid不能为空");
        }
        try {
            int i = flowService.deleteFlow(dto.getFid());
            return Result.ok();
        } catch (Exception e) {
            log.error("删除流程失败e={} params={} fid={}",e,dto.getFid());
            return Result.fail("删除流程失败");
        }

    }

    @PostMapping(path = "/nodeAudit",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "审核节点接口",notes = "审核节点接口",response = Result.class,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result nodeAudit(@RequestBody FlowNodeDto dto){
        //AuthUtil.getAuthUserInfoVo().getFid();
        if (Check.NuNStr(dto.getFid())){
            return Result.fail("用户fid不能为空");
        }
        try {
            int i = flowService.auditFlowNode(dto);
            return Result.ok();
        } catch (Exception e) {
            log.error("审核节点失败e={} params={} fid={}",e,dto.getFid());
            return Result.fail("审核节点失败");
        }

    }



}
