package com.example.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

@RestController
@RequestMapping("/redis")
public class RedisController {

    private static final Logger logger = LoggerFactory.getLogger(RedisController.class);
    private final RedisService redisService;

    @Autowired
    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> save(
            @RequestParam String key,
            @RequestParam String value,
            @RequestParam(defaultValue = "3600") long ttl) {
        try {
            redisService.saveValue(key, value, ttl);
            return ResponseEntity.ok(Map.of(
                "code", "0000",
                "message", "Key saved successfully"));
        } catch (Exception e) {
            logger.error("Redis operation failed", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                    "code", "9999",
                    "message", "Redis operation failed"));
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam String key) {
        try {
            Object val = redisService.getValue(key);
            if (val == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                        "code", "1000",
                        "message", "Key not found"));
            }
            return ResponseEntity.ok(Map.of(
                "code", "0000",
                "value", val.toString()));
        } catch (Exception e) {
            logger.error("Redis operation failed", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                    "code", "9999",
                    "message", "Redis operation failed"));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, String>> delete(@RequestParam String key) {
        try {
            redisService.deleteValue(key);
            return ResponseEntity.ok(Map.of(
                "code", "0000",
                "message", "Key deleted successfully"));
        } catch (Exception e) {
            logger.error("Redis operation failed", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                    "code", "9999",
                    "message", "Redis operation failed"));
        }
    }
}
