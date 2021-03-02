package com.binginx.myboot.gc;

public class GCMain {
    public static final int _1M = 1024*1024;

    /**
     * -Xms200M -Xmx200M -Xmn100M -XX:SurvivorRatio=8 -XX:+UseParallelGC
     * -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:./gc.log
     * @param args
     */
    public static void main(String[] args) {
        byte[] b1 = new byte[_1M * 30];
        b1 = new byte[_1M * 30];
        b1 = null;
        byte[] b2 = new byte[_1M * 30];
    }
}
