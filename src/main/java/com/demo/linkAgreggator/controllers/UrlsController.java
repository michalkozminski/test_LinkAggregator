package com.demo.linkAgreggator.controllers;

import com.demo.linkAgreggator.model.SavedWebsite;
import com.demo.linkAgreggator.repository.SavedWebsiteRepository;
import org.jsoup.Jsoup;
import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@RestController
public class UrlsController {
    private final SavedWebsiteRepository repository;

    public UrlsController(SavedWebsiteRepository repositor) {
        this.repository = repositor;
    }

    @GetMapping("/urls")
    Flux<String> getAll() {
        return this.repository
                .findAll()
                .map( u -> u.url)
                .map( url -> WebClient.create(url).get().retrieve() )
                .flatMap(res -> res.bodyToMono(String.class))
                .map(s -> Jsoup.parse(s).title());
    }

    @PostMapping("/urls")
    Mono<Void> create(@RequestBody Publisher<SavedWebsite> personStream) {
        return this.repository.saveAll(personStream).then();
    }

}
