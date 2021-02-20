package com.inspur.slf4jdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


/**
 * 【强制】异常信息应该包括两类信息：案发现场信息和异常堆栈信息。如果不处理，那么通过 关键字 throws 往上抛出。
 *
 * 现场信息
 * 异常堆栈
 *
 * 【推荐】尽量用英文来描述日志错误信息，如果日志中的错误信息用英文描述不清楚的话使用 中文描述即可，否则容易产生歧义。国际化团队或海外部署的服务器由于字符集问题，【强制】 使用全英文来注释和描述日志错误信息。
 */
public class Slf4jDemo2 {
    private static final Logger logger = LoggerFactory.getLogger(Slf4jDemo2.class);

    public static void main(String[] args) {

        new  Slf4jDemo2().method1();

    }

    private void method1() {
        //此处使用运行时异常来演示，并不特别合适，因为正常情况下，运行时异常不应该捕获。应该进行判断，只演示日志的记录效果是可以的
        try {
            int i = 10 / 0;
        } catch (Exception e) {
            // 错误示例，不能正确打印堆栈信息
            logger.error("method1 error "+ e);  // 直接拼接e
            // 正确示例
            logger.error("method1 error ", e);  //只有一个e的时候，当做为参数，一定要做为最后一个参数

            // 错误示例
            logger.error("error {} method1 {} ", e, "名字");  // 占位符使用，动态数组和异常一定要做为最后一个参数
            // 正确示例
            logger.error("method1 {} error ", "名字", e); // 不需要占位符

            // 错误示例
            logger.error("error {} method1 {} ", new Object[]{e, "名字"} );  // 数组的方式传递参数，异常一定要做为最后一个参数,不做为最后一个参数的情况下，当做字符串来处理
            // 正确示例
            logger.error("method1 {}, 在 {} 发生error ", new Object[]{"名字",new Date(), e} );
        }
    }


}
