package org.example.mikhaylovivan2semester.entity;

public record Response<T>(int statusCode, T data, String message) {
  public Response(int statusCode, String message) {
    this(statusCode, null, message);
  }

  public Response(T data) {
    this(200, data, "Успешно");
  }

  public Response(int statusCode) {
    this(statusCode, null, "");
  }

  public boolean isSuccess() {
    return statusCode == 200;
  }
}
