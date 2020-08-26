package sea.top.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/7/26 10:57
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Animal {
    // TODO 这里是fastjson!!!
    private Integer age;
    private Integer height;
    private String name;
    //@JsonProperty("lover")  fastjson的坑！！！
    @JSONField(name = "name")
    private String loverName;
    // @JsonIgnore 如果注解失效，可能是因为你使用的是fastJson，
    // 可以尝试使用该注解@JSONField(serialize = false)，使用方法一样
    @JSONField(serialize = false)
    private String address;
    //    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    // 接受的数据返回！！！

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoverName() {
        return loverName;
    }

    public void setLoverName(String loverName) {
        this.loverName = loverName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
