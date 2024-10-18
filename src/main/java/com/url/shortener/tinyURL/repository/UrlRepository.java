package com.url.shortener.tinyURL.repository;

import com.url.shortener.tinyURL.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, String> {
    @Query()
    Optional<Url> findByShortUrl(String shortUrl);

    Optional<Url> findByOriginalUrl(String originalUrl);
}
