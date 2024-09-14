# from-paris-to-berlin-circuit-breaker

---


[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=From%20Paris%20to%20Berlin%20????&color=informational)](https://github.com/jesperancinha/from-paris-to-berlin-circuit-breaker)

[![CircleCI](https://circleci.com/gh/jesperancinha/from-paris-to-berlin-circuit-breaker.svg?style=svg)](https://circleci.com/gh/jesperancinha/from-paris-to-berlin-circuit-breaker)
[![from-paris-to-berlin-circuit-breaker](https://github.com/jesperancinha/from-paris-to-berlin-circuit-breaker/actions/workflows/from-paris-to-berlin-circuit-breaker.yml/badge.svg)](https://github.com/jesperancinha/from-paris-to-berlin-circuit-breaker/actions/workflows/from-paris-to-berlin-circuit-breaker.yml)
[![e2e-from-paris-to-berlin-circuit-breaker](https://github.com/jesperancinha/from-paris-to-berlin-circuit-breaker/actions/workflows/from-paris-to-berlin-circuit-breaker-e2e.yml/badge.svg)](https://github.com/jesperancinha/from-paris-to-berlin-circuit-breaker/actions/workflows/from-paris-to-berlin-circuit-breaker-e2e.yml)

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/db670c80127c412d95d74ec2a10145ff)](https://www.codacy.com/gh/jesperancinha/from-paris-to-berlin-circuit-breaker/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/from-paris-to-berlin-circuit-breaker&amp;utm_campaign=Badge_Grade)
[![codebeat badge](https://codebeat.co/badges/43d5e2ce-3805-4ab3-9804-0d68b5ad8d24)](https://codebeat.co/projects/github-com-jesperancinha-from-paris-to-berlin-circuit-breaker-main)
[![Known Vulnerabilities](https://snyk.io/test/github/jesperancinha/from-paris-to-berlin-circuit-breaker/badge.svg)](https://snyk.io/test/github/jesperancinha/from-paris-to-berlin-circuit-breaker)

[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/db670c80127c412d95d74ec2a10145ff)](https://www.codacy.com/gh/jesperancinha/from-paris-to-berlin-circuit-breaker/dashboard?utm_source=github.com&utm_medium=referral&utm_content=jesperancinha/from-paris-to-berlin-circuit-breaker&utm_campaign=Badge_Coverage)
[![codecov](https://codecov.io/gh/jesperancinha/from-paris-to-berlin-circuit-breaker/branch/main/graph/badge.svg?token=M7ZOccsNUq)](https://codecov.io/gh/jesperancinha/from-paris-to-berlin-circuit-breaker)
[![Coverage Status](https://coveralls.io/repos/github/jesperancinha/from-paris-to-berlin-circuit-breaker/badge.svg?branch=main)](https://coveralls.io/github/jesperancinha/from-paris-to-berlin-circuit-breaker?branch=main)

[![GitHub language count](https://img.shields.io/github/languages/count/jesperancinha/from-paris-to-berlin-circuit-breaker.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/top/jesperancinha/from-paris-to-berlin-circuit-breaker.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/code-size/jesperancinha/from-paris-to-berlin-circuit-breaker.svg)](#)

---

## Technologies used

Please check the [TechStack.md](TechStack.md) file for details.

## Introduction

The idea of this project is to take passengers from Paris to Berlin.	However, in our simulation, the weather is bad and so the lines will be failing.	The role of kystrix here is to avoid a massive traffic flow to blocked roads and instead to get the cars somewhere else.

The idea of [Kystrix](https://github.com/johanhaleby/kystrix) is the same as [Hystrix](https://github.com/Netflix/Hystrix). These are circuit-breakers responsible to stop the flow of requests to certain endpoints and make them fail or redirect to somewhere else.

Hystrix is, however, no longer in development, and instead we have [Resilience4J](https://github.com/resilience4j/resilience4j).

This means that although we can hope that [Kystrix](https://github.com/johanhaleby/kystrix) gets more standardized, it appears to be that [Resilience4J](https://github.com/resilience4j/resilience4j) is the way to go at this time.

#### Stable releases

-   [0.0.0](https://github.com/jesperancinha/from-paris-to-berlin-circuit-breaker/tree/0.0.0) - [bc013410e0f9db81b84979746cbb88fe32bdb5cf](https://github.com/jesperancinha/from-paris-to-berlin-circuit-breaker/tree/0.0.0) - Docker with IP's / Kotlin 1.8 / JDK17
	
## Project Layout

1.  [From Paris to Berlin Data](./from-paris-to-berlin-data) - Common Data Library used in all executable projects
2.  [From Paris to Brlin City Generator](./from-paris-to-berlin-city-generator) - Generates a City JSON which can be fed to the main application
3.  [From Paris to Berlin Kystrix Demo](./from-paris-to-berlin-kystrix-runnable-app) - A simplified Kystrix demo independently of Spring
4.  [From Paris to Berlin Resilience4J Runnable Demo](./from-paris-to-berlin-resilience4j-runnable-app) - Creating Circuit Breakers using programmatic Resilience4J independently of Spring
5.  [From Paris to Berlin Resilience4J Spring Demo](./from-paris-to-berlin-resilience4j-spring-app) - Creating Circuit Breakers using programmatic Resilience4J using Spring
6.  [From Paris to Berlin Resilience4J AOP Spring Demo](./from-paris-to-berlin-resilience4j-aop-spring-app) - Creating Circuit Breakers using AOP(Aspect Oriented Programming) and declarative Resilience4J
7.  [From Paris to Berlin Web](./from-paris-to-berlin-web) - Front end application to support the Paris to Berlin Game

## Java Setup

```shell
sdk install java 17-open
sdk use java 17-open
```

## How to run locally

##### Docker-Compose

1.  Start Image

```shell
make dcup-full-action
```

2.  Start Demo

```shell
make demo-docker
```

3.  Go to [localhost:9000](http://localhost:9000)

---

## Swagger UI


##### Local

1.  [from-paris-to-berlin-resilience4j-aop-spring-app](http://localhost:8080/api/fptb/webjars/swagger-ui/index.html)
2.  [from-paris-to-berlin-ws-service](http://localhost:8081/api/fptb/swagger-ui/index.html#/)

##### Via Docker

1.  [from-paris-to-berlin-resilience4j-aop-spring-app](http://localhost:9000/api/fptb/webjars/swagger-ui/index.html)
2.  [from-paris-to-berlin-ws-service](http://localhost:9000/api/fptb/broker/ws/swagger-ui/index.html)

## Coverage report Graphs

<div align="center">
<img width="30%" src="https://codecov.io/gh/jesperancinha/from-paris-to-berlin-circuit-breaker/branch/main/graphs/sunburst.svg"/>
<img width="30%" src="https://codecov.io/gh/jesperancinha/from-paris-to-berlin-circuit-breaker/branch/main/graphs/tree.svg"/>
</div>
<div align="center">
<img width="60%" src="https://codecov.io/gh/jesperancinha/from-paris-to-berlin-circuit-breaker/branch/main/graphs/icicle.svg"/>
</div>

## References

-   [Improve your Kotlin code with Detekt](https://medium.com/frichtitech/improve-your-kotlin-code-with-detekt-65795656b1ed)
-   [Resilience 4J Spring-Boot-Demo](https://github.com/resilience4j/resilience4j-spring-boot2-demo)
-   [Netflix / Hystrix](https://github.com/Netflix/Hystrix)
-   [Kystrix – A Kotlin DSL for Hystrix](http://code.haleby.se/2018/09/16/kystrix-a-kotlin-dsl-for-hystrix/)
-   [johanhaleby / kystrix](https://github.com/johanhaleby/kystrix)
-   [resilience4j / resilience4j](https://github.com/resilience4j/resilience4j)
-   [CircuitBreaker - Getting started with resilience4j-circuitbreaker](https://resilience4j.readme.io/docs/circuitbreaker)

## About me

[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)
