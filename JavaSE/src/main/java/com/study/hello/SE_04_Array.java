package com.study.hello;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SE_04_Array {

    public static void main(String[] args) {
        ergodic();
    }

    /**
     * 数组
     * - 数组就是用来存储一批同种类型数据的容器，数组也是一种数据类型对象是(object)，是引用类型。
     * - 数组中的元素可以是任何数据类型，包括基本类型和引用类型，但是不能混用，下标是从0开始的。
     * - 数组初始化必须指定类型和长度，一旦定义出来就不能更改，程序执行的过程中长度和类型是固定的
     */
    public static void array() {
        // 注意：数组变量名中存储的是数组在内存中的地址，数组是引用类型。
        // 什么类型的数组存放什么类型的数据，否则报错。
        // “数据类型[] 数组名”
        int[] arr;

        // 静态初始化数组：定义数组的时候直接给数组赋值，有长度，有元素
        // 数据类型[] 数组名 = new 数据类型[]{元素1,元素2,元素3…};
        int[] staticArr = new int[]{12, 24, 36};
        // 简化格式
        // 数据类型[] 数组名 = {元素1,元素2,元素3,…};
        int[] simplifyArr = {12, 24, 36};

        // 数组的动态初始化：定义数组的时候只确定元素的类型和数组的长度，之后再存入具体数据，有长度，没有元素(存在默认值)
        // 数据类型[] 数组名 = new 数据类型[长度];
        int[] autoArr = new int[10];
        autoArr[0] = 10;
        // 数组基本属性
        // 访问数组的元素，数组名称[索引]
        int indexOf = autoArr[0];
        // 访问数组的长度，数组名称.length
        int length = autoArr.length;
        // 数组的最大索引，数组名.length–1，前提：元素个数大于0
        int maxIndex = autoArr.length - 1;

        // 元素默认值规则
        // 基本类型
        // byte、short、char、int、long    0
        // float、double                 0.0
        // boolean                      false
        // 引用类型
        // 类、接口、数组、String          null
    }

    /**
     * 数组遍历
     * - 利用循环一个一个的访问数组内的数据
     */
    public static void ergodic() {
        // 数组遍历
        int[] arr = {10, 20, 30, 40, 50};
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        // 增强for循环
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
        // 或者使用Arrays类遍历数组，返回数组的内容(字符串形式)
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 冒泡排序
     * - 通过对待排序序列从后向前(从下标较大的元素开始)，依次比较相邻元素的值，若发现逆序则交换，使值较大的元素逐渐从前移向后部，就象水底下的气泡一样逐渐向上冒。
     */
    public static void bubbleSort() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        System.out.println("排序前数组内容：" + Arrays.toString(arr));
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("排序后数组内容：" + Arrays.toString(arr));
    }

    /**
     * 二维数组
     */
    public static void twoArray() {
        // 类型[][] 数组名= new 类型[大小][大小];
        int[][] arr1 = new int[10][10];
        //静态初始化
        int[][] arr2 = {
                {1, 2, 3, 4, 5},
                {2, 2, 0, 0, 0},
                {3, 0, 3, 0, 0},
                {4, 0, 0, 4, 0},
                {5, 0, 0, 0, 5},
        };
        // 动态初始化,和C语言必须指定列数不同，Java必须指定行数(对象数量)
        int[][] arr3 = new int[10][];

        // 二维数组遍历
        // 二维数组的每个元素是一维数组，所以如果需要得到每个一维数组的值，还需要再次遍历
        int[][] arr = {{1, 2, 3}, {1, 2}, {3}};
        // 遍历二维数组的每个元素(数组)
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("数组第%d行数据：[", i + 1);
            // arr[0]二维数组的第一个元素，arr[0].length二维数组的第一个元素的长度
            for (int j = 0; j < arr[i].length; j++) {
                // arr[0]二维数组的第一个元素(数组),arr[0][0]是arr[0]数组的的一个元素
                System.out.print(arr[i][j] + ",");
            }
            System.out.print("]\n");
        }
        // 使用Arrays类遍历输出的形式
        for (int i = 0; i < arr.length; i++) {
            // arr[i]代表二维数组的第i个元素，每个元素都是一个一维数组
            System.out.printf("数组第%d行数据：%s\n", i, Arrays.toString(arr[i]));
        }
    }
}
