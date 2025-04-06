package org.example.mikhaylovivan2semester.exception;

public class CatalogOperationException extends RuntimeException {

  public CatalogOperationException(String message) {
    super(message);
  }

  public CatalogOperationException(String message, Throwable cause) {
    super(message, cause);
  }
}
