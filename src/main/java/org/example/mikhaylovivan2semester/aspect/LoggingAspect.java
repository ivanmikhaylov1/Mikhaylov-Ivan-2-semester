package org.example.mikhaylovivan2semester.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

  @Pointcut("within(org.example.mikhaylovivan2semester.controller..*)")
  public void controllerMethods() {
  }

  @Pointcut("within(org.example.mikhaylovivan2semester.repository.implementations..*)")
  public void repositoryMethods() {
  }

  @Pointcut("within(org.example.mikhaylovivan2semester.service.implementations..*)")
  public void serviceMethods() {
  }

  @Around("controllerMethods()")
  public Object logController(ProceedingJoinPoint joinPoint) throws Throwable {
    String methodName = joinPoint.getSignature().getName();
    try {
      Object result = joinPoint.proceed();
      log.info("[Контроллер] Метод {} выполнен успешно", methodName);
      return result;
    } catch (Exception e) {
      log.error("[Контроллер] Ошибка при выполнении метода {}: {}", methodName, e.getMessage());
      throw e;
    }
  }

  @Around("serviceMethods()")
  public Object logService(ProceedingJoinPoint joinPoint) throws Throwable {
    String methodName = joinPoint.getSignature().getName();
    try {
      Object result = joinPoint.proceed();
      log.info("[Сервис] Метод {} выполнен успешно", methodName);
      return result;
    } catch (Exception e) {
      log.error("[Сервис] Ошибка при выполнении метода {}: {}", methodName, e.getMessage());
      throw e;
    }
  }

  @Around("repositoryMethods()")
  public Object logRepository(ProceedingJoinPoint joinPoint) throws Throwable {
    String methodName = joinPoint.getSignature().getName();
    try {
      Object result = joinPoint.proceed();
      log.info("[Репозиторий] Метод {} выполнен успешно", methodName);
      return result;
    } catch (Exception e) {
      log.error("[Репозиторий] Ошибка при выполнении метода {}: {}", methodName, e.getMessage());
      throw e;
    }
  }

  @Before("controllerMethods()")
  public void logMethodName(JoinPoint joinPoint) {
    System.out.println("Вызов метода: " + joinPoint.getSignature().getName());
  }

  @Around("controllerMethods()")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    Instant start = Instant.now();
    Object result = joinPoint.proceed();
    Instant end = Instant.now();
    System.out.println("Время выполнения метода " + joinPoint.getSignature().getName() + ": " +
        Duration.between(start, end).toMillis() + " мс");
    return result;
  }
}
