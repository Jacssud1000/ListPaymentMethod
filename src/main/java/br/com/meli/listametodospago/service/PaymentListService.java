package br.com.meli.listametodospago.service;

import br.com.meli.listametodospago.dtos.PaymentDto;
import br.com.meli.listametodospago.dtos.ResponsePaymentDto;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.client.paymentmethod.PaymentMethodClient;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class PaymentListService {
  public ResponsePaymentDto payment(@Valid PaymentDto paymentDto) {
    try {
      MercadoPagoConfig.setAccessToken("APP_USR-5879859661431869-050816-63fe2574c50c3369bae624b038a5c0c4-1259153998");

      PaymentMethodClient client = new PaymentMethodClient();

      client.list();

      PaymentCreateRequest paymentCreateRequest = PaymentCreateRequest.builder()
          .transactionAmount(paymentDto.getTransaction_amount())
          .description(paymentDto.getDescription())
          .paymentMethodId(paymentDto.getPayment_method_id())
          .payer(PaymentPayerRequest.builder()
              .email(paymentDto.getPayerDto().getEmail())
              .firstName(paymentDto.getPayerDto().getFirst_name())
              .lastName(paymentDto.getPayerDto().getLast_name())
              .identification(
                  IdentificationRequest.builder()
                      .type(paymentDto.getPayerDto().getIdentification().getType())
                      .number(paymentDto.getPayerDto().getIdentification().getNumber())
                      .build())
              .build())
          .build();

      Map<String, String> customHeaders = new HashMap<>();
      customHeaders.put("x-idempotency-key", UUID.randomUUID().toString());

      MPRequestOptions requestOptions = MPRequestOptions.builder()
          .accessToken("APP_USR-5879859661431869-050816-63fe2574c50c3369bae624b038a5c0c4-1259153998")
          .customHeaders(customHeaders).build();

      PaymentClient clients = new PaymentClient();

      Payment payment = clients.create(paymentCreateRequest, requestOptions);

      String external_resource_url = payment.getTransactionDetails().getExternalResourceUrl();

      return new ResponsePaymentDto(
          payment.getId(),
          payment.getStatus(),
          payment.getStatusDetail(),
          external_resource_url
      );


    } catch (MPException | MPApiException e) {
      throw new RuntimeException(e);
    }
  }
}
