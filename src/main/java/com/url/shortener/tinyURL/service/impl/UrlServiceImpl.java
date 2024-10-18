package com.url.shortener.tinyURL.service.impl;

import com.url.shortener.tinyURL.model.Url;
import com.url.shortener.tinyURL.repository.UrlRepository;
import com.url.shortener.tinyURL.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public String shortenUrl(String originalUrl) {
        // Check if the original URL already exists in database
        Optional<Url> existingUrl = urlRepository.findByOriginalUrl(originalUrl);
        if (existingUrl.isPresent()) {
            return existingUrl.get().getShortUrl();
        }

        //Generate new short URL if doesn't exist
        String shortUrl = generateShortUrl();
        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortUrl(shortUrl);
        urlRepository.save(url);
        return shortUrl;
    }

    public Optional<String> getOriginalUrl(String shortUrl) {
        System.out.println("Retrieving URL for shortUrl: " + shortUrl);

        Optional<String> originalUrl = urlRepository.findByShortUrl(shortUrl)
                .map(Url::getOriginalUrl);
        System.out.println("original url: " + originalUrl.get());
        return originalUrl;
    }

    private String generateShortUrl() {
        // Generate a random alphanumeric string (e.g., 7 characters)
        return RandomStringUtils.randomAlphanumeric(7);
    }
}
