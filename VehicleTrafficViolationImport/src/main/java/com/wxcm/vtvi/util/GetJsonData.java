package com.wxcm.vtvi.util;

import net.sf.json.JSONObject;
import org.springframework.util.DigestUtils;

/**
 * @author GZH
 * @date 2020-12-28
 */
public class GetJsonData {

    public static String getJsonData(String token, JSONObject jsonObject) throws Exception {
        //unixTimestamp 也可使用new Date().getTime()
        long unixTimestamp = System.currentTimeMillis();
        String uuid = UuidUtil.getUuid32();
        JSONObject object = new JSONObject();
        object.element("token", token);
        object.element("timestamp", unixTimestamp);
        object.element("nonce", uuid);
        object.element("appKey", Constants.APP_KEY);
        object.element("sign", DigestUtils.md5DigestAsHex(("token=" + token + "&timestamp=" + unixTimestamp +
                "&nonce=" + uuid + "&appKey=" + Constants.APP_KEY).getBytes()).toUpperCase());
        object.putAll(jsonObject);
        return Des3.encode(object.toString(), Constants.APP_KEY);
    }

}
