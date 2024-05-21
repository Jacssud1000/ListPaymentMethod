package br.com.meli.listametodospago.dtos;

import lombok.Data;

@Data
public class PaymentMethodDto {
  private String id;
  private String name;
  private String payment_type_id;
  private String status;
  private String secure_thumbnail;
  private String thumbnail;
  private String deferred_capture;

}
