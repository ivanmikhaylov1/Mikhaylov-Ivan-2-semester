package org.example.mikhaylovivan2semester.dto;

public record Response<T>(int statusCode, T data, String message) {
  public Response(int statusCode, String message) {
    this(statusCode, null, message);
  }

  public Response(T data) {
    this(200, data, "Успешно");
  }
}
