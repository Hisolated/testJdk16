package com.isolate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description: 自定义排序规则，规则由我定
 * @author: isolate
 * @email: 15071340963@163.com
 * @date: 2021/7/10 13:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSortFromMyself {

    /** 自定义排序规则
     *  1. 读取自定义规则字典文件（可能还包含一些特殊符号，特殊字符也可能在排序规则中）
     *  2. 将所有的自定义规则拼接成一个string，对string进行处理
     *      处理规则：
     *      1) 将所有自定义的规则数字或是字母全部读取放在一个string中，回车拼接时前面加一个空格；
     *      2) 全部读取出来后，进行一个全局处理，双空格全部替换成一个空格
     *  3. 将生成的string以空格截取然后放在一个list或是数组中，作为排序依据
     *  4. 对于我们的排序目标数据，进行根据我们自定义的排序，双重for循环对数据进行排序
     *     每次将拿出的数据，获取在排序规则中的index
     *
     */
    @Test
    public void testSort(){


    }

}
