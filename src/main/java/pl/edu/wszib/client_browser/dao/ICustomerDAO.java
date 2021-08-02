package pl.edu.wszib.client_browser.dao;

import pl.edu.wszib.client_browser.model.Customer;

import java.util.List;

public interface ICustomerDAO {
    void addNewCustomer(final Customer customer);

    void deleteCustomer(final Customer customer);

    Customer getCustomerById(final Long id);

    List<Customer> getAllCustomers();
}
