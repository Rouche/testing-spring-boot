package guru.springframework.brewery.web.controllers;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import lombok.extern.slf4j.Slf4j;

import guru.springframework.brewery.services.BeerOrderService;
import guru.springframework.brewery.web.model.BeerOrderDto;
import guru.springframework.brewery.web.model.BeerOrderPagedList;
import guru.springframework.brewery.web.model.OrderStatusEnum;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BeerOrderController.class)
@Slf4j
class BeerOrderControllerTest {

    private static final UUID CUSTOMER_ID = UUID.randomUUID();

    @MockBean
    private BeerOrderService beerOrderService;

    @Autowired
    private MockMvc mockMvc;

    private List<BeerOrderDto> orders;

    @BeforeEach
    void setUp() {

        orders = Arrays.asList(BeerOrderDto.builder()
                .customerId(UUID.randomUUID())
                .id(UUID.randomUUID())
                .customerRef("Ref1")
                .orderStatus(OrderStatusEnum.NEW)
                .createdDate(OffsetDateTime.parse("2020-01-01T20:15:33Z"))
                .build(), BeerOrderDto.builder()
                .customerId(CUSTOMER_ID)
                .id(UUID.randomUUID())
                .customerRef("Ref2")
                .orderStatus(OrderStatusEnum.READY)
                .createdDate(OffsetDateTime.parse("2020-01-01T20:10:33Z"))
                .build());
    }

    @AfterEach
    void tearDown() {
        reset(beerOrderService);
    }

    @Test
    void listOrders() throws Exception {
        // Given
        BeerOrderPagedList pagedList = new BeerOrderPagedList(orders, PageRequest.of(1, 10), 100);
        given(beerOrderService.listOrders(any(UUID.class), any(Pageable.class))).willReturn(pagedList);

        // When Then
        mockMvc.perform(
                get("/api/v1/customers/{customerId}/orders", CUSTOMER_ID.toString())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.content", hasSize(2)));
    }

    @Test
    void getOrder() throws Exception {
        //Given
        given(beerOrderService.getOrderById(any(UUID.class), any(UUID.class))).willReturn(orders.get(0));
        BeerOrderDto beerOrderDto = orders.get(0);

        // When Then
        mockMvc.perform(
                get("/api/v1/customers/{customerId}/orders/{orderId}", CUSTOMER_ID.toString(), beerOrderDto.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(beerOrderDto.getId().toString()));
    }

}