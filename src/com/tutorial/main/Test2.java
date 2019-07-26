package com.tutorial.main;

import java.util.ArrayList;
import java.util.List;

public class Test2 {
    List list = new ArrayList();


    public static void main(String[] args) {
        Test2 test2 = new Test2();
        for (int i = 0; i < 10; i++) {
            test2.list.add(i);
        }
        test2.list.clear();
        test2.list.clear();
        for (Object i: test2.list) System.out.println(i);
    }
}
