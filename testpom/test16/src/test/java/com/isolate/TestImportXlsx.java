package com.isolate;

import org.apache.poi.ss.usermodel.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Iterator;

/**
 * @description: some desc
 * @author: isolate
 * @email: 15071340963@163.com
 * @date: 2021/6/24 20:19
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestImportXlsx {

    @Test
    public void TestImport() {
        File file = new File("C:\\Users\\强\\Desktop\\user.xlsx");
        Workbook workBook = null;
        Statement statement = null;
        if (file.exists()) {
            try {
                /**获取msyql连接类型*/
                Connection conn = sql();
                FileInputStream fis = new FileInputStream(file);

                if (!conn.isClosed()) {
                    statement = conn.createStatement();
                }
                workBook = WorkbookFactory.create(fis);
                int numberOfSheets = workBook.getNumberOfSheets();
                // sheet工作表
                for (int s = 0; s < numberOfSheets; s++) {
                    Sheet sheetAt = workBook.getSheetAt(s);
                    //获取工作表名称
                    String className = sheetAt.getSheetName();


                    StringBuilder sql = new StringBuilder();

                    //获取当前Sheet的总行数
                    int rowsOfSheet = sheetAt.getPhysicalNumberOfRows();
                    /**
                     * 判断是不是第一列，第一列要作为列名
                     */
                    boolean isFirst = true;

                    /**
                     * 循环行
                     */
                    for (int r = 0; r < rowsOfSheet; r++) {
                        Row row = sheetAt.getRow(r);
                        if (row == null) {
                            continue;
                        } else {
                            if (isFirst) {
                                sql.append("DROP TABLE IF EXISTS " + className + "; ")
                                        .append("CREATE TABLE " + className + " (");
                            } else {
                                sql.delete(0,sql.length());
                                sql.append("INSERT INTO " + className + " VALUES (");
                            }

                            /**
                             * 利用迭代器，循环列
                             */
                            Iterator<Cell> cellIterator = row.cellIterator();
                            while (cellIterator.hasNext()) {
                                Cell cell = cellIterator.next();
                                /**
                                 * CellType 类型 值
                                 * CELL_TYPE_NUMERIC 数值型 0
                                 * CELL_TYPE_STRING 字符串型 1
                                 * CELL_TYPE_FORMULA 公式型 2
                                 * CELL_TYPE_BLANK 空值 3
                                 * CELL_TYPE_BOOLEAN 布尔型 4
                                 * CELL_TYPE_ERROR 错误 5
                                 */
                                /**
                                 * 第一行当为列名
                                 */
                                /**
                                 * 只对number进行处理，其余全部按string处理
                                 */
                                switch (cell.getCellType()) {
                                    case NUMERIC:
                                        if (isFirst) {
                                            sql.append(Integer.valueOf((int) cell.getNumericCellValue()) + " int,");
                                        } else {
                                            sql.append((int) cell.getNumericCellValue() + ",");
                                        }
                                        break;
                                    default:
                                        if (isFirst) {
                                            sql.append(cell.getStringCellValue() + " varchar(255),");
                                        } else {
                                            sql.append(cell.getStringCellValue() + ",");
                                        }

                                        break;
                                }

                                /**
                                 * 判断是不是最后一列
                                 */
                                if (!cellIterator.hasNext()){
                                    sql.deleteCharAt(sql.length()-1);
                                    if (isFirst) {
                                        /**
                                         * 建表
                                         */
                                        sql.append(")ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;");
                                    } else {
                                        sql.append(");");
                                    }
                                }

                            }
                        }
                        statement.execute(sql.toString());
                        isFirst = false;
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("文件不存在!");
        }

    }


    /**
     * 获取数据库连接
     */
    public static Connection sql() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://192.168.31.37:3306/test?useUnicode=yes&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=UTC&useSSL=false";
        String user = "root";
        String password = "123456";
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

}
