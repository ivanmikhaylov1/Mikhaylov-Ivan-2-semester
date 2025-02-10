package org.example.mikhaylovivan2semester.controller;

import org.example.mikhaylovivan2semester.entity.User;
import org.example.mikhaylovivan2semester.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService = new UserService();

    @GetMapping
    public List<User> findAll() {
        logger.info("Получен запрос на получение всех пользователей");
        return userService.findAll();
    }

    @PostMapping
    public User save(@RequestParam String name, @RequestParam String password) {
        logger.info("Получен запрос на сохранение пользователя: {}", name);
        return userService.save(name, password).orElse(null);
    }

    @GetMapping("/{userId}")
    public Optional<User> getById(@PathVariable UUID userId) {
        logger.info("Получен запрос на получение пользователя по ID: {}", userId);
        return userService.getById(userId).orElse(null);
    }

    @GetMapping("/by-name")
    public Optional<User> findByName(@RequestParam String name) {
        logger.info("Получен запрос на получение пользователя по имени: {}", name);
        return userService.findByName(name).orElse(null);
    }

    @GetMapping("/exists")
    public boolean exists(@RequestParam String name) {
        logger.info("Получен запрос на проверку существования пользователя с именем: {}", name);
        return userService.exists(name);
    }
}
