package br.com.meli.listametodospago.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Service
public class ListPaymentMethodService {
  private static final String API_URL = "https://api.mercadopago.com/v1/payment_methods";
  private static final String AUTH_TOKEN = "APP_USR-5879859661431869-050816-63fe2574c50c3369bae624b038a5c0c4-1259153998";

  public ResponseEntity<String> getPaymentMethods() throws JSONException {
    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", "application/json");
    headers.set("Authorization", "Bearer " + AUTH_TOKEN);

    HttpEntity<String> entity = new HttpEntity<>(headers);

    ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.GET, entity, String.class);

    JSONArray resultArray = getJsonArray(response.getBody());

    return ResponseEntity.status(HttpStatus.OK).body(resultArray.toString());
  }

  private static JSONArray getJsonArray(String responseBody) throws JSONException {
    JSONArray jsonArray = new JSONArray(responseBody);
    JSONArray resultArray = new JSONArray();
    for (int i = 0; i < jsonArray.length(); i++) {
      JSONObject jsonObject = jsonArray.getJSONObject(i);
      JSONObject resultObject = new JSONObject();
      resultObject.put("id", jsonObject.getString("id"));
      resultObject.put("name", jsonObject.getString("name"));
      resultObject.put("payment_method_id", jsonObject.getString("payment_type_id"));
      resultArray.put(resultObject);
    }
    return resultArray;
  }
}
