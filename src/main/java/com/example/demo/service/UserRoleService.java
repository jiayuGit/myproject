package com.example.demo.service;

import com.example.demo.dao.*;
import com.example.demo.dto.UserRolePageDto;
import com.example.demo.dto.UserRoleinfoDto;
import com.example.demo.entity.TRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
 * @date Created in 2020年04月09日 15:33
 * @since 1.0
 */
@Service
public class UserRoleService {
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

    public String userPage(UserRolePageDto dto) {
        String res =null;
        return res;
    }

    public String updateUserRoleinfo(UserRoleinfoDto dot) {
        String res =null;
        return res;
    }


    public List<TRole> roleList() {
        List<TRole> res = new ArrayList<>();
        return res;
    }
    @Transactional
    public int addRole(TRole role) {
        int i = 0;

        return i;
    }
}
