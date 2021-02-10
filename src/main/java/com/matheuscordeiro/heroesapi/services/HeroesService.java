package com.matheuscordeiro.heroesapi.services;

import com.matheuscordeiro.heroesapi.models.Heroes;
import com.matheuscordeiro.heroesapi.repositories.HeroesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HeroesService {
    @Autowired
    HeroesRepository heroesRepository;

    public Flux<Heroes> findAll(){
        return Flux.fromIterable(this.heroesRepository.findAll());
    }

    public Mono<Heroes> findByIdHero(String id){

        return  Mono.justOrEmpty(this.heroesRepository.findById(id));
    }

    public Mono<Heroes> save(Heroes heroes){
        return  Mono.justOrEmpty(this.heroesRepository.save(heroes));
    }


    public Mono<Boolean> deleteByIdHero(String id) {
        heroesRepository.deleteById(id);
        return Mono.just(true);
    }
}
