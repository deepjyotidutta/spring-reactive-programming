package com.learn.reactivespring.fluxandmonoplayground;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoTest {
    @Test
    public void fluxTest() {
        System.out.println("Hello");
        Flux myflux = Flux.just("hello","hi","how are you")
                .concatWith(Flux.error(new RuntimeException("Runtime exception occurred")))
                .log();

        myflux.subscribe(System.out::println,(e)->System.err.println("Exception is "+e));
    }
    @Test
    public void fluxTestElements_withouterror(){
        Flux myflux = Flux.just("hello","hi","how are you")
                .log();
        StepVerifier.create(myflux)
                .expectNext("hello")
                .expectNext("hi")
                .expectNext("how are you")
                .verifyComplete();
    }

    @Test
    public void fluxTestElements_witherror(){
        Flux myflux = Flux.just("hello","hi","how are you")
                .concatWith(Flux.error(new RuntimeException("Runtime exception occurred")))
                .log();
        StepVerifier.create(myflux)
                .expectNext("hello")
                .expectNext("hi")
                .expectNext("how are you")
                .expectError(RuntimeException.class)
                //.expectErrorMessage("Runtime exception occurred")
                .verify();
    }

    @Test
    public void fluxTestElementsCount_witherror(){
        Flux myflux = Flux.just("hello","hi","how are you")
                .concatWith(Flux.error(new RuntimeException("Runtime exception occurred")))
                .log();
        StepVerifier.create(myflux)
                .expectNextCount(3)
                .expectError(RuntimeException.class)
                //.expectErrorMessage("Runtime exception occurred")
                .verify();
    }
}
