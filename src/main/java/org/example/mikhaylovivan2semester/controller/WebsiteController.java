package org.example.mikhaylovivan2semester.controller;

import org.example.mikhaylovivan2semester.entity.Website;
import org.example.mikhaylovivan2semester.service.WebsiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/websites")
public class WebsiteController {
    private static final Logger logger = LoggerFactory.getLogger(WebsiteController.class);
    private final WebsiteService websiteService = new WebsiteService();

    @GetMapping("/basic")
    public List<Website> getBasicWebsites() {
        logger.info("Получен запрос на получение базовых сайтов");
        return websiteService.getBasicWebsites();
    }

    @GetMapping("/user/{userId}")
    public List<Website> getUserWebsites(@PathVariable UUID userId) {
        logger.info("Получен запрос на получение сайтов пользователя с ID: {}", userId);
        return websiteService.getUserWebsites(userId);
    }

    @PostMapping("/user/{userId}")
    public Website addUserWebsite(@PathVariable UUID userId, @RequestParam String name, @RequestParam String url) {
        logger.info("Получен запрос на добавление сайта '{}' для пользователя с ID: {}", name, userId);
        return websiteService.addUserWebsite(userId, name, url);
    }

    @DeleteMapping("/user/{userId}")
    public void deleteByName(@PathVariable UUID userId, @RequestParam String name) {
        logger.info("Получен запрос на удаление сайта '{}' у пользователя с ID: {}", name, userId);
        websiteService.deleteByName(userId, name);
    }

    @GetMapping("/exists")
    public boolean existsByName(@RequestParam String name) {
        logger.info("Получен запрос на проверку существования сайта с именем: {}", name);
        return websiteService.existsByName(name);
    }

    @GetMapping("/user/{userId}/by-name")
    public Optional<Website> getByName(@PathVariable UUID userId, @RequestParam String name) {
        logger.info("Получен запрос на получение сайта '{}' для пользователя с ID: {}", name, userId);
        return websiteService.getByName(userId, name);
    }
}
