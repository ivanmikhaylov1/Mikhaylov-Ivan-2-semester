package org.example.mikhaylovivan2semester.entity;

public record Request<T>(T data, String message) {

  public Request(T data) {
    this(data, "");
  }

  public Request(String message) {
    this(null, message);
  }

  public boolean hasData() {
    return data != null;
  }

  public boolean hasMessage() {
    return !message.isEmpty();
  }
}
