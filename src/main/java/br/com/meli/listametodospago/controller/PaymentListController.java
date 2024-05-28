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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONException;

@RestController
@CrossOrigin("http://localhost:63342/")
@RequestMapping()
public class PaymentListController {

  @Autowired
  private ListPaymentMethodService listPaymentMethodService;

  @GetMapping("/v1/payment_methods")
  public ResponseEntity<String> getListPaymentMethod() {
    try {
      return listPaymentMethodService.getPaymentMethods();
    } catch (JSONException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing JSON response");
    }
  }
}
