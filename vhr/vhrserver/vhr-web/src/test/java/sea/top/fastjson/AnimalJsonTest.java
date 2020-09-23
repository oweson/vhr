package sea.top.fastjson;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Date;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/7/26 11:04
 */
public class AnimalJsonTest {
    @Test
    public  void  demo1(){
        Animal animal = new Animal();
        animal.setAge(24);
        String toJSONString = JSON.toJSONString(animal);
        System.out.println(toJSONString);
        // 1 {"age":24} 空的数据不参与序列化！不要返回null给前端
    }
    @Test
    public void demo2JsonPropertyTest(){
        Animal animal  = new Animal();
        animal.setAge(24);
        animal.setLoverName("那个她");
        animal.setAddress("guangzhou");
        animal.setCreateTime(new Date());
        String toJSONString = JSON.toJSONString(animal);
        System.out.println(toJSONString);
        // {"age":24,"createTime":"2019-07-26 11:24:20","name":"那个她"}TODO  有效果
    }
}
