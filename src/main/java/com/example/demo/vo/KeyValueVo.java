package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
 * @date Created in 2020年04月22日 19:56
 * @since 1.0
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class KeyValueVo {
    private String value2;
    private String value;
    private String text;
}
