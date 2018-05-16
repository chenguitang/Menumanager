package com.posin.menumanager;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import static android.R.attr.digits;
import static java.util.Arrays.asList;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private static final String TAG = "ExampleUnitTest";

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void calcStringLength() throws Exception {


        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        for (int i = 0; i < 10; i++) {
            hashMap.put("测试" + i, "我就是" + i);
        }

        hashMap.remove("测试4");
        hashMap.put("测试4", "测试41243121312");

        ListIterator<Map.Entry<String, String>> iMap = new ArrayList<>(hashMap.entrySet()).listIterator(hashMap.size());
        while (iMap.hasPrevious()) {

            Map.Entry<String, String> entry = iMap.previous();
            System.out.println(entry.getKey() + ":  " + entry.getValue());
        }

//        for (String key : hashMap.keySet()) {
//            System.out.println("key: " + key);
//        }
    }

    // 二维数组纵向合并
    private static Object[][] unite(Object[][] content1, Object[][] content2) {
        Object[][] newArrey = new Object[][]{};
        List<Object[]> list = new ArrayList<>();
        list.addAll(Arrays.asList(content1));
        list.addAll(Arrays.asList(content2));
        return list.toArray(newArrey);
    }


}