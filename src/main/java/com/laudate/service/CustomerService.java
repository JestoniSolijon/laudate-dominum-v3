package com.laudate.service;

import com.laudate.entity.Customer;
import com.laudate.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Optional<Customer> findCustomerById(int customerId) { return customerRepository.findById(customerId); }

    public List<Customer> getAllCustomer() { return customerRepository.findAll(); }
}
