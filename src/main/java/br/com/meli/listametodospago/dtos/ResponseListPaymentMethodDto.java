package br.com.meli.listametodospago.dtos;

import java.util.List;
import lombok.Data;

@Data
public class ResponseListPaymentMethodDto {
private List<PaymentMethodDto> paymentMethods;
}
