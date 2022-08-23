# from-paris-to-berlin-circuit-breaker

---

[![Twitter URL](https://img.shields.io/twitter/url?logoColor=blue&style=social&url=https%3A%2F%2Fimg.shields.io%2Ftwitter%2Furl%3Fstyle%3Dsocial)](https://twitter.com/intent/tweet?text=Checkout%20this%20@bitbucket%20repo%20by%20@joaofse%20%F0%9F%91%A8%F0%9F%8F%BD%E2%80%8D%F0%9F%92%BB:%20https://github.com/jesperancinha/from-paris-to-berlin-circuit-breaker)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=From%20Paris%20to%20Berlin%20????&color=informational)](https://github.com/jesperancinha/from-paris-to-berlin-circuit-breaker)

[![CircleCI](https://circleci.com/gh/jesperancinha/from-paris-to-berlin-circuit-breaker.svg?style=svg)](https://circleci.com/gh/jesperancinha/from-paris-to-berlin-circuit-breaker)
[![from-paris-to-berlin-circuit-breaker](https://github.com/jesperancinha/from-paris-to-berlin-circuit-breaker/actions/workflows/from-paris-to-berlin-circuit-breaker.yml/badge.svg)](https://github.com/jesperancinha/from-paris-to-berlin-circuit-breaker/actions/workflows/from-paris-to-berlin-circuit-breaker.yml)
[![e2e-from-paris-to-berlin-circuit-breaker](https://github.com/jesperancinha/from-paris-to-berlin-circuit-breaker/actions/workflows/from-paris-to-berlin-circuit-breaker-e2e.yml/badge.svg)](https://github.com/jesperancinha/from-paris-to-berlin-circuit-breaker/actions/workflows/from-paris-to-berlin-circuit-breaker-e2e.yml)

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/db670c80127c412d95d74ec2a10145ff)](https://www.codacy.com/gh/jesperancinha/from-paris-to-berlin-circuit-breaker/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/from-paris-to-berlin-circuit-breaker&amp;utm_campaign=Badge_Grade)
[![codebeat badge](https://codebeat.co/badges/43d5e2ce-3805-4ab3-9804-0d68b5ad8d24)](https://codebeat.co/projects/github-com-jesperancinha-from-paris-to-berlin-circuit-breaker-main)
[![BCH compliance](https://bettercodehub.com/edge/badge/jesperancinha/from-paris-to-berlin-circuit-breaker?branch=main)](https://bettercodehub.com/results/jesperancinha/from-paris-to-berlin-circuit-breaker)
[![Known Vulnerabilities](https://snyk.io/test/github/jesperancinha/from-paris-to-berlin-circuit-breaker/badge.svg)](https://snyk.io/test/github/jesperancinha/from-paris-to-berlin-circuit-breaker)

[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/db670c80127c412d95d74ec2a10145ff)](https://www.codacy.com/gh/jesperancinha/from-paris-to-berlin-circuit-breaker/dashboard?utm_source=github.com&utm_medium=referral&utm_content=jesperancinha/from-paris-to-berlin-circuit-breaker&utm_campaign=Badge_Coverage)
[![codecov](https://codecov.io/gh/jesperancinha/from-paris-to-berlin-circuit-breaker/branch/main/graph/badge.svg?token=M7ZOccsNUq)](https://codecov.io/gh/jesperancinha/from-paris-to-berlin-circuit-breaker)
[![Coverage Status](https://coveralls.io/repos/github/jesperancinha/from-paris-to-berlin-circuit-breaker/badge.svg?branch=main)](https://coveralls.io/github/jesperancinha/from-paris-to-berlin-circuit-breaker?branch=main)

[![GitHub language count](https://img.shields.io/github/languages/count/jesperancinha/from-paris-to-berlin-circuit-breaker.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/top/jesperancinha/from-paris-to-berlin-circuit-breaker.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/code-size/jesperancinha/from-paris-to-berlin-circuit-breaker.svg)](#)

---

## Technologies used

---

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/kotlin-50.png "Kotlin")](https://kotlinlang.org/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/docker-50.png)](https://www.docker.com/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/docker-compose-50.png)](https://docs.docker.com/compose/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/postgres-50.png "PostgreSQL")](https://www.postgresql.org/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/spring-50.png)](https://spring.io/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/spring-boot-50.png)](https://spring.io/projects/spring-boot)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/spring-reactor-50.png)](https://projectreactor.io/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/angular-50.png "Angular")](https://angular.io/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/bash-50.png)](https://www.gnu.org/software/bash/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/jupiter5-50.png "Jupiter 5")](https://junit.org/junit5/docs/current/user-guide/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/kotest-50.png "Kotest 4.6.1")](https://kotest.io/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/mockk-50.png "MockK")](https://mockk.io/)

---

This project is an investigation of Circuit-Breakers in the Kotlin language


## Introduction

The idea of this project is to take passengers from Paris to Berlin.	However, in our simulation, the weather is bad and so the lines will be failing.	The role of kystrix here is to avoid a massive traffic flow to blocked roads and instead to get the cars somewhere else.

The idea of [Kystrix](https://github.com/johanhaleby/kystrix) is the same as [Hystrix](https://github.com/Netflix/Hystrix). These are circuit-breakers responsible to stop the flow of requests to certain endpoints and make them fail or redirect to somewhere else.

Hystrix is however no longer in development, and instead we have [Resilience4J](https://github.com/resilience4j/resilience4j).

This means that although we can hope that [Kystrix](https://github.com/johanhaleby/kystrix) gets more standardized, it appears to be that [Resilience4J](https://github.com/resilience4j/resilience4j) is the way to go at this time.

This repo is also the official support article to my article on medium:

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/medium-20.png "Medium")](https://medium.com/@jofisaes/from-paris-to-berlin-creating-circuit-breakers-in-kotlin-3c8be96876ca) [From Paris to Berlin — Creating Circuit-Breakers in Kotlin](https://medium.com/@jofisaes/from-paris-to-berlin-creating-circuit-breakers-in-kotlin-3c8be96876ca)

<div align="center">
      <a title="From Paris to Berlin — Creating Circuit-Breakers in Kotlin" href="https://medium.com/@jofisaes/from-paris-to-berlin-creating-circuit-breakers-in-kotlin-3c8be96876ca">
     <img 
          src="./docs/images/article.paris.berlin.banner.png" 
          style="width:100%;">
      </a>
</div>

	
## Project Layout

1.  [From Paris to Berlin Data](./from-paris-to-berlin-data) - Common Data Library used in all executable projects
2.  [From Paris to Brlin City Generator](./from-paris-to-berlin-city-generator) - Generates a City JSON which can be fed to the main application
3.  [From Paris to Berlin Kystrix Demo](./from-paris-to-berlin-kystrix-runnable-app) - A simplified Kystrix demo independently of Spring
4.  [From Paris to Berlin Resilience4J Runnable Demo](./from-paris-to-berlin-resilience4j-runnable-app) - Creating Circuit Breakers using programmatic Resilience4J independently of Spring
5.  [From Paris to Berlin Resilience4J Spring Demo](./from-paris-to-berlin-resilience4j-spring-app) - Creating Circuit Breakers using programmatic Resilience4J using Spring
6.  [From Paris to Berlin Resilience4J AOP Spring Demo](./from-paris-to-berlin-resilience4j-aop-app) - Creating Circuit Breakers using AOP(Aspect Oriented Programming) and declarative Resilience4J
7.  [From Paris to Berlin Web](./from-paris-to-berlin-web) - Front end application to support the Paris to Berlin Game

## Java Setup

```shell
sdk install java 17-open
sdk use java 17-open
```

## How to run locally

##### Docker-Compose

1. Start Image

```shell
make dcup-full-action
```

2. Start Demo

```shell
make demo-docker
```

3. Go to [localhost:9000](http://localhost:9000)

---

## Swagger UI


##### Local

1. [from-paris-to-berlin-resilience4j-aop-spring-app](http://localhost:8080/api/fptb/webjars/swagger-ui/index.html)
2. [from-paris-to-berlin-ws-service](http://localhost:8081/api/fptb/webjars/swagger-ui/index.html)

##### Via Docker

1. [from-paris-to-berlin-resilience4j-aop-spring-app](http://localhost:9000/api/fptb/webjars/swagger-ui/index.html)
2. [from-paris-to-berlin-ws-service](http://localhost:9000/api/fptb/broker/webjars/swagger-ui/index.html)

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

## About me 👨🏽‍💻🚀🏳️‍🌈

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/JEOrgLogo-20.png "João Esperancinha Homepage")](http://joaofilipesabinoesperancinha.nl)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/medium-20.png "Medium")](https://medium.com/@jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/bmc-20.png "Buy me a Coffe")](https://www.buymeacoffee.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/credly-20.png "Credly")](https://www.credly.com/users/joao-esperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=WWW&message=joaofilipesabinoesperancinha.nl&color=6495ED "João Esperancinha Homepage")](https://joaofilipesabinoesperancinha.nl/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/google-apps-20.png "Google Apps")](https://play.google.com/store/apps/developer?id=Joao+Filipe+Sabino+Esperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/sonatype-20.png "Sonatype Search Repos")](https://search.maven.org/search?q=org.jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/docker-20.png "Docker Images")](https://hub.docker.com/u/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/stack-overflow-20.png)](https://stackoverflow.com/users/3702839/joao-esperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/reddit-20.png "Reddit")](https://www.reddit.com/user/jesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/devto-20.png "Dev To")](https://dev.to/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hackernoon-20.jpeg "Hackernoon")](https://hackernoon.com/@jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codeproject-20.png "Code Project")](https://www.codeproject.com/Members/jesperancinha)
[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=social "GitHub")](https://github.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/bitbucket-20.png "BitBucket")](https://bitbucket.org/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/gitlab-20.png "GitLab")](https://gitlab.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/coursera-20.png "Coursera")](https://www.coursera.org/user/da3ff90299fa9297e283ee8e65364ffb)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/free-code-camp-20.jpg "FreeCodeCamp")](https://www.freecodecamp.org/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hackerrank-20.png "HackerRank")](https://www.hackerrank.com/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/leet-code-20.png "LeetCode")](https://leetcode.com/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codebyte-20.png "Codebyte")](https://coderbyte.com/profile/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codewars-20.png "CodeWars")](https://www.codewars.com/users/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codepen-20.png "Code Pen")](https://codepen.io/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hacker-earth-20.png "Hacker Earth")](https://www.hackerearth.com/@jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/khan-academy-20.png "Khan Academy")](https://www.khanacademy.org/profile/jofisaes)
[![Twitter Follow](https://img.shields.io/twitter/follow/joaofse?label=João%20Esperancinha&style=social "Twitter")](https://twitter.com/joaofse)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hacker-news-20.png "Hacker News")](https://news.ycombinator.com/user?id=jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/infoq-20.png "InfoQ")](https://www.infoq.com/profile/Joao-Esperancinha.2/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/linkedin-20.png "LinkedIn")](https://www.linkedin.com/in/joaoesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/xing-20.png "Xing")](https://www.xing.com/profile/Joao_Esperancinha/cv)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/tumblr-20.png "Tumblr")](https://jofisaes.tumblr.com/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/pinterest-20.png "Pinterest")](https://nl.pinterest.com/jesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/quora-20.png "Quora")](https://nl.quora.com/profile/Jo%C3%A3o-Esperancinha)
[![VMware Spring Professional 2021](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/vmware-spring-professional-2021-20.png "VMware Spring Professional 2021")](https://www.credly.com/badges/762fa7a4-9cf4-417d-bd29-7e072d74cdb7)
[![Oracle Certified Professional, JEE 7 Developer](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/oracle-certified-professional-java-ee-7-application-developer-20.png "Oracle Certified Professional, JEE7 Developer")](https://www.credly.com/badges/27a14e06-f591-4105-91ca-8c3215ef39a2)
[![Oracle Certified Professional, Java SE 11 Programmer](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/oracle-certified-professional-java-se-11-developer-20.png "Oracle Certified Professional, Java SE 11 Programmer")](https://www.credly.com/badges/87609d8e-27c5-45c9-9e42-60a5e9283280)
[![IBM Cybersecurity Analyst Professional](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/ibm-cybersecurity-analyst-professional-certificate-20.png "IBM Cybersecurity Analyst Professional")](https://www.credly.com/badges/ad1f4abe-3dfa-4a8c-b3c7-bae4669ad8ce)
[![Certified Advanced JavaScript Developer](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/cancanit-badge-1462-20.png "Certified Advanced JavaScript Developer")](https://cancanit.com/certified/1462/)
[![Certified Neo4j Professional](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/professional_neo4j_developer-20.png "Certified Neo4j Professional")](https://graphacademy.neo4j.com/certificates/c279afd7c3988bd727f8b3acb44b87f7504f940aac952495ff827dbfcac024fb.pdf)
[![Deep Learning](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/deep-learning-20.png "Deep Learning")](https://www.credly.com/badges/8d27e38c-869d-4815-8df3-13762c642d64)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=JEsperancinhaOrg&color=yellow "jesperancinha.org dependencies")](https://github.com/JEsperancinhaOrg)
[![Generic badge](https://img.shields.io/static/v1.svg?label=All%20Badges&message=Badges&color=red "All badges")](https://joaofilipesabinoesperancinha.nl/badges)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Status&message=Project%20Status&color=red "Project statuses")](https://github.com/jesperancinha/project-signer/blob/master/project-signer-quality/Build.md)
