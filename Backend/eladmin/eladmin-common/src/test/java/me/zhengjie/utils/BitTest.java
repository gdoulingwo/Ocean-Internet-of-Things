package me.zhengjie.utils;

import java.security.SecureRandom;
import java.util.Arrays;

public class BitTest {
    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[16]; // 128 bits are converted to 16 bytes;
        random.nextBytes(bytes);
        System.out.println(new String(bytes));
    }
}
