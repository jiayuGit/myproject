package com.example.demo.vo;

import com.example.demo.entity.TRoleMenu;
import lombok.Data;

import java.util.Date;

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
 * @date Created in 2020年04月23日 11:13
 * @since 1.0
 */
@Data
public class RoleMenuPo extends TRoleMenu {
    private String menuName;
}
