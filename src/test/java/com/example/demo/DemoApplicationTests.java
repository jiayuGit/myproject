package com.example.demo;


import com.example.demo.dto.UserRolePageDto;
import com.example.demo.service.UserRoleService;
import com.example.demo.vo.PageResult;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={DemoApplication.class})
class DemoApplicationTests {
    @Autowired
    private UserRoleService userRoleService;

    @Test
    public void text(){
        UserRolePageDto dto = new UserRolePageDto();
        dto.setPageSize(11);
        dto.setStartPage(1);
        PageResult pageResult = userRoleService.userPage(dto);
        System.out.println(pageResult);
    }

//    public static void main(String [] args) {
//        for (String s:"".split("#")
//             ) {
//            System.out.println(s+"="+s.length());
//        }
//        System.out.println("".split("#"));
//        Optional<TUser> optional = Optional.empty();
//        Optional<TUser> user = Optional.ofNullable(new TUser());
//        Object o = Optional.ofNullable(null).orElse(new TUser());
//        TUser tUser = null;
//        System.out.println(o);
//        Optional.ofNullable(null).ifPresent(v-> System.out.println(v));
//        TUser tUser1 = Optional.ofNullable(tUser).filter(v -> !Check.NuNStr(v.getEmaill())).orElse(TUser.builder().emaill("aaaa").build());
//
//        System.out.println(tUser1);
//    }

}
