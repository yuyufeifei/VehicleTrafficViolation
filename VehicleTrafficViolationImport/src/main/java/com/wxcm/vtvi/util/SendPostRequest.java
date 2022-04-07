package com.wxcm.vtvi.util;

import net.sf.json.JSONObject;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author GZH
 */
public class SendPostRequest {

    public static JSONObject sendPostRequest (String url, Map<String, Object> map) throws Exception {
        JSONObject response = new JSONObject();
        RestTemplate restTemplate = new RestTemplate();
        new HttpHeaders().setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(map);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        HttpStatus statusCode = responseEntity.getStatusCode();
        if (statusCode == HttpStatus.OK) {
            response = JSONObject.fromObject(responseEntity.getBody());
        }
        return response;
    }

    public static JSONObject sendPostRequest (String url, MultiValueMap<String, Object> map) throws Exception {
        JSONObject response = new JSONObject();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(map, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);
        HttpStatus statusCode = responseEntity.getStatusCode();
        if (statusCode == HttpStatus.OK) {
            System.out.println(Des3.decode(responseEntity.getBody(), Constants.APP_KEY));
            response = JSONObject.fromObject(Des3.decode(responseEntity.getBody(), Constants.APP_KEY));
        }
        return response;
    }

}
