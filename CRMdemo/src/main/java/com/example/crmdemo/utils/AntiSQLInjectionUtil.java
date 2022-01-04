package com.example.crmdemo.utils;

/**
 * 防止SQL注入工具类
 */
public class AntiSQLInjectionUtil {
    public static String SQL(String str)
    {
        return str.replaceAll(".*([';=]+|(--)+).*", " ");//采用正则表达式将包含有 单引号(')，分号(;) 等号(=)和 注释符号(--)的语句给替换掉来防止SQL注入
    }
}
