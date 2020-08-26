package sea.top.jackson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/7/28 11:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friend {
    private String nickname;
    private int age;
}
