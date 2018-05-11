package com.posin.menumanager;

import android.nfc.Tag;
import android.util.Log;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;

import static android.R.attr.digits;
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

//        HashMap<String, String> hashMap = new HashMap<>();
//        for (int i = 0; i < 10; i++) {
//            hashMap.put("gree" + i, "我排序为" + i);
//        }
//
//        Iterator<String> iterator = hashMap.keySet().iterator();
//        while (iterator.hasNext()) {
//            System.out.println("测试值为： " + hashMap.get(iterator.next()));
//        }

//        System.out.println("40.275 * 3= " + (float) 40.275 * 3);
//        System.out.println("add(40.275,3) " + add(40.275, 3));

        System.out.println("40.275 + 3= " +  (40.275 + 3.01));

//        StringBuilder sb = new StringBuilder();
//        sb.append("#.");
//        for (int i = 0; i < 1; i++) {
//            sb.append("#");
//        }
//        DecimalFormat df = new DecimalFormat(sb.toString());
//
//        String format = df.format(40.2712545 * 3);
//
//        System.out.println("format: " + format);
//
//        System.out.println("test end ... ");

    }

    public double add(double b1, double b2) {
        BigDecimal bignum1 = new BigDecimal(b1);
        BigDecimal bignum2 = new BigDecimal(b2);
        return bignum1.multiply(bignum2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}