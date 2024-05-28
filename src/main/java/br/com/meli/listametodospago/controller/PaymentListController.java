package br.com.meli.listametodospago.controller;

import br.com.meli.listametodospago.service.ListPaymentMethodService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:63342")
@RequestMapping()
public class PaymentListController {

  @Autowired
  private ListPaymentMethodService listPaymentMethodService;

  @GetMapping("/v1/payment_methods")
  ResponseEntity<String> getListPaymentMethod() throws JSONException {
    String payment = String.valueOf(listPaymentMethodService.getPaymentMethods());
    return ResponseEntity.status(HttpStatus.OK).body(payment);
  }
}
