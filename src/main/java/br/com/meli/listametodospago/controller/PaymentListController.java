package br.com.meli.listametodospago.controller;

import br.com.meli.listametodospago.dtos.PaymentDto;
import br.com.meli.listametodospago.dtos.ResponseListPaymentMethodDto;
import br.com.meli.listametodospago.dtos.ResponsePaymentDto;
import br.com.meli.listametodospago.service.ListPaymentMethodService;
import br.com.meli.listametodospago.service.PaymentListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:63342")
@RequestMapping()
public class PaymentListController {

  @Autowired
  private PaymentListService paymentListService;
  @Autowired
  private ListPaymentMethodService listPaymentMethodService;

  @PostMapping(path = "/process_payment", consumes = {"text/plain; charset=UTF-8", "application/*; charset=UTF-8"})
  ResponseEntity<ResponsePaymentDto> payment(@Valid @RequestBody PaymentDto paymentDto) {
    ResponsePaymentDto paymentRequest = paymentListService.payment(paymentDto);
    return ResponseEntity.status(HttpStatus.OK).body(paymentRequest);
  }

  @GetMapping("/v1/payment_methods")
  ResponseEntity<String> getListPaymentMethod(){
    String payment = listPaymentMethodService.getPaymentMethods();
    return ResponseEntity.status(HttpStatus.OK).body(payment);
  }
}
