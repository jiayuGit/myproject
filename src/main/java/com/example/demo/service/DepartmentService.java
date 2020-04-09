package com.example.demo.service;

import com.example.demo.dao.*;
import com.example.demo.dto.DepartmentInfoDto;
import com.example.demo.dto.DepartmentPageDto;
import com.example.demo.entity.TDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * @date Created in 2020年04月09日 15:34
 * @since 1.0
 */
@Service
public class DepartmentService {
    @Autowired
    private TUserMapper userMapper;

    @Autowired
    private TUserPostMapper userPostMapper;
    @Autowired
    private TUserRoleMapper userRoleMapper;
    @Autowired
    private TDepartmentMapper departmentMapper;
    @Autowired
    private TDepartmentPostMapper departmentPostMapper;
    @Autowired
    private TPostMapper postMapper;
    @Autowired
    private TRoleMapper roleMapper;
    @Autowired
    private TUserMapper userMapper;

    public String userPage(DepartmentPageDto dto) {
        String res = null;
        return res;
    }

    public String updateDepartmentInfo(DepartmentInfoDto dto) {
        String res = null;
        return res;
    }

    public List<TDepartment> departmentList() {
        return null;
    }

    public int addDepartment(TDepartment department) {
        return 0;
    }
}
