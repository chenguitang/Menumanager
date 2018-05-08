package com.posin.menumanager.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

/**
 * Created by Ryan_Zhao on 2017/1/18.
 */

public class Json {
    private static final JSONDeserializer<Map<String,String> > DESERIALIZER_MAP = new JSONDeserializer<Map<String,String> >();
    private static final JSONDeserializer<List<Map<String,String>>> DESERIALIZER_LIST = new JSONDeserializer<List<Map<String,String>>>();
    private static final JSONSerializer SERIALIZER = new JSONSerializer();

    public static List<Map<String,String>> stringToList(String s) {
        return DESERIALIZER_LIST.deserialize(s);
    }

    public static Map<String,String> stringToMap(String s) {
        return DESERIALIZER_MAP.deserialize(s);
    }

    public static String mapToString(Map<String,String> m) {
        return SERIALIZER.serialize(m);
    }

    public static String listToString(List<Map<String,String>> lst) {
        return SERIALIZER.serialize(lst);
    }

    public static String jsonString(String ... args) {
        Map<String,String> m = new HashMap<String,String>();
        for(int i=0; i<args.length; i+=2) {
            m.put(args[i], args[i+1]);
        }
        return SERIALIZER.serialize(m);
    }

    public static Map<String,String> jsonMap(String ... args) {
        Map<String,String> m = new HashMap<String,String>();
        for(int i=0; i<args.length; i+=2) {
            m.put(args[i], args[i+1]);
        }
        return m;
    }
}
