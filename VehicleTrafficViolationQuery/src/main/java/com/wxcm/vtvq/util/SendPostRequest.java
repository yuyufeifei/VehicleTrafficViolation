package com.wxcm.vtvq.util;

import net.sf.json.JSONObject;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author GZH
 */
public class SendPostRequest {
    public static JSONObject sendPostRequest (String url, MultiValueMap<String, Object> map) {
        JSONObject response = new JSONObject();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(map, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);
        HttpStatus statusCode = responseEntity.getStatusCode();
        if (statusCode == HttpStatus.OK) {
            response = JSONObject.fromObject(responseEntity.getBody());
        }
        return response;
    }
}
