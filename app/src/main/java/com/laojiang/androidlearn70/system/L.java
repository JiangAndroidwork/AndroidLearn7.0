package com.laojiang.androidlearn70.system;

import android.util.Log;

import com.laojiang.androidlearn70.MyApplication;

import java.io.PrintWriter;
import java.io.StringWriter;



/**
 * 将Log 工具类 , 显示调用方法所在的 文件，行号
 * 在debug模式下开启，在 release模式下关闭  提高性能
 * Created by huiyao huang on 2016/7/17.
 */
public class L {
    public static final String TAG = "hhy"; // logcat filter name
    private static final int METHOD_COUNT = 2; // show method count in trace
    private static final int MIN_STACK_OFFSET = 3;// starts at this class after two native calls
    private static final int MAX_STACK_TRACE_SIZE = 131071; //128 KB - 1

    /**
     * 打印日志：级别 info
     * 默认输出  日志信息+当前类名和行数
     * @param str 日志信息
     */
    public static void i (String str){
        info(str);
    }

    /**
     * 打印日志：级别 debug
     * 默认输出  日志信息+当前类名和行数
     * @param str 日志信息
     */
    public static void d (String str){
        debug(str);
    }
    /**
     * 打印日志：级别 error
     * 默认输出  日志信息+当前类名和行数
     * @param str 日志信息
     */
    public static void e (String str){
        error(str);
    }

    /**
     * 打印日志：级别 warn
     * 默认输出  日志信息+当前类名和行数
     * @param str 日志信息
     */
    public static void w (String str){
        warn(str );
    }

    /**
     * 打印日志：级别 verbose
     * 默认输出  日志信息+当前类名和行数
     * @param str 日志信息
     */
    public static void v (String str){
        verbose(str);
    }

    /**
     * 打印日志：级别 warn
     * 默认输出  Throwable日志信息
     * @param throwable 日志信息
     */
    public static void w (Throwable throwable){
        warn(toStackTraceString(throwable));
    }

    /**
     * 打印日志：级别 error
     * 默认输出  Throwable日志信息
     * @param throwable 日志信息
     */
    public static void e (Throwable throwable){
        error(toStackTraceString(throwable));
    }

    /**
     * 打印日志：级别 info
     * 默认输出 日志信息+当前类名和行数
     * @param tag Log tag
     * @param str 日志信息
     */
    public static void i (String tag , String str){
        info(tag,str );
    }

    /**
     * 打印日志：级别 debug
     * 默认输出 日志信息+当前类名和行数
     * @param tag Log tag
     * @param str 日志信息
     */
    public static void d (String tag , String str){
        Log.d(tag, str );
    }

    /**
     * 打印日志；级别 error
     * 默认输出 日志信息+当前类名和行数
     * @param tag Log tag
     * @param str 日志信息
     */
    public static void e (String tag , String str){
        Log.e(tag, str );
    }

    /**
     * 打印日志：级别 warn
     * 默认输出 日志信息+当前类名和行数
     * @param tag Log tag
     * @param str 日志信息
     */
    public static void w (String tag , String str){
        Log.w(tag, str );
    }

    /**
     * 打印日志：级别 verbose
     * 默认输出 日志信息+当前类名和行数
     * @param tag Log tag
     * @param str 日志信息
     */
    public static void v (String tag , String str){
        Log.v(tag, str );
    }

    /**
     * 日志输出具体实现 debug模式下才能输出  级别：info
     * @param tag 标记
     * @param str 日志输出信息
     */
    private static void info(String tag , String str){
        //只有在debug模式下，才显示日志
        if(MyApplication.DEBUG){
            Log.i(tag , str + getTraceElement());
        }
    }

    /**
     * 日志输出具体实现 debug模式下才能输出  级别：info
     * @param str 日志输出信息
     */
    private static void info(String str){
        //只有在debug模式下，才显示日志
        if(MyApplication.DEBUG){
            Log.i(TAG , str + getTraceElement());
        }
    }
    /**
     * 日志输出具体实现 debug模式下才能输出  级别：debug
     * @param str 日志输出信息
     */
    private static void debug(String str){
        //只有在debug模式下，才显示日志
        if(MyApplication.DEBUG){
            Log.d(TAG , str + getTraceElement());
        }
    }

    /**
     * 日志输出具体实现 debug模式下才能输出  级别：verbose
     * @param str 日志输出信息
     */
    private static void verbose(String str){
        //只有在debug模式下，才显示日志
        if(MyApplication.DEBUG){
            Log.v(TAG , str + getTraceElement());
        }
    }

    /**
     * 日志输出具体实现 debug模式下才能输出  级别：error
     * @param str 日志输出信息
     */
    private static void error(String str){
        //只有在debug模式下，才显示日志
        if(MyApplication.DEBUG){
            Log.e(TAG , str + getTraceElement());
        }
    }

    /**
     * 日志输出具体实现 debug模式下才能输出  级别：warn
     * @param str 日志输出信息
     */
    private static void warn(String str){
        //只有在debug模式下，才显示日志
        if(MyApplication.DEBUG){
            Log.w(TAG , str + getTraceElement());
        }
    }

    /**
     * 可显示调用方法所在的文件行号，在AndroidStudio的logcat处可点击定位。
     * 此方法参考：https://github.com/orhanobut/logger
     */
    private static String getTraceElement() {
        try {
            int methodCount = METHOD_COUNT;
            StackTraceElement[] trace = Thread.currentThread().getStackTrace();
            int stackOffset = _getStackOffset(trace);

            //corresponding method count with the current stack may exceeds the stack trace. Trims the count
            if (methodCount + stackOffset > trace.length) {
                methodCount = trace.length - stackOffset - 1;
            }

            String level = "    ";
            StringBuilder builder = new StringBuilder();
            for (int i = methodCount; i > 0; i--) {
                int stackIndex = i + stackOffset;
                if (stackIndex >= trace.length) {
                    continue;
                }
                builder.append("\n")
                        .append(level)
                        .append(_getSimpleClassName(trace[stackIndex].getClassName()))
                        .append(".")
                        .append(trace[stackIndex].getMethodName())
                        .append(" ")
                        .append("(")
                        .append(trace[stackIndex].getFileName())
                        .append(":")
                        .append(trace[stackIndex].getLineNumber())
                        .append(")");
                level += "    ";
            }
            return builder.toString();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Determines the starting index of the stack trace, after method calls made by this class.
     *
     * @param trace the stack trace
     * @return the stack offset
     */
    private static int _getStackOffset(StackTraceElement[] trace) {
        for (int i = MIN_STACK_OFFSET; i < trace.length; i++) {
            StackTraceElement e = trace[i];
            String name = e.getClassName();
            if (!name.equals(L.class.getName())) {
                return --i;
            }
        }
        return -1;
    }

    /**
     * 得到简单的类名 （去掉 . 后面的字符）
     * @param name 类名
     * @return 去掉 . 后面字符 的类名
     */
    private static String _getSimpleClassName(String name) {
        int lastIndex = name.lastIndexOf(".");
        return name.substring(lastIndex + 1);
    }

    /**
     * To stack trace string string.
     * <p/>
     * 此方法参见：https://github.com/Ereza/CustomActivityOnCrash
     *
     * @param throwable the throwable
     * @return the string
     */
    public static String toStackTraceString(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        String stackTraceString = sw.toString();
        //Reduce data to 128KB so we don't get a TransactionTooLargeException when sending the intent.
        //The limit is 1MB on Android but some devices seem to have it lower.
        //See: http://developer.android.com/reference/android/os/TransactionTooLargeException.html
        //And: http://stackoverflow.com/questions/11451393/what-to-do-on-transactiontoolargeexception#comment46697371_12809171
        if (stackTraceString.length() > MAX_STACK_TRACE_SIZE) {
            String disclaimer = " [stack trace too large]";
            stackTraceString = stackTraceString.substring(0, MAX_STACK_TRACE_SIZE - disclaimer.length()) + disclaimer;
        }
        return stackTraceString;
    }
}
