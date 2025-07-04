package com.example.demo.elk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ElkController {

	@Autowired
    private ELKService elkService;

    @GetMapping("/test-elk")
    public String testElkLogs() {
        elkService.doSomething();
        return "Logs sent to ELK!";
    }
}