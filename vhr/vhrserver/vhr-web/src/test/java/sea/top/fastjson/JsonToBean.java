package sea.top.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/7/25 23:06
 */
public class JsonToBean {

    /**
     * 1 JSON字符串转JAVA简单对象
     */
    public static void demo1() {
        String string = "{\n" +
                "\t\"age\":24,\n" +
                "\t\"id\":0,\n" +
                "\t\"name\":\"Aaron\"\n" +
                "}";

        Student student = JSON.parseObject(string,Student.class);
        System.out.println(student.toString()+"-----------------------------");
        Student student1 = JSON.parseObject(string, new TypeReference<Student>() {
        });
        System.out.println(student1.toString());


    }

    public static void main(String[] args) {
        demo1();

    }
}
