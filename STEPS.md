

## OpenFeign

1. Add the `@EnableFeignClients` on `@Configuration` annotated classes
```java
@SpringBootApplication
@EnableFeignClients
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

2. Create a Proxy
```java
@FeignClient("stores")
public interface StoreClient {
    @RequestMapping(method = RequestMethod.GET, value = "/stores")
    List<Store> getStores();

    @RequestMapping(method = RequestMethod.GET, value = "/stores")
    Page<Store> getStores(Pageable pageable);

    @RequestMapping(method = RequestMethod.POST, value = "/stores/{storeId}", consumes = "application/json")
    Store update(@PathVariable("storeId") Long storeId, Store store);

    @RequestMapping(method = RequestMethod.DELETE, value = "/stores/{storeId:\\d+}")
    void delete(@PathVariable Long storeId);
}
```
- here the `stores` or `name = stores` has a registered url in the use Discovery Server like Eureka
- You can also specify a URL using the url attribute (absolute value or just a hostname).
  - ```java
    @FeignClient(name = "stores", url="http://sample.com")
    ```
- The load-balancer client above will want to discover the physical addresses for the "stores" service. If your application is a Eureka client then it will resolve the service in the Eureka service registry. If you don’t want to use Eureka, you can configure a list of servers in your external configuration using SimpleDiscoveryClient.
