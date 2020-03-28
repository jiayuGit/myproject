package com.example.demo.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Map;

public class Check {
    public static boolean NuNObj(Object obj) {
        if (obj instanceof String) {
            return NuNStr((String)obj);
        } else {
            return obj == null;
        }
    }

    public static boolean NuNObjs(Object... objs) {
        Object[] var1 = objs;
        int var2 = objs.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Object obj = var1[var3];
            boolean nun = NuNObj(obj);
            if (nun) {
                return true;
            }
        }

        return false;
    }

    public static boolean NuNObject(Object[] objs) {
        if (objs != null && objs.length != 0) {
            Object[] var1 = objs;
            int var2 = objs.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                Object obj = var1[var3];
                boolean nun = NuNObj(obj);
                if (nun) {
                    return true;
                }
            }

            return false;
        } else {
            return true;
        }
    }

    public static boolean NuNStr(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean NuNStrStrict(String str) {
        return NuNStr(str) || "null".equalsIgnoreCase(str);
    }

    public static boolean NuNCollection(Collection<?> colls) {
        return colls == null || colls.isEmpty();
    }

    public static boolean NuNMap(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static String encodeUrl(String params) {
        return encodeUrl(params, "UTF-8");
    }

    public static String encodeUrl(String params, String encode) {
        try {
            return URLEncoder.encode(params, encode);
        } catch (UnsupportedEncodingException var3) {
            return encodeUrl(params, "UTF-8");
        }
    }

    private Check() {
        throw new AssertionError("Uninstantiable class");
    }
}
