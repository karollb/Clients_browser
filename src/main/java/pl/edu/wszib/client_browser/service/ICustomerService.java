package pl.edu.wszib.client_browser.service;

import pl.edu.wszib.client_browser.model.Customer;

import java.util.List;

public interface ICustomerService {
    Customer getCustomerById(final Long id);

    List<Customer> getAllCustomers();

    void deleteCustomer(final Long id);

    boolean addNewCustomer(final Customer customer);
}
