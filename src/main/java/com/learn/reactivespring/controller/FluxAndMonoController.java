package com.learn.reactivespring.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class FluxAndMonoController {

    @GetMapping("/flux")
    public Flux<Integer> returnFlux(){
        return Flux.just(1,2,3,4).log();
    }

    @Deprecated
    @GetMapping(value = "/fluxstream/old",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Integer> returnFluxStreamOld(){
        return Flux.just(1,2,3,4).delayElements(Duration.ofSeconds(1)).log();
    }

    @GetMapping(value = "/fluxstream",produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Integer> returnFluxStream(){
        return Flux.just(1,2,3,4).delayElements(Duration.ofSeconds(1)).log();
    }
}
