package sea.top.other;

import lombok.*;
import lombok.experimental.Accessors;
import org.junit.Test;

import javax.validation.constraints.NotNull;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/10/1 7:32
 */
@Accessors(chain = true)
@Setter
@Getter
@RequiredArgsConstructor(staticName = "offName")
@ToString
@Builder
@AllArgsConstructor
public class LombokTest {
    private Integer age;
    @NotNull
    private String name;

    //    Object x() { return "abc"; }
//    String x() { return "123"; }
    public static void main(String[] args) {
        LombokTest lombokTest = new LombokTest().setAge(24).setName("ppx");
        LombokTest oweson = new LombokTest().offName().setName("oweson").setAge(23);
        System.out.println(lombokTest);
        System.out.println(oweson);

    }


}
