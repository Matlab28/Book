package com.example.book.controller;

import com.example.book.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {
    private final EmailService service;

    @PostMapping("/send")
    public String sendEmail(@RequestParam String sub, @RequestParam String text) {
        return service.sendEmail(sub,text);
    }
}
