package br.com.meli.listametodospago.controller;

import br.com.meli.listametodospago.dtos.PaymentDto;
import br.com.meli.listametodospago.dtos.ResponsePaymentDto;
import br.com.meli.listametodospago.service.PaymentListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/process_payment")
public class PaymentListController {

  @Autowired
  private PaymentListService paymentListService;

  @PostMapping()
  ResponseEntity<ResponsePaymentDto> payment(@Valid @RequestBody PaymentDto paymentDto) {
    ResponsePaymentDto paymentRequest = paymentListService.payment(paymentDto);
    return ResponseEntity.status(HttpStatus.OK).body(paymentRequest);
  }
}
