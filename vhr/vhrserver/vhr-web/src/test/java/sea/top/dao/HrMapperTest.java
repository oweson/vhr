package sea.top.dao;

import org.javaboy.vhr.mapper.HrMapper;
import org.javaboy.vhr.model.Hr;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author oweson
 * @date 2020/11/25 23:27
 */


public class HrMapperTest extends TinyintTest {

    @Autowired
    HrMapper hrMapper;

    @Test
    public void list() {
        Hr loadUserByUsername = hrMapper.loadUserByUsername("liuzongyuan");
        System.out.println(loadUserByUsername);
    }
}
