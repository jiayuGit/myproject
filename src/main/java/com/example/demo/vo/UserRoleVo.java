package com.example.demo.vo;

import com.example.demo.entity.TRole;
import com.example.demo.entity.TUserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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
 * @date Created in 2020年04月20日 10:57
 * @since 1.0
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRoleVo {
    private String uuid;

    private String emaill;

    private String name;


    private Date lastModifyTime;

    private List<KeyValueVo> list;

    private Integer clockCount;
}
