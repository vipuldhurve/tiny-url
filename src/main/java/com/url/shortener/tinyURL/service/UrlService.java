package com.url.shortener.tinyURL.service;

import com.url.shortener.tinyURL.model.Url;

import java.util.Optional;

public interface UrlService {

    String shortenUrl(String originalUrl);

    Optional<String> getOriginalUrl(String shortUrl);
}
