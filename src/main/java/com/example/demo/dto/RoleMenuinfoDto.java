package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;
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
 * @date Created in 2020年04月09日 15:43
 * @since 1.0
 */
@Data
public class RoleMenuinfoDto implements Serializable {
    private static final long serialVersionUID = 7883421422800306223L;
    private String fid;
    private List<String> list;
}
