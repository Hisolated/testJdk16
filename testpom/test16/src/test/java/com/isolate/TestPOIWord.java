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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
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
                        // 获取表格内容控件
                        List<CTSdtCell> tblSdtList = row.getCtRow().getSdtList();

                        if (tblSdtList != null && tblSdtList.size() > 0) {
                            for (CTSdtCell ctSdtCell : tblSdtList) {
                                CTSdtPr sdtPr = ctSdtCell.getSdtPr();
//                                printSdtPrContent(sdtPr);
                                CTSdtContentCell sdtContent = ctSdtCell.getSdtContent();
                                printSdtContentCell(sdtContent);
                            }
                        }


                        for (int j = 0; j < cells.size(); j++) {
                            XWPFTableCell cell = cells.get(j);
                            List<IBodyElement> bodyElements = cell.getBodyElements();
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



    //无法获取更多的内容(如文本类型,是否可编辑)
    public void printXWPFSDTContent(XWPFSDT sdt) {
        StringBuffer sb = new StringBuffer();
        sb.append(" 标记:").append(sdt.getTag());
        sb.append(" 标题:").append(sdt.getTitle());
        ISDTContent content = sdt.getContent();
        sb.append(" 内容:").append(content.getText());
        System.out.println(sb.toString());
    }

    public void printParaListContent(List<XWPFParagraph> paraList) {
        if (paraList == null || paraList.size() == 0) {
            return;
        }
        for (XWPFParagraph para : paraList) {
            List<CTSdtRun> sdtList = para.getCTP().getSdtList();
            if (sdtList == null || sdtList.size() == 0) {
                continue;
            }
            for (CTSdtRun sdtRun : sdtList) {
                CTSdtPr sdtPr = sdtRun.getSdtPr();
//                printSdtPrContent(sdtPr);
                CTSdtContentRun sdtContent = sdtRun.getSdtContent();
                printSdtContent(sdtContent);
            }
        }
    }
//
//    // 解析样式,区分纯文本和格式文本
//    public void printSdtPrContent(CTSdtPr sdtPr) {
//        if (sdtPr == null) {
//            return;
//        }
//        StringBuffer sb = new StringBuffer();
//        List<CTSdtText> textList = sdtPr.getTextList();
//        if (textList != null && textList.size() > 0) {
//            sb.append(" 内容控件类型:").append("纯文本");
//            CTSdtText sdtText = textList.get(0);
//            if (sdtText.getMultiLine() != null) {
//                int mulType = sdtText.getMultiLine().intValue();
//                if (mulType == 1 || mulType == 3 || mulType == 6) {
//                    sb.append(" 是否允许回车:").append("是");
//                }
//            }
//        } else {
//            sb.append(" 内容控件类型:").append("格式文本");
//        }
//        List<CTDecimalNumber> idList = sdtPr.getIdList();
//        if (idList != null && idList.size() > 0) {
//            sb.append(" ID:").append(idList.get(0).getVal());
//        }
//        List<CTString> aliasList = sdtPr.getAliasList();
//        if (aliasList != null && aliasList.size() > 0) {
//            sb.append(" 标题:").append(aliasList.get(0).getVal());
//        }
//        List<CTString> tagList = sdtPr.getTagList();
//        if (tagList != null && tagList.size() > 0) {
//            sb.append(" 标记:").append(tagList.get(0).getVal());
//        }
//        List<CTLock> lockList = sdtPr.getLockList();
//        if (lockList != null && lockList.size() > 0) {
//            CTLock ctLock = lockList.get(0);
//            int lockType = ctLock.getVal().intValue();
//            switch (lockType) {
//                case 1:
//                    sb.append(" 锁定方式:").append("无法删除内容控件");
//                    break;
//                case 2:
//                    sb.append(" 锁定方式:").append("无法编辑内容");
//                    break;
//                case 4:
//                    sb.append(" 锁定方式:").append("无法删除内容控件，无法编辑内容");
//                    break;
//                default:
//                    sb.append(" 锁定方式:").append(ctLock.getVal());
//                    break;
//            }
//        }
//        List<CTOnOff> tempList = sdtPr.getTemporaryList();
//        if (tempList != null && tempList.size() > 0) {
//            if (tempList.get(0).getVal() != null) {
//                int isOn = tempList.get(0).getVal().intValue();
//                if (isOn == 1 || isOn == 3 || isOn == 6) {
//                    sb.append(" 替换后是否删除内容控件:").append("是");
//                }
//            } else {
//                sb.append(" 替换后是否删除内容控件:").append("是");
//            }
//        }
//        List<CTRPr> rprList = sdtPr.getRPrList();
//        if (rprList != null && rprList.size() > 0) {
//            CTRPr rpr = rprList.get(0);
//            CTString rprStyle = rpr.getRStyle();
//            if (rprStyle != null) {
//                sb.append(" 样式名称:").append(rprStyle.getVal());
//            }
//        }
//        System.out.println(sb.toString());
//    }

    // 段落内内容控件
    public void printSdtContent(CTSdtContentRun sdtContentRun) {
        if (sdtContentRun == null) {
            return;
        }
        StringBuffer sb = new StringBuffer();
        List<CTR> ctrList = sdtContentRun.getRList();
        sb.append(getCTRContent(ctrList));
        System.out.println("内容:" + sb.toString());
    }

    // 表格内内容控件
    public void printSdtContentCell(CTSdtContentCell sdtContentCell) {
        if (sdtContentCell == null) {
            return;
        }
        StringBuffer sb = new StringBuffer();
        List<CTTc> cttcList = sdtContentCell.getTcList();
        sb.append(getCTTcContent(cttcList));
        System.out.println("内容:" + sb.toString());
    }

    // 段落
    public String getCTRContent(List<CTR> ctrList) {
        StringBuffer sb = new StringBuffer();
        if (ctrList != null && ctrList.size() > 0) {
            for (CTR ctr : ctrList) {
                List<CTText> tList = ctr.getTList();
                if (tList != null && tList.size() > 0) {
                    for (CTText ctText : tList) {
                        sb.append(ctText.getStringValue());
                    }
                }
            }
        }
        return sb.toString();
    }

    // 表格
    public String getCTTcContent(List<CTTc> cttcList) {
        StringBuffer sb = new StringBuffer();
        if (cttcList != null && cttcList.size() > 0) {
            for (CTTc cttc : cttcList) {
                List<CTP> pList = cttc.getPList();
                if (pList != null && pList.size() > 0) {
                    for (CTP ctp : pList) {
                        List<CTR> rList = ctp.getRList();
                        sb.append(getCTRContent(rList));
                    }
                }
            }
        }
        return sb.toString();
    }


}
