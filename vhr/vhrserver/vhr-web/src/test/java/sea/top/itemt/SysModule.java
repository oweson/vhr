package sea.top.itemt;


import lombok.Data;

import java.util.Date;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/8/5 21:34
 */
@Data
public class SysModule {
    private Integer id;

    private String title;

    private String intro;

    private String url;

    private Byte level;

    private String pcode;

    private String code;

    private Byte flag;

    private Date createdate;

    private Long iconcode;


}
