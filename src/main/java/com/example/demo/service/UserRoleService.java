package com.example.demo.service;

import com.example.demo.dao.*;
import com.example.demo.dto.BasicPageDto;
import com.example.demo.dto.UserRolePageDto;
import com.example.demo.dto.UserRoleinfoDto;
import com.example.demo.entity.TRole;
import com.example.demo.entity.TUser;
import com.example.demo.entity.TUserRole;
import com.example.demo.vo.PageResult;
import com.example.demo.vo.UserRoleVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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
//
//    @Autowired
//    private TUserPostMapper userPostMapper;
    @Autowired
    private TUserRoleMapper userRoleMapper;
//    @Autowired
//    private TDepartmentMapper departmentMapper;
//    @Autowired
//    private TDepartmentPostMapper departmentPostMapper;
//    @Autowired
//    private TPostMapper postMapper;
    @Autowired
    private TRoleMapper roleMapper;
//    @Autowired
//    private TUserMapper userMapper;

    public PageResult userPage(UserRolePageDto dto) {
        Page<Object> objects = PageHelper.startPage(dto.getStartPage(), dto.getPageSize());
        List<TUser> tUsers =  userMapper.selectUserPage();
        List<UserRoleVo> list  = new ArrayList<>(tUsers.size());
        Map<String, UserRoleVo> map = tUsers.stream().map(v -> {
            UserRoleVo build = UserRoleVo.builder()
                    .name(v.getName())
                    .emaill(v.getEmaill())
                    .uuid(v.getUuid())
                    .lastModifyTime(v.getLastModifyTime())
                    .list(new ArrayList<>())
                    .build();
            list.add(build);
            return build;
        }).collect(Collectors.toMap(k -> k.getUuid(), v -> v, (k1, k2) -> k1));
        PageResult pageResult = PageResult.builder()
                .data(list)
                .total(objects.getTotal())
                .build();
        List<String> collect = tUsers.stream().map(TUser::getUuid).collect(Collectors.toList());
        List<TUserRole> userRoleList =  userRoleMapper.selectUserRoleIfList(collect);
        userRoleList.stream().forEach(v->map.get(v.getUserFid()).getList().add(v.getRoleName()));
        return pageResult;
    }

    public String updateUserRoleinfo(UserRoleinfoDto dot) {
        String res =null;
        return res;
    }


    public PageResult roleList(BasicPageDto dto) {
        Page<Object> objects = PageHelper.startPage(dto.getStartPage(), dto.getPageSize());
        List<TRole> res = roleMapper.selectRole();
        PageResult build = PageResult.builder()
                .data(res)
                .total(objects.getTotal())
                .build();
        return build;
    }
    @Transactional
    public int addRole(TRole role) throws Exception {
        int i = 0;
        role.setFid(UUID.randomUUID().toString());
        i = roleMapper.insertSelective(role);
        if (i==0){
            throw new Exception("addRole添加失败"+role.toString());
        }
        return i;
    }
}
