package cn.luosonglin.test.util;

import java.util.Random;

/**
 * Created by luosonglin on 25/11/2016.
 */
public class RandUtil {
    public static String rand(int length) {

        int[] array = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        Random rand = new Random();
        for (int i = 10; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = array[index];
            array[index] = array[i - 1];
            array[i - 1] = tmp;
        }
        int result = 0;
        for (int i = 0; i < length; i++)
            result = result * 10 + array[i];
        return String.valueOf(result);
    }

    public static void main(String[] args) {
        System.out.println(rand(4));
    }
}