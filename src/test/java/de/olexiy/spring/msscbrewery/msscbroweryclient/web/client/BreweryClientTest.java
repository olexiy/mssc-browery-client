package de.olexiy.spring.msscbrewery.msscbroweryclient.web.client;

import static org.junit.jupiter.api.Assertions.*;


import de.olexiy.spring.msscbrewery.msscbroweryclient.web.model.BeerDto;
import de.olexiy.spring.msscbrewery.msscbroweryclient.web.model.CustomerDto;
import java.math.BigDecimal;
import java.net.URI;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BreweryClientTest {

  @Autowired
  BreweryClient breweryClient;

  @Test
  @DisplayName("Should load beer by ID with GET")
  void getBeerById() throws Exception{
    BeerDto beerDTO = breweryClient.getBeerById(UUID.randomUUID());
    assertNotNull(beerDTO);
  }

  @Test
  @DisplayName("Should save new beer with POST")
  void saveNewBeer()  throws Exception{
    BeerDto beerDTO = getValidBeerDto();
    URI newBeerURI = breweryClient.saveNewBeer(beerDTO);
    assertNotNull(newBeerURI);

    System.out.println(newBeerURI.toString());
  }

  @Test
  @DisplayName("Should update existing beer with PATH")
  void updateBeer() throws Exception{
    BeerDto beerDTO = getValidBeerDto();
    beerDTO.setId(UUID.randomUUID());
    breweryClient.updateExistingBeer(beerDTO.getId(), beerDTO);
  }

  @Test
  @DisplayName("Should remove existing beer with DELETE")
  void deleteBeer() throws Exception{
    breweryClient.deleteBeer(UUID.randomUUID());
  }

  @Test
  @DisplayName("Should load customer by ID with GET")
  void getCustomerById() throws Exception{
    CustomerDto customerDTO = breweryClient.getCustomerById(UUID.randomUUID());
    assertNotNull(customerDTO);
  }

  @Test
  @DisplayName("Should save new customer with POST")
  void saveNewCustomer()  throws Exception{
    CustomerDto customerDTO = getValidCustomerDto();
    URI newBeerURI = breweryClient.saveNewCustomer(customerDTO);
    assertNotNull(newBeerURI);

    System.out.println(newBeerURI.toString());
  }

  @Test
  @DisplayName("Should update existing customer with PATH")
  void updateCustomer() throws Exception{
    CustomerDto customerDTO = getValidCustomerDto();
    customerDTO.setId(UUID.randomUUID());
    breweryClient.updateExistingCustomer(customerDTO.getId(), customerDTO);
  }

  @Test
  @DisplayName("Should remove existing customer with DELETE")
  void deleteCustomer() throws Exception{
    breweryClient.deleteCustomer(UUID.randomUUID());
  }

  CustomerDto getValidCustomerDto() {
    return CustomerDto.builder()
        .name("John Dow")
        .build();
  }

  BeerDto getValidBeerDto() {
    return BeerDto.builder()
        .beerName("My Beer")
        .beerStyle("ALE")
        .price(new BigDecimal("9.99"))
        .upc(123452345L)
        .build();
  }

}