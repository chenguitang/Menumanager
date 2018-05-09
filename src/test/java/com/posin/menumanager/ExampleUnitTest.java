package com.posin.menumanager;

import android.nfc.Tag;
import android.util.Log;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;

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
//        String name = "姜葱鸡拼烧鸭";
////        Log.e(TAG, "length: "+name.length());
//        System.out.print("length: " + name.length());
//        System.out.print("submit: " + name.substring(0,name.length()-1)+"...");

        HashMap<String, String> hashMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            hashMap.put("gree" + i, "我排序为" + i);
        }

        Iterator<String> iterator = hashMap.keySet().iterator();
        while (iterator.hasNext()) {
            System.out.println("测试值为： " + hashMap.get(iterator.next()));
        }
        System.out.println("test end ... ");

    }
}