# from-paris-to-berlin-kystrix

This project is an investigation of Hystrix in the Kotlin language

## Introduction

The idea of this project is to take passengers from Paris to Berlin.	However, in our simulation, the weather is bad and so the lines will be failing.	The role of kystrix here is to avoid a massive traffic flow to blocked roads and instead to get the cars somewhere else.

The idea of Kystrix is the same as Hystrix. These are circuit-breakers responsible to stop the flow of requests to certain endpoints and make them fail or redirect to somewhere else.

## References

- [Kystrix – A Kotlin DSL for Hystrix](http://code.haleby.se/2018/09/16/kystrix-a-kotlin-dsl-for-hystrix/)
- [johanhaleby / kystrix](https://github.com/johanhaleby/kystrix)
- [resilience4j / resilience4j](https://github.com/resilience4j/resilience4j)
- [CircuitBreaker - Getting started with resilience4j-circuitbreaker](https://resilience4j.readme.io/docs/circuitbreaker)