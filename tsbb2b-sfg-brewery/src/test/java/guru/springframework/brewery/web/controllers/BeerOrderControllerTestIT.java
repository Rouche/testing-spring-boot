package guru.springframework.brewery.web.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import guru.springframework.brewery.domain.BeerOrder;
import guru.springframework.brewery.domain.Customer;
import guru.springframework.brewery.repositories.BeerOrderRepository;
import guru.springframework.brewery.repositories.CustomerRepository;
import guru.springframework.brewery.web.model.BeerOrderDto;
import guru.springframework.brewery.web.model.BeerOrderPagedList;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
/**
 * ROUCHE_DOCS: Because of access through customer -> beerOrders
 * removed by autowiring beerOrderRepo.
 */
// @Transactional
class BeerOrderControllerTestIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BeerOrderRepository beerOrderRepository;

    private Customer customer;
    private BeerOrder beerOrder;

    @BeforeEach
    void setUp() {
        customer = customerRepository.findAllByOrderByCustomerName().get(0);
        Page<BeerOrder> beerOrderPage = beerOrderRepository.findAllByCustomer(customer, PageRequest.of(0, 10));
        beerOrder = beerOrderPage.iterator().next();
    }

    @Test
    void listOrders() {
        BeerOrderPagedList pagedList = restTemplate.getForObject("/api/v1/customers/{customerId}/orders", BeerOrderPagedList.class,
                customer.getId().toString());

        assertThat(pagedList.getContent()).hasSize(1);
    }

    @Test
    void getOrder() {
        BeerOrderDto beerOrderDto = restTemplate.getForObject("/api/v1/customers/{customerId}/orders/{orderId}", BeerOrderDto.class,
                customer.getId().toString(), beerOrder.getId().toString());

        assertThat(beerOrderDto.getCustomerRef()).isEqualTo(beerOrder.getCustomerRef());
    }
}