package pl.edu.wszib.client_browser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.client_browser.service.ICustomerService;

@Controller
public class CustomerController {
    private final ICustomerService customerService;

    @Autowired
    public CustomerController(final ICustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public String customer(@PathVariable final Long id, Model model) {
        model.addAttribute("customer", this.customerService.getCustomerById(id));

        return "/customer";
    }
}
