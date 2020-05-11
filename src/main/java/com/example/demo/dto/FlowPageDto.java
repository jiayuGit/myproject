package com.example.demo.dto;

import lombok.Data;

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
 * @date Created in 2020年04月09日 15:37
 * @since 1.0
 */
@Data
public class FlowPageDto extends BasicPageDto{
    private static final long serialVersionUID = 5306550076423303253L;
    private String userFid;
    private String fid;
}
