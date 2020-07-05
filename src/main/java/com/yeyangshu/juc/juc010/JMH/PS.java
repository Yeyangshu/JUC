package com.yeyangshu.juc.juc010.JMH;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/7/5 9:59
 */
public class PS {
    static List<Integer> nums = new ArrayList<>();
    static {
        Random r = new Random();
        for (int i = 0; i < 10000; i++) {
            nums.add(1000000 + r.nextInt(1000000));
        }
    }
    static void foreach() {
        nums.forEach(v -> isPrime(v));
    }

    static void parallel() {
        nums.parallelStream().forEach(PS::isPrime);
    }

    static boolean isPrime(int num) {
        for (int i = 2; i < num/2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
