package pl.edu.wszib.client_browser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.client_browser.dao.ICustomerDAO;
import pl.edu.wszib.client_browser.model.Customer;
import pl.edu.wszib.client_browser.service.ICustomerService;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {
    private final ICustomerDAO customerDAO;

    @Autowired
    public CustomerServiceImpl(final ICustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public Customer getCustomerById(final Long id) {
        return this.customerDAO.getCustomerById(id);
    }

    @Override
    public void deleteCustomer(final Customer customer) {
        this.customerDAO.deleteCustomer(customer);
    }

    //TODO add client validator
    @Override
    public boolean addNewCustomer(final Customer customer) {

        if (customer.equals(this.customerDAO.getCustomerById(customer.getId()))) {
            return false;
        }
        this.customerDAO.addNewCustomer(customer);
        return true;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerDAO.getAllCustomers();
    }
}
