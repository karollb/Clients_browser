package pl.edu.wszib.client_browser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.client_browser.service.ICustomerService;

@Controller
public class CommonController {
    private final ICustomerService customerService;

    @Autowired
    public CommonController(final ICustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String landingPage() {
        return "redirect:/main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {

        model.addAttribute("customers", this.customerService.getAllCustomers());
        return "/main";
    }

}
