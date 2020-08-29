package com.ilyagusarov.swisscom.homeasssigment.restservice.restservice;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(WebAppCustomerController.class)
public class WebAppCustomerControllerTests {

    public static List<Customer> customers;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerRepository customerRepository;

    @Before
    public void setUp() {
        Customer ilya = new Customer((long)1, "Ilya");
        customers = Arrays.asList(ilya);
    }

    @Test
    public void getCustomers_shouldReturn200WithAllCustomers_WhenNoStartsWithParameterPassed() throws Exception {


        when(customerRepository.findAll()).thenReturn(customers);
        mvc.perform(MockMvcRequestBuilders.get("/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].displayName", Matchers.is(customers.get(0).getDisplayName())));
    }

    @Test
    public void getCustomers_shouldReturn200WithFoundCustomers_WhenStartsWithParameterPassed() throws Exception {
        Customer ilya = new Customer((long)1, "Ilya");
        List<Customer> customers = Arrays.asList(ilya);
        String startsWith = "foo";

        when(customerRepository.findByDisplayNameStartsWith(startsWith)).thenReturn(customers);
        mvc.perform(MockMvcRequestBuilders.get("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .param("startsWith", startsWith))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].displayName", Matchers.is(customers.get(0).getDisplayName())));
    }
}
