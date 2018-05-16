package com.posin.menumanager.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Greetty on 2018/5/16.
 * <p>
 * 数组工具类
 */
public class ArrayUtils {


    /**
     * 合并一维数组
     *
     * @param content1 数组A
     * @param content2 数组B
     * @return 合并后数组
     * @throws Exception
     */
    public static String[] mergeOneDimArray(String[] content1, String[] content2) throws Exception {
        String[] a = content1;
        String[] b = content2;
        String[] c = new String[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    /**
     * 二维数组纵向合并
     *
     * @param content1 数组A
     * @param content2 数组B
     * @return 合并后的数组
     * @throws Exception
     */
    public static String[][] mergeTwoDimArrayVer(String[][] content1, String[][] content2)
            throws Exception {
        if (content1 == null || content1.length == 0) return content2;
        if (content2 == null || content2.length == 0) return content1;
        String[][] newArray = new String[][]{};

        List<String[]> list = new ArrayList<>();

        list.addAll(Arrays.asList(content1));
        list.addAll(Arrays.asList(content2));

        return list.toArray(newArray);
    }


    /**
     * 二维数组横向合并
     *
     * @param content1 数组A
     * @param content2 数组B
     * @return 合并后的数组
     * @throws Exception
     */
    public static String[][] mergeTwoDimArrayHor(String[][] content1, String[][] content2)
            throws Exception {
        if (content1 == null || content1.length == 0) return content2;
        if (content2 == null || content2.length == 0) return content1;
        String[][] newArrey = null;
        // 根据数组的长度，判断要补全哪个数组
        if (content1.length > content2.length) {
            newArrey = new String[content1.length][];
            // 补全较短的数组
            String[][] temps = new String[content1.length - content2.length][content2[0].length];
            for (int i = 0; i < temps.length; i++) {
                for (int j = 0; j < temps[0].length; j++)
                    temps[i][j] = "";
            }
            String[][] btemp = mergeTwoDimArrayVer(content2, temps);
            // 合并
            for (int k = 0; k < content1.length; k++) {
                newArrey[k] = mergeOneDimArray(content1[k], btemp[k]);
            }
        } else {
            newArrey = new String[content2.length][];
            String[][] temps = new String[content2.length - content1.length][content1[0].length];
            for (int i = 0; i < temps.length; i++) {
                for (int j = 0; j < temps[0].length; j++)
                    temps[i][j] = "";
            }
            String[][] atemp = mergeTwoDimArrayVer(content1, temps);
            for (int k = 0; k < content2.length; k++) {
                newArrey[k] = mergeOneDimArray(atemp[k], content2[k]);
            }
        }
        return newArrey;
    }


}
