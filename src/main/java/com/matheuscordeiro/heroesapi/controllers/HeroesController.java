package com.matheuscordeiro.heroesapi.controllers;

import com.matheuscordeiro.heroesapi.models.Heroes;
import com.matheuscordeiro.heroesapi.services.HeroesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.matheuscordeiro.heroesapi.constants.HeroesConstant.*;

@RestController(value = HEROES_ENDPOINT_LOCAL)
@Slf4j
public class HeroesController {
    @Autowired
    HeroesService heroesService;

    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(HeroesController.class);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Heroes> getAllItems() {
        log.info("requesting the list off all heroes");
        return heroesService.findAll();

    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Heroes>> findByIdHero(@PathVariable String id) {
        log.info("Requesting the hero with id {}", id);
        return heroesService.findByIdHero(id)
                .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Heroes> createHero(@RequestBody Heroes heroes) {
        log.info("A new Hero was Created");
        return heroesService.save(heroes);

    }

    @DeleteMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<HttpStatus> deleteByIdHero(@PathVariable String id) {
        heroesService.deleteByIdHero(id);
        log.info("Deleting the hero with id {}", id);
        return Mono.just(HttpStatus.NO_CONTENT);
    }
}
