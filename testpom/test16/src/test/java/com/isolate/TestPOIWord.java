package com.isolate;

import com.isolate.infrastructure.general.toolkit.XWPFUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.security.auth.Subject;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * @description: some desc
 * @author: isolate
 * @email: 15071340963@163.com
 * @date: 2023/2/13 14:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPOIWord {

    @Test
    public void testParseWord() throws IOException {
        InputStream in = new FileInputStream("D:\\Document\\我的博客\\Java解析word,获取文档中图片位置\\示例.docx");
        XWPFDocument xwpfDocument = new XWPFDocument(in);
        List<XWPFParagraph> paragraphList = xwpfDocument.getParagraphs();
        System.out.println("图片的索引\t|图片名称\t|图片上一段文字的内容\t");
        System.out.println("------------------------------------------");
        for(int i = 0;i < paragraphList.size();i++){
            List<String> imageBundleList = XWPFUtils.readImageInParagraph(paragraphList.get(i));
            if(CollectionUtils.isNotEmpty(imageBundleList)){
                for(String pictureId:imageBundleList){
                    XWPFPictureData pictureData = xwpfDocument.getPictureDataByID(pictureId);
                    String imageName = pictureData.getFileName();
                    String lastParagraphText = paragraphList.get(i-1).getParagraphText();
                    System.out.println(pictureId +"\t|" + imageName + "\t|" + lastParagraphText);
                }
            }
        }
    }

    // 读取doc文档中表格数据示例
    /**
     * 读取文档中表格
     * @param
     */
    @Test
    public void testWord(){

        System.err.println("开始读取");
        String filePath = "C:\\Users\\Ric\\Downloads\\嘉兴每日网上舆情信息表2023年2月13日9时.docx";
        System.err.println("结束读取");

        List<Subject> resultList = new ArrayList<>();

        try{
            FileInputStream in = new FileInputStream(filePath);//载入文档
            // 处理docx格式 即office2007以后版本
            if(filePath.toLowerCase().endsWith("docx")){
                //word 2007 图片不会被读取， 表格中的数据会被放在字符串的最后
                XWPFDocument xwpf = new XWPFDocument(in);//得到word文档的信息
                Iterator<XWPFTable> it = xwpf.getTablesIterator();//得到word中的表格
                List<XWPFParagraph> paragraphList = xwpf.getParagraphs();

                // 设置需要读取的表格  set是设置需要读取的第几个表格，total是文件中表格的总数
                int set = 1, total = 2;
                int num = set;
                // 过滤前面不需要的表格
              /*  for (int i = 0; i < set-1; i++) {
                    it.hasNext();
                    it.next();
                }*/
                while(it.hasNext()){
                    XWPFTable table = it.next();
                    System.out.println("这是第" + num + "个表的数据");
                    List<XWPFTableRow> rows = table.getRows();
                    //读取每一行数据
                    for (int i = 0; i < rows.size(); i++) {
                        XWPFTableRow  row = rows.get(i);
                        System.err.println("第"+i+"行：");
                        //读取每一列数据
                        List<XWPFTableCell> cells = row.getTableCells();
                        for (int j = 0; j < cells.size(); j++) {
                            XWPFTableCell cell = cells.get(j);
                            List<XWPFParagraph> paragraphs = cell.getParagraphs();
                            for (XWPFParagraph paragraph : paragraphs) {
                                List<String> imageBundleList = XWPFUtils.readImageInParagraph(paragraph);
                                if(CollectionUtils.isNotEmpty(imageBundleList)){
                                    for(String pictureId:imageBundleList){
                                        XWPFPictureData picture = paragraph.getDocument().getPictureDataByID(pictureId);
                                        byte[] bytev = picture.getData();
                                        // 输出图片到磁盘
                                        FileOutputStream out = new FileOutputStream(
                                                "C:\\test\\picture\\" + UUID.randomUUID() + picture.getFileName());
                                        out.write(bytev);
                                        out.close();
                                        System.out.println("图片所属位置：第" + i + "行，第" + j + "列");
//                                        XWPFPictureData pictureData = xwpf.getPictureDataByID(pictureId);
//                                        String imageName = pictureData.getFileName();
//                                        String lastParagraphText = paragraphList.get(i-1).getParagraphText();
//                                        System.out.println(pictureId +"\t|" + imageName + "\t|" + lastParagraphText);
                                    }
                                }


                            }
                            //输出当前的单元格的数据
                            System.out.print("第"+j+"列："+cell.getText() + "\t");
                        }
                        System.out.println();
                    }
                    // 过滤多余的表格
                 /*   while (num < total) {
                        it.hasNext();
                        it.next();
                        num += 1;
                    }*/
                }
            }else{
                // 处理doc格式 即office2003版本
                POIFSFileSystem pfs = new POIFSFileSystem(in);
                HWPFDocument hwpf = new HWPFDocument(pfs);
                Range range = hwpf.getRange();//得到文档的读取范围
                TableIterator it = new TableIterator(range);
                // 迭代文档中的表格
                // 如果有多个表格只读取需要的一个 set是设置需要读取的第几个表格，total是文件中表格的总数
                int set = 1, total = 10;
                int num = total;
                for (int i = 0; i < set-1; i++) {
                    it.hasNext();
                    it.next();
                }
                while (it.hasNext()) {
                    Table tb = (Table) it.next();
                    System.out.println("这是第" + num + "个表的数据");
                    //迭代行，默认从0开始,可以依据需要设置i的值,改变起始行数，也可设置读取到那行，只需修改循环的判断条件即可
                    for (int i = 0; i < tb.numRows(); i++) {

                        Subject subject = new Subject();


                        TableRow tr = tb.getRow(i);
                        //迭代列，默认从0开始
//                        for (int j = 0; j < tr.numCells(); j++) {
                        for (int j = 0; j < tr.numCells()-1; j++) {
                            TableCell td = tr.getCell(j);//取得单元格
                            //取得单元格的内容
                            for(int k = 0; k < td.numParagraphs(); k++){
                                Paragraph para = td.getParagraph(k);
                                String s = para.text();
                                //去除后面的特殊符号
                                if(null != s && !"".equals(s)){
                                    s = s.substring(0, s.length()-1);
                                }

//                                if(j == 0){
//                                    subject.setCode(s);
//                                }
//                                if(j == 1){
//                                    subject.setName(s);
//                                }

                                System.out.print("---" + s + "\t");
                            }
                        }
                        //将数据保存到数据库中
//                        mongoTemplate.save(subject);
                        resultList.add(subject);

                        System.out.println();
                    }
                    // 过滤多余的表格
                    while (num < total) {
                        it.hasNext();
                        it.next();
                        num += 1;
                    }
                }
            }
            System.out.println("一共：" + resultList.size() + " 个");

        }catch(Exception e){
            e.printStackTrace();
        }
//        System.out.println(JSON.toJSONString(resultList, true));
    }

}
