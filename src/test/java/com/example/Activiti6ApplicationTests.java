package com.example;

import com.example.enums.JwtExpiration;
import com.example.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 一.jar命令参数：
 * <p>
 * jar命令格式：jar {c t x u f }[ v m e 0 M i ][-C 目录]文件名...
 * <p>
 * 其中{ctxu}这四个参数必须选选其一。[v f m e 0 M i ]是可选参数，文件名也是必须的。
 * <p>
 * -c  创建新档案
 * -t  列出档案目录
 * -x  从档案中提取指定的 (或所有) 文件
 * -u  更新现有档案
 * -v  在标准输出中生成详细输出
 * -f  指定档案文件名
 * -m  包含指定清单文件中的清单信息
 * -n  创建新档案后执行 Pack200 规范化
 * -e  为绑定到可执行 jar 文件的独立应用程序
 * 指定应用程序入口点
 * -0  仅存储; 不使用任何 ZIP 压缩
 * -M  不创建条目的清单文件
 * -i  为指定的 jar 文件生成索引信息
 * -C  更改为指定的目录并包含以下文件
 * <p>
 * -c  创建一个jar包
 * -t 显示jar中的内容列表
 * -x 解压jar包
 * -u 添加文件到jar包中
 * -f 指定jar包的文件名
 * -v  生成详细的报造，并输出至标准设备
 * -m 指定manifest.mf文件.(manifest.mf文件中可以对jar包及其中的内容作一些一设置)
 * -0 产生jar包时不对其中的内容进行压缩处理
 * -M 不产生所有文件的清单文件(Manifest.mf)。这个参数与忽略掉-m参数的设置
 * -i    为指定的jar文件创建索引文件
 * <p>
 * -C 表示转到相应的目录下执行jar命令,相当于cd到那个目录，然后不带-C执行jar命令
 * <p>
 * <p>
 * 示例: https://www.cnblogs.com/liyanbin/p/6088458.html
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Activiti6ApplicationTests {

    @Test
    public void contextLoads() throws Exception {

    }


}
