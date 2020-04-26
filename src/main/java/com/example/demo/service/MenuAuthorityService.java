package com.example.demo.service;

import com.example.demo.dao.TAuthorityMapper;
import com.example.demo.dao.TMenuAuthorMapper;
import com.example.demo.dao.TMenuMapper;
import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.util.DateUtilFormate;
import com.example.demo.vo.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
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
 * @date Created in 2020年04月26日 12:00
 * @since 1.0
 */
@Service
public class MenuAuthorityService {
    @Autowired
    private TMenuMapper tMenuMapper;

    @Autowired
    private TAuthorityMapper authorityMapper;

    @Autowired
    private TMenuAuthorMapper menuAuthorMapper;


    public PageResult authPage(MenuAuthPageDto dto) {
        Page<Object> objects = PageHelper.startPage(dto.getStartPage(), dto.getPageSize());
        List<TMenu> tMenus =  tMenuMapper.selectMenuPage();
        List<MenuAuthVo> list  = new ArrayList<>(tMenus.size());
        Map<String, MenuAuthVo> map = tMenus.stream().map(v -> {
            MenuAuthVo build = MenuAuthVo.builder()
                    .name(v.getMenuName())
                    .fid(v.getFid())
                    .lastModifyTime(v.getLastModifyTime())
                    .list(new ArrayList<>())
                    .build();
            list.add(build);
            return build;
        }).collect(Collectors.toMap(k -> k.getFid(), v -> v, (k1, k2) -> k1));
        PageResult pageResult = PageResult.builder()
                .data(list)
                .total(objects.getTotal())
                .build();
        List<String> collect = tMenus.stream().map(TMenu::getFid).collect(Collectors.toList());
        List<TMenuAuthorPo> authorityList =  menuAuthorMapper.selectMenuAuthList(collect);
        authorityList.stream().forEach(v->
                map.get(v.getMenuFid()).getList().add(
                        KeyValueVo.builder().value(String.valueOf(v.getFid())).text(v.getAuthorityName()).build()));
        return pageResult;
    }


    public PageResult authList(BasicPageDto dto) {
        Page<Object> objects = PageHelper.startPage(dto.getStartPage(), dto.getPageSize());
        List<TAuthority> res = authorityMapper.selectAuth();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtilFormate.DATEFORMAT_6);
        PageResult build = PageResult.builder()
                .data(
                        res.stream().map(v-> AuthVo.builder().fid(v.getFid()).name(v.getAuthorityName()).authorityPath(v.getAuthorityPath())
                        .lastModifyTime(simpleDateFormat.format(v.getLastModifyTime())).build()).collect(Collectors.toList())
                )
                .total(objects.getTotal())
                .build();
        return build;
    }

    public List<KeyValueVo> authKeyValeList() {
        List<TAuthority> res = authorityMapper.selectAuth();
        return res.stream().map(v->KeyValueVo.builder().value(v.getFid()).text(v.getAuthorityName()).build()).collect(Collectors.toList());
    }
    @Transactional
    public int addAuth(TAuthority  role) throws Exception {
        int i = 0;
        role.setFid(UUID.randomUUID().toString());
        i = authorityMapper.insertSelective(role);
        if (i==0){
            throw new Exception("addauth添加失败"+role.toString());
        }
        return i;
    }

    @Transactional
    public String updateMenuAuth(MenuAuthinfoDto dot) throws Exception {
        int i = menuAuthorMapper.updateDeleteByMenuFid(dot.getFid());
        if (dot.getList().isEmpty()){
            return "ok";
        }
        List<TMenuAuthor> collect = dot.getList().stream()
                .map(v -> TMenuAuthor
                        .builder()
                        .fid(UUID.randomUUID().toString())
                        .menuFid(dot.getFid())
                        .authFid(v)
                        .build())
                .collect(Collectors.toList());

        int i1 = menuAuthorMapper.insertList(collect);
        if(i1!=dot.getList().size()){
            throw new Exception("addMenuAuth添加失败"+dot+i);
        }
        return "ok";
    }

    @Transactional
    public int updata(TAuthority auth) throws Exception {
        int i = 0;
        i = authorityMapper.updateByFidSelective(auth);
        if (i==0){
            throw new Exception("authorityupdateByFidSelective失败"+auth.toString());
        }
        return i;
    }
    @Transactional
    public int delete(TAuthority auth) throws Exception {
        auth.setIsDel(1);
        int i = 0;
        i = authorityMapper.updateByFidSelective(auth);
        if (i==0){
            throw new Exception("addRole删除失败"+auth.toString());
        }
        return i;
    }
    @Transactional
    public int deleteMenu(TMenu menu) throws Exception {
        menu.setIsDel(1);
        int i = 0;
        i = tMenuMapper.updateByFidSelective(menu);
        if (i==0){
            throw new Exception("addRole删除失败"+menu.toString());
        }
        return i;
    }

    public List<KeyValueVo> all() {
        List<TMenu> tMenus =  tMenuMapper.selectMenuPage();
        return tMenus.stream().map(v->KeyValueVo.builder().text(v.getMenuName()).value(v.getFid()).build()).collect(Collectors.toList());
    }
    @Transactional
    public int addMenu(TMenu menu) throws Exception {
        int i = 0;
        menu.setFid(UUID.randomUUID().toString());
        i = tMenuMapper.insertSelective(menu);
        if (i==0){
            throw new Exception("menu添加失败"+menu.toString());
        }
        return i;
    }

    public int upMenu(TMenu tMenu) throws Exception {
        int i = 0;
        i = tMenuMapper.updateByFidSelective(tMenu);
        if (i==0){
            throw new Exception("authorityupdateByFidSelective失败"+tMenu.toString());
        }
        return i;
    }
}
