package mftplus.model.service;

import lombok.Getter;
import mftplus.model.entity.Customer;
import mftplus.model.repository.CustomerRepository;

import java.util.List;

public class CustomerService implements Service<Customer, Integer> {
    @Getter
    private static CustomerService service = new CustomerService();

    private CustomerService() {

    }

    @Override
    public void save(Customer customer) throws Exception {
        try (CustomerRepository customerRepository = new CustomerRepository()) {
            customerRepository.save(customer);
        }

    }

    @Override
    public void edit(Customer customer) throws Exception {
        try (CustomerRepository customerRepository = new CustomerRepository()) {
            customerRepository.edit(customer);
        }

    }

    @Override
    public void delete(Integer id) throws Exception {
        try (CustomerRepository customerRepository = new CustomerRepository()) {
            customerRepository.delete(id);
        }

    }

    @Override
    public List<Customer> findAll() throws Exception {
        try (CustomerRepository customerRepository = new CustomerRepository()) {
            return customerRepository.findAll();
        }

    }

    @Override
    public Customer findById(Integer id) throws Exception {
        try (CustomerRepository customerRepository = new CustomerRepository()) {
            return customerRepository.findById(id);
        }

    }
}
