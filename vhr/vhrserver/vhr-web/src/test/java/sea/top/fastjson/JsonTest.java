package sea.top.fastjson;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/7/25 22:23
 */
public class JsonTest {
    @Test
    public void demo1() {
        /** 1 输出的json格式与我们的Student中定义的属性其实是不一致的，
         * 这个其实不影响我们生成以及后面的解析，因为他有严格的get和set方法定义。
         */
       /* Student student = new Student(0, "Aaron", 24);
        System.out.println(JSON.toJSONString(student));*/
        Student student = new Student(0, "Aaron", 24);
        System.out.println(JSON.toJSONString(student, true));
        // 标准的输出！！！
        System.out.println("----------------------------");
        List<Student> students = new ArrayList<Student>();
        for (int i = 0; i < 5; i++) {
            Student stu = new Student(i, "Student" + i, 18 + i);
            students.add(stu);
        }
        String toJSONString = JSON.toJSONString(students);
        System.out.println(toJSONString);
    }

    @Test
    public void difficultJson() {
        /** 2 json的嵌套*/

        List<Teacher> teaList = new ArrayList<Teacher>();
        long time = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            Teacher teacher = new Teacher(i, "Teacher " + i);
            List<Student> stus = new ArrayList<Student>();
            for (int j = 0; j < 4; j++) {
                Student s = new Student(j, "Student" + j, 18 + j);
                stus.add(s);
            }
            teacher.setStudents(stus);
            teaList.add(teacher);
            // teacher-集合--student--集合！
        }
        String jsonTeach = JSON.toJSONString(teaList);
        System.out.println("fastjson = " + jsonTeach);

    }

    @Test
    public void demo3() {
        // JSON是抽象类！
        JSONObject parse = (JSONObject) JSON.parse("{\"age\":24,\n" +
                "\t\"id\":0,\n" +
                "\t\"name\":\"Aaron\"}");
        Object age = parse.get("age");
        System.out.println(age);

    }

    @Test
    public void demo4() {

    }

    // 1 json字符串-简单对象型
    private static final String JSON_OBJ_STR = "{\"studentName\":\"lily\",\"studentAge\":12}";
    //2 json字符串-数组类型
    private static final String JSON_ARRAY_STR = "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";
    //3 复杂格式json字符串
    private static final String COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";

    /**
     * 1 json字符串-简单对象型与JSONObject之间的转换
     */
    public static void testJSONStrToJSONObject() {

        JSONObject jsonObject = JSON.parseObject(JSON_OBJ_STR);
        //JSONObject jsonObject1 = JSONObject.parseObject(JSON_OBJ_STR);
        // 因为JSONObject继承了JSON，所以这样也是可以的

        System.out.println(jsonObject.getString("studentName") + ":" + jsonObject.getInteger("studentAge"));

    }

    /**
     * 2 json字符串-数组类型与JSONArray之间的转换
     */
    public static void testJSONStrToJSONArray() {

        JSONArray jsonArray = JSON.parseArray(JSON_ARRAY_STR);
        //JSONArray jsonArray1 = JSONArray.parseArray(JSON_ARRAY_STR);
        // 因为JSONArray继承了JSON，所以这样也是可以的

        //遍历方式1
        int size = jsonArray.size();
        for (int i = 0; i < size; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            System.out.println(jsonObject.getString("studentName") + ":" + jsonObject.getInteger("studentAge"));
        }

        //遍历方式2
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            System.out.println(jsonObject.getString("studentName") + ":" + jsonObject.getInteger("studentAge"));
        }
    }

    /**
     * 3 复杂json格式字符串与JSONObject之间的转换
     */
    public static void testComplexJSONStrToJSONObject() {

        JSONObject jsonObject = JSON.parseObject(COMPLEX_JSON_STR);
        //JSONObject jsonObject1 = JSONObject.parseObject(COMPLEX_JSON_STR);
        // 因为JSONObject继承了JSON，所以这样也是可以的

        String teacherName = jsonObject.getString("teacherName");
        Integer teacherAge = jsonObject.getInteger("teacherAge");
        JSONObject course = jsonObject.getJSONObject("course");
        JSONArray students = jsonObject.getJSONArray("students");
        Object courseName = course.get("courseName");
        Object code = course.get("code");
        System.out.println(courseName+"  :"+code);
        for (int i = 0; i < students.size(); i++) {
            JSONObject jsonObject1 = (JSONObject) students.get(i);
            Object studentName = jsonObject1.get("studentName");
            Object studentAge = jsonObject1.get("studentAge");
            System.out.println(studentAge+"  ："+studentName);
        }


    }

    public static void main(String[] args) {
        testComplexJSONStrToJSONObject();


    }
}
