package sea.top.entry;

import com.github.liaochong.myexcel.core.annotation.ExcelColumn;
import lombok.Data;

import java.util.Date;
@Data
public class ArtCrowd {

    // index代表列索引，从0开始
    @ExcelColumn(index = 0)
    private String name;

    @ExcelColumn(index = 1)
    private String age;

    @ExcelColumn(index = 2,dateFormatPattern="yyyy-MM-dd")
    private Date birthday;
}
