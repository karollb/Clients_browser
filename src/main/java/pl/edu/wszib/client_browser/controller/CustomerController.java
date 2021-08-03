package pl.edu.wszib.client_browser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.client_browser.model.Customer;
import pl.edu.wszib.client_browser.service.ICustomerService;
import pl.edu.wszib.client_browser.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class CustomerController {
    private final ICustomerService customerService;

    @Autowired
    public CustomerController(final ICustomerService customerService) {
        this.customerService = customerService;
    }

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public String customer(@PathVariable final Long id, Model model) {
        model.addAttribute("customer", this.customerService.getCustomerById(id));

        return "/customer";
    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.GET)
    public String addCustomerForm(Model model) {

        model.addAttribute("customer", new Customer());
        model.addAttribute("info", this.sessionObject.getInfo());

        return "/addCustomer";
    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    public String addCustomer(@ModelAttribute Customer customer) {
        if (!this.customerService.addNewCustomer(customer)) {
            return "redirect:/addCustomer";

        }

        return "redirect:/main";
    }

    @RequestMapping(value = "/deleteCustomer/{id}", method = RequestMethod.GET)
    public String deleteCustomer(@PathVariable Long id) {

        this.customerService.deleteCustomer(id);
        return "redirect:/main";
    }
}
