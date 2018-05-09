package com.posin.menumanager;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.posin.menumanager", appContext.getPackageName());
    }

    @Test
    public void calcStringLength() throws Exception {
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
