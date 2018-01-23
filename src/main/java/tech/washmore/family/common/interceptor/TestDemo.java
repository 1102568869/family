package tech.washmore.family.common.interceptor;

/**
 * @author Washmore
 * @version V1.0
 * @summary TODO
 * @Copyright (c) 2018, Lianjia Group All Rights Reserved.
 * @since 2018/1/18
 */
public class TestDemo {
    public static void main(String[] args) {
//        int array[] = new int[]{1, 3, -1, 5, -2};
//        System.out.print("原数组为:");
//        for (int i = 0; i < array.length; i++) {
//            System.out.print(array[i] + "\t");
//        }
//
//        //注意这里使用 i<= array.length / 2
//        //多遍历了一个数,防止奇数个数元素的情况下,中间那个数如果是负数,就不会被修改为0
//        for (int i = 0; i <= array.length / 2; i++) {
//            //两数互换
//            int temp = array[i];
//            array[i] = array[array.length - 1 - i];
//            array[array.length - 1 - i] = temp;
//
//            //如果哪个数小于0,就让他等于0;
//            if (array[i] < 0) {
//                array[i] = 0;
//            }
//            if (array[array.length - 1 - i] < 0) {
//                array[array.length - 1 - i] = 0;
//            }
//        }


        int array[] = new int[]{1, 3, -1, 5, -2};
        System.out.print("原数组为:");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");
        }
        //这次我们分两步走
        //第一步 倒序
        //注意这里使用 i< array.length / 2 这是倒序的正常写法  不再是 <=
        for (int i = 0; i <= array.length / 2; i++) {
            //两数互换
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }

        System.out.print("\n倒序后的数组为:");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");
        }

        //第二步 将小于0的数置为0
        for (int i = 0; i < array.length; i++) {
            //如果哪个数小于0,就让他等于0;
            if (array[i] < 0) {
                array[i] = 0;
            }
        }

        System.out.print("\n处理掉负数后的数组为:");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");
        }
    }
}
