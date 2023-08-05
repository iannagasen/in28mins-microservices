package dev.agasen.currencyconversionservice;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currency-conversion")
public class CurrencyConversionController {

  private final RestTemplate restTemplate;
  private final CurrencyExchangeProxy proxy;

  /**
   * This is without OpenFeigh
   * This is using the imperative approach restTemplate
   */
  @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyConversion calculateCurrencyConversion(
    @PathVariable String from,
    @PathVariable String to,
    @PathVariable BigDecimal quantity
  ) {
    
    ResponseEntity<CurrencyConversion> res = restTemplate.getForEntity(
      "http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
      CurrencyConversion.class, 
      Map.of("from", from, "to", to)
    );

    var currencyConversion = res.getBody();

    return new CurrencyConversion(
      currencyConversion.getId(),
      from,
      to,
      quantity,
      currencyConversion.getConversionMultiple(),
      quantity.multiply(currencyConversion.getConversionMultiple()),
      "%s restTemplate".formatted(currencyConversion.getEnvironment())
    );
  }

  @GetMapping("-feign/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyConversion calculateCurrencyConversionFeign(
    @PathVariable String from,
    @PathVariable String to,
    @PathVariable BigDecimal quantity
  ) {
    
    var currencyConversion = proxy.retrieveExchangeValue(from, to);

    return new CurrencyConversion(
      currencyConversion.getId(),
      from,
      to,
      quantity,
      currencyConversion.getConversionMultiple(),
      quantity.multiply(currencyConversion.getConversionMultiple()),
      "%s restTemplate".formatted(currencyConversion.getEnvironment())
    );
  }


}
