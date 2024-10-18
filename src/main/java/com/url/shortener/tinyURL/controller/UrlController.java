package com.url.shortener.tinyURL.controller;

import com.url.shortener.tinyURL.request.UrlRequest;
import com.url.shortener.tinyURL.service.UrlService;
import com.url.shortener.tinyURL.service.impl.UrlServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody UrlRequest request) {
        String shortUrl = urlService.shortenUrl(request.getOriginalUrl());
        return ResponseEntity.ok("http://localhost:8080/" + shortUrl);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
        Optional<String> originalUrl = urlService.getOriginalUrl(shortUrl);
        if (originalUrl.isPresent()) {
            response.sendRedirect(originalUrl.get());
            return ResponseEntity.status(HttpStatus.FOUND).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
