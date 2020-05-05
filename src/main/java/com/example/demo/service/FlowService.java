package com.example.demo.service;


import com.example.demo.dao.TFlowMapper;
import com.example.demo.dao.TFlowNodeMapper;
import com.example.demo.dto.BasicPageDto;
import com.example.demo.dto.FlowInfoDto;
import com.example.demo.dto.FlowPageDto;
import com.example.demo.entity.TFlow;
import com.example.demo.entity.TFlowNode;
import com.example.demo.enumerate.FlowEnum;
import com.example.demo.enumerate.FlowNodeEnum;
import com.example.demo.vo.FlowNodeVo;
import com.example.demo.vo.FlowVo;
import com.example.demo.vo.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FlowService {

    @Autowired
    private TFlowMapper flowMapper;

    @Autowired
    private TFlowNodeMapper flowNodeMapper;


    @Transactional
    public int createFlow(FlowInfoDto dto) throws Exception {
        int i = 0;
        String fid = UUID.randomUUID().toString();
        TFlow build = TFlow.builder()
                .flowName(dto.getFlowName())
                .userFid(dto.getUserFid())
                .remark(dto.getRemark())
                .state(FlowEnum.SPZ.getCode())
                .fid(fid)
                .build();
        String parentFid = null;
        String nowFid = UUID.randomUUID().toString();
        boolean boo = true;
        for (TFlowNode v : dto.getList()) {
            v.setParentFid(parentFid);
            v.setFlowFid(fid);
            v.setFid(nowFid);
            if (boo){
                v.setState(FlowNodeEnum.DSP.getCode());
                boo=false;
            }else {
                v.setState(FlowNodeEnum.WKS.getCode());
            }

            parentFid = nowFid;
            nowFid = UUID.randomUUID().toString();
            v.setNextFid(nowFid);
        }
        dto.getList().get(dto.getList().size() - 1).setNextFid(null);
        i += flowMapper.insertSelective(build);
        for (TFlowNode v : dto.getList()) {
            i += flowNodeMapper.insertSelective(v);
        }
        if (i != dto.getList().size() + 1) {
            throw new Exception("创建流程失败,回滚");
        }

        return i;
    }

    public PageResult selectFlow(FlowPageDto dto) {
        Page<Object> objects = PageHelper.startPage(dto.getStartPage(), dto.getPageSize());
        List<TFlow> tFlowNodes = flowMapper.selectByUserFid(dto.getUserFid());
        List<FlowVo> collect = tFlowNodes.stream().map(v -> {
            FlowVo flowVo = new FlowVo();
            BeanUtils.copyProperties(v, flowVo);
            flowVo.setStateName(FlowEnum.getNameByCode(v.getState()));
            return flowVo;
        }).collect(Collectors.toList());
        PageResult build = PageResult.builder()
                .total(objects.getTotal())
                .data(collect)
                .build();
        return build;
    }

    @Transactional
    public int deleteFlow(String fid) {
        int i = 0;

        i += flowMapper.updateByFlowFid(TFlow.builder()
                .fid(fid)
                .isDel(1)
                .build());
        i += flowNodeMapper.updateByFlowFid(TFlowNode.builder()
                .flowFid(fid)
                .isDel(1)
                .build());
        return i;
    }

    public PageResult selectFlowNode(BasicPageDto dto, List<String> list) {
        Page<Object> objects = PageHelper.startPage(dto.getStartPage(), dto.getPageSize());
        List<FlowNodeVo> res = flowNodeMapper.selectFlowNode(list);
        res.forEach(v->v.setStateName(FlowNodeEnum.getNameByCode(v.getState())));
        PageResult build = PageResult.builder()
                .total(objects.getTotal())
                .data(res)
                .build();
        return build;
    }
}
