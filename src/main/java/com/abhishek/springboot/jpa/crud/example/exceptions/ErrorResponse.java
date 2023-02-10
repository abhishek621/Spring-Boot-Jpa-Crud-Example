package com.abhishek.springboot.jpa.crud.example.exceptions;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

  private String errorCode;
  private String errorMessage;
  private Date timeStamp;

  
}
