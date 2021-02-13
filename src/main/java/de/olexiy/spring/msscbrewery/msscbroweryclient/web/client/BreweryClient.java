package de.olexiy.spring.msscbrewery.msscbroweryclient.web.client;

import de.olexiy.spring.msscbrewery.msscbroweryclient.web.model.BeerDto;
import de.olexiy.spring.msscbrewery.msscbroweryclient.web.model.CustomerDto;
import java.net.URI;
import java.util.UUID;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@ConfigurationProperties(value = "msscbrewery.api", ignoreUnknownFields = false)
public class BreweryClient {

  public final String BEER_PATH_V1 = "/api/v1/beer/";
  public final String CUSTOMER_PATH_V1 = "/api/v1/customer/";

  private String apiHost;

  private final RestTemplate restTemplate;

  public BreweryClient(RestTemplateBuilder restTemplateBuilder){
    this.restTemplate = restTemplateBuilder.build();
  }

  public BeerDto getBeerById(UUID uuid){
    return this.restTemplate.getForObject(apiHost+BEER_PATH_V1+ uuid.toString(), BeerDto.class);
  }

  public URI saveNewBeer(BeerDto beerDTO){
    return this.restTemplate.postForLocation(apiHost+BEER_PATH_V1, beerDTO);
  }

  public void updateExistingBeer(UUID uuid, BeerDto beerDTO){
    this.restTemplate.put(apiHost+BEER_PATH_V1+ uuid.toString(), beerDTO);
  }

  public void deleteBeer(UUID uuid){
    this.restTemplate.delete(apiHost+BEER_PATH_V1+ uuid.toString());
  }

  public CustomerDto getCustomerById(UUID uuid){
    return this.restTemplate.getForObject(apiHost+CUSTOMER_PATH_V1+ uuid.toString(), CustomerDto.class);
  }

  public URI saveNewCustomer(CustomerDto customerDTO){
    return this.restTemplate.postForLocation(apiHost+CUSTOMER_PATH_V1, customerDTO);
  }

  public void updateExistingCustomer(UUID uuid, CustomerDto customerDTO){
    this.restTemplate.put(apiHost+CUSTOMER_PATH_V1+ uuid.toString(), customerDTO);
  }

  public void deleteCustomer(UUID uuid){
    this.restTemplate.delete(apiHost+CUSTOMER_PATH_V1+ uuid.toString());
  }

  public void setApiHost(String apiHost) {
    this.apiHost = apiHost;
  }
}
