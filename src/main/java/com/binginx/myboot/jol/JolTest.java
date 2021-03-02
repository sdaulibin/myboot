package com.binginx.myboot.jol;

import cn.hutool.core.util.StrUtil;
import org.openjdk.jol.info.ClassLayout;

import java.security.SecureRandom;

public class JolTest {
    Object object = new Object();
    public static void main(String[] args) {
        Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
        System.out.println(ClassLayout.parseInstance(new JolTest()).toPrintable());

        System.out.println(toBase62(2342423));
//        byte[] desKey = generateRN(16,true);
//        for (int i = 0; i < desKey.length; i++) {
//            System.out.print((char)desKey[i]+"");
//        }
//        System.out.println(new String(desKey));
    }

    private static final String BASE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String toBase62(long num) {
        StringBuilder sb = new StringBuilder();
        int targetBase = BASE.length();
        do {
            int i = (int) (num % targetBase);
            sb.append(BASE.charAt(i));
            num /= targetBase;
        } while (num > 0);

        return sb.reverse().toString();
    }

//    private static byte[] generateRN(int len, boolean oddParity) {
//        // generate sk
//        SecureRandom sr = new SecureRandom();
//        byte[] sk = sr.generateSeed(len);
//        sr.nextBytes(sk);
//        if (oddParity) {
//            sk = toOddParity(sk, sk.length);
//        }
//        return sk;
//    }
//
//    private static byte[] toOddParity(byte[] values, int len) {
//        int i, flag, count;
//        for (i = 0; i < len; i++) {
//            count = 0;
//            for (flag = 0x01; flag != 0; flag <<= 1)
//                if ((values[i] & flag) == 0x00)
//                    count++;
//            if ((count % 2) == 0)
//                values[i] ^= 0x01;
//        }
//        return values;
//    }
}
