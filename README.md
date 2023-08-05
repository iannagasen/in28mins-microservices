
### You need to run first the EUREKA discovery server or you will get this error
![](screenshots/2023-08-03-22-47-02.png)


### Endpoints:

1. Eureka Naming Server: 
   - `http://localhost:8761/`

2. Currency Conversion Service
   - `http://localhost:8100/currency-conversion/from/{from}/to/{to}/quantity/{quantity}`
      - Endpoint using restTemplate
      - Ex: `http://localhost:8100/currency-conversion/from/USD/to/PHP/quantity/20`
   -  `http://localhost:8100/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}`
      - Endpoint using Feign Client
      - Ex: `http://localhost:8100/currency-conversion-feign/from/USD/to/PHP/quantity/{quantity}`

3. Config Server
   - 


### Currency Conversion Service
`installed starters`
1. Lombok

`properties`
```yaml
server:
  port: 8100

spring:
  application:
    name: currency-conversion

  output:
    ansi:
      enabled: always

  # commented out as it currently not working  
  # config:
  #   import:
  #   - 'optional:configserver:'

logging:
  pattern:
    console: '%clr(%d{HH:mm:ss.SSS}){blue} %clr(---){cyan} %clr([%15.15t]){yellow} %clr(:){red} %clr(%m){magenta}%n'
    level: '%5p [${spring.application.name:},%X{traceId:-},%X{spanId:-}]'

eureka:
  client:
    service-url:
      defaultZone: 'http://localhost:8761/eureka'

management:
  tracing:
    sampling:
      probability: 1.0
```

### Naming Server - Eureka Server
`INSTALLED STARTERS`
1. Lombok
2. Eureka Server
3. Cloud Config (Client)
4. DevTools
5. Actuator

```yaml

```


### Config Server
`INSTALLED STARTERS`
1. Lombok
2. DevTools
3. Cloud Config (Server)