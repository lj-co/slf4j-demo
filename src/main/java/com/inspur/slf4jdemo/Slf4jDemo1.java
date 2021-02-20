package com.inspur.slf4jdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 【强制】对 trace/debug/info 级别的日志输出，必须使用条件输出形式或者使用占位符的方 式。
 * 说明：logger.debug("Processing trade with id: " + id + " and symbol: " + symbol); 如果日志级别是 warn，上述日志不会打印，但是会执行字符串拼接操作，如果 symbol 是对象， 会执行 toString()方法，浪费了系统资源，执行了上述操作，最终日志却没有打印。
 * 正例：（条件）建设采用如下方式
 * if (logger.isDebugEnabled()) {
 * logger.debug("Processing trade with id: " + id + " and symbol: " + symbol);
 * }
 * 正例：（占位符）
 * logger.debug("Processing trade with id: {} and symbol : {} ", id, symbol);
 * 提高性能。
 * 参照：Slf4jDemo1
 */

public class Slf4jDemo1 {
    private  static final Logger logger = LoggerFactory.getLogger(Slf4jDemo1.class);

    public static void main(String[] args) {
        List<String> list = makeList();

        // 查看性能影响
        long start = System.currentTimeMillis();
        logger.debug("debug {}", list);  // 推荐使用占位符
        System.out.println("耗时" + (System.currentTimeMillis() - start));

        long start2 = System.currentTimeMillis();
        logger.debug("debug {}"+ list);  //此时会先将list拼接字符串，然后做为参数，调用debug(不推荐)
        System.out.println("耗时" + (System.currentTimeMillis() - start2));

//        logger.warn("err " + (System.currentTimeMillis() - start2));
//        logger.error("error");
    }

    private static List<String> makeList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            list.add(i + "");
        }
        return list;
    }

}
