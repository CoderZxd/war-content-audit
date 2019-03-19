package com.intermap.content.audit.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project war-content-audit
 * @Package com.intermap.content.audit.controller
 * @Author：zouxiaodong
 * @Description:
 * @Date:Created in 17:10 2019/3/18.
 */
public class Test {
    public static void main(String[] args){
        List<String> test = new ArrayList<String>(10);
        test.add("1");
        test.add("2");
        test.add("3");
        test.add("4");
        test.add("5");
        System.out.println(test.toString());
        System.out.println(test.size());
        test.remove(0);
        System.out.println(test.toString());
        System.out.println(test.size());
        test.remove(0);
        System.out.println(test.toString());
        System.out.println(test.size());
    }
}
