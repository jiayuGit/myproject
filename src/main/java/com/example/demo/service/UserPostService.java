package com.example.demo.service;

import com.example.demo.dao.*;
import com.example.demo.dto.UserPostInfoDto;
import com.example.demo.dto.UserPostPageDto;
import com.example.demo.entity.TPost;
import com.example.demo.entity.TUserPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * @date Created in 2020年04月09日 15:28
 * @since 1.0
 */
@Service
public class UserPostService {

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

    public String userPage(UserPostPageDto dto) {
        String res = null;
        return res;
    }

    public String updateUserPostinfo(UserPostInfoDto dto) {
        String res = null;
        return res;
    }

    public List<TPost> postList() {
        List<TPost> list = new ArrayList<>();

        return list;
    }

    public int addPost(TPost post) {
        int i = 0;
        return i;
    }
}
