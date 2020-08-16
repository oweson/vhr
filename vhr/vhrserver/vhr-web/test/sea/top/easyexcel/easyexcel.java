package sea.top.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import sea.top.entry.DemoData;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class easyexcel {
    @Before
    public void demo1() {
        ArrayList<DemoData> newArrayList = Lists.newArrayList();
        DemoData demoData = null;
        for (int i = 0; i < 10; i++) {
            demoData = new DemoData("hello"+i,new Date(),2.0);
            newArrayList.add(demoData);

        }
    }

    @Test
    public void simpleWrite() {
        ArrayList<DemoData> newArrayList = Lists.newArrayList();
        DemoData demoData = null;
        for (int i = 0; i < 1000000; i++) {
            demoData = new DemoData("hello"+i,new Date(),2.0);
            newArrayList.add(demoData);

        }
        // 写法1
        //String fileName = TestFileUtil.getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        String fileName = "D:\\" + "simpleWrite01" + System.currentTimeMillis() + ".xlsx";

        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(newArrayList);

        // 写法2
        fileName = "D:\\" + "simpleWrite02" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
        ExcelWriter write = excelWriter.write(newArrayList, writeSheet);
        System.out.println(write.writeContext());
        /// 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }
//    @Test
//    public void simpleRead(){
//        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
//        // 写法1：
//        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
//        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
//        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();
//
//        // 写法2：
//        fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
//        ExcelReader excelReader = EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).build();
//        ReadSheet readSheet = EasyExcel.readSheet(0).build();
//        excelReader.read(readSheet);
//        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
//        excelReader.finish();
//    }
}
