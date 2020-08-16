package sea.top.utils;//package sea.top.utils;
//
//import com.github.liaochong.myexcel.core.DefaultExcelReader;
//import sea.top.entry.ArtCrowd;
//
//import java.net.URISyntaxException;
//import java.net.URL;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.List;
//
//public class Demo1ExcelTest {
//    public void demo1() throws URISyntaxException {
//        URL htmlToExcelEampleURL = this.getClass().getResource("/templates/read_example.xlsx");
//        Path path = Paths.get(htmlToExcelEampleURL.toURI());
//
//// 方式一：全部读取后处理
//        List<ArtCrowd> result = DefaultExcelReader.of(ArtCrowd.class)
//                .sheet(0) // 0代表第一个，如果为0，可省略该操作，也可sheet("名称")读取
//                .rowFilter(row -> row.getRowNum() > 0) // 如无需过滤，可省略该操作，0代表第一行
//                .beanFilter(ArtCrowd::isDance) // bean过滤
//                .read(path.toFile());// 可接收inputStream
//    }
//    public static void main(String[] args) throws URISyntaxException {
//
//    }
//}
