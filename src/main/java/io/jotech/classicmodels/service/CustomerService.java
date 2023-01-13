package io.jotech.classicmodels.service;


import java.util.List;

import io.jotech.classicmodels.entity.Customer;

public interface CustomerService {
    List<Customer> getAllCustomers(Integer start, Integer limit);

    Customer createCustomer(Customer customer);

    Customer getCustomer(Integer     customerNumber);

    Customer updateCustomer(Customer customer);

    boolean deleteCustomer(Integer customerNumber);
}
