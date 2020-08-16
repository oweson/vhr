package sea.top.itemt;

import lombok.Data;

import java.util.List;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/8/5 21:35
 */
@Data
public class SysModuleVo extends SysModule {
    private List<SysModule> listModule;

}
