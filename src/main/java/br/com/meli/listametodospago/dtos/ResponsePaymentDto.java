package br.com.meli.listametodospago.dtos;

import lombok.Data;

@Data
public class ResponsePaymentDto {
  private Long id;
  private String status;
  private String detail;

  public ResponsePaymentDto(Long id, String status, String detail) {
    this.id = id;
    this.status = status;
    this.detail = detail;
  }
}
