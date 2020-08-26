package sea.top.fastjson;

import lombok.Data;

import java.util.List;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/7/25 22:20
 */
@Data
public class Teacher {
    private int id;
    private String name;

    private List<Student> students;
    /**
     * 默认的构造方法必须不能省，不然不能解析
     */
    public Teacher() {

    }
    public Teacher(int id,String name) {
        this.id = id;
        this.name = name;
    }


}
