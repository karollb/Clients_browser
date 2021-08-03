package pl.edu.wszib.client_browser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.client_browser.dao.ICustomerDAO;
import pl.edu.wszib.client_browser.model.Customer;
import pl.edu.wszib.client_browser.service.ICustomerService;
import pl.edu.wszib.client_browser.session.SessionObject;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CustomerServiceImpl implements ICustomerService {
    private final ICustomerDAO customerDAO;

    @Autowired
    public CustomerServiceImpl(final ICustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Resource
    SessionObject sessionObject;

    @Override
    public Customer getCustomerById(final Long id) {
        return this.customerDAO.getCustomerById(id);
    }

    @Override
    public void deleteCustomer(final Long id) {
        this.customerDAO.deleteCustomer(this.customerDAO.getCustomerById(id));
    }

    @Override
    public boolean addNewCustomer(final Customer customer) {

        if (this.customerDAO.getCustomerById(customer.getId()) != null) {
            this.sessionObject.setInfo("Użytkownik o takim Id już istnieje!");
            return false;
        }
        if (customer.getName().isEmpty()) {
            this.sessionObject.setInfo("Uzupełnij Imię Klienta!");
            return false;
        }
        if (customer.getSurname().isEmpty()) {
            this.sessionObject.setInfo("Uzupełnij Nazwisko Klienta!");
            return false;
        }
        if (!emailValidation(customer.getEmail())) {
            this.sessionObject.setInfo("Niepoprawny adres email!");
            return false;
        }
        if (!phoneValidation(customer.getPhone())) {
            this.sessionObject.setInfo("Nie poprawny numer telefonu!");
            return false;
        }

        this.customerDAO.addNewCustomer(customer);
        return true;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerDAO.getAllCustomers();
    }

    private boolean emailValidation(final String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }

    private static boolean phoneValidation(final String phone) {
        if (phone == null) {
            return true;
        }
        String regex = "(?<!\\w)(\\(?(\\+|00)?48\\)?)?[ -]?\\d{3}[ -]?\\d{3}[ -]?\\d{3}(?!\\w)";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();

    }


}


