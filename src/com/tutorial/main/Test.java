package com.tutorial.main;


public class Test {

    private static final int MAXN = 1 << 20;
    private int[] x;

    public Test() {
        x = new int[MAXN];
    }

    // x [ n ] = a x [ n-1 ] + b ( mod m )
    public void rand() {
//        x[0] = (int) (Math.random() * 100 + 1);  // 随机种子(可以用日期产生)
        x[0] = 1;  // 随机种子(可以用日期产生)
        /* 保证m与a互质 */
        int m = MAXN;
        int a = 9;  // a = 4p + 1
        int b = 7;  // b = 2q + 1

        System.out.println(x[0]);
        /* 取前1w个数*/
        for (int i = 1; i < 10000; ++i) {
            x[i] = (a * x[i - 1] + b) % m;
            System.out.println(x[i]);
        }
    }

    public static void main(String[] args) {
            Test mc = new Test();
            mc.rand();
    }


}
