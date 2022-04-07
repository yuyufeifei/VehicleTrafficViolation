package com.wxcm.vtvi.util;

import java.util.UUID;

/**
 * @author GZH
 * @date 2020-12-28
 */
public class UuidUtil {
    public static String getUuid32() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println(new java.util.Date().getTime());
    }
}
