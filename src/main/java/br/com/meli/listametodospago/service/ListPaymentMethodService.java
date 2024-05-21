package br.com.meli.listametodospago.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Service
public class ListPaymentMethodService {
  private static final String API_URL = "https://api.mercadopago.com/v1/payment_methods";
  private static final String AUTH_TOKEN = "APP_USR-5879859661431869-050816-63fe2574c50c3369bae624b038a5c0c4-1259153998";

  public String getPaymentMethods() {
    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", "application/json");
    headers.set("Authorization", "Bearer " + AUTH_TOKEN);

    HttpEntity<String> entity = new HttpEntity<>(headers);

    ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.GET, entity, String.class);

    return response.getBody();
    /*ObjectMapper objectMapper = new ObjectMapper();
    List<PaymentMethodDto> paymentMethods;

    try {
      paymentMethods = objectMapper.readValue(response.getBody(), new TypeReference<List<PaymentMethodDto>>(){});

    } catch (Exception e) {
      throw new RuntimeException("Error parsing payment methods", e);
    }

    ResponseListPaymentMethodDto responseDto = new ResponseListPaymentMethodDto();
    responseDto.setPaymentMethods(paymentMethods);
    return responseDto;*/
  }
}




