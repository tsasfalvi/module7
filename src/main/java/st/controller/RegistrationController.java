package st.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import st.dto.UserRegistration;
import st.service.UserService;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/user")
public class RegistrationController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/registration", method = POST)
    public boolean register(@Valid @RequestBody final UserRegistration userRegistration, BindingResult result, WebRequest request, Errors errors) {
        if (result.hasErrors()) {
            return false;
        }

        try {
            userService.createUser(userRegistration);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
