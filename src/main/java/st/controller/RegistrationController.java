package st.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import st.dto.User;
import st.dto.UserRegistration;
import st.service.UserService;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping(method = POST)
    public ResponseEntity<Object> register(@Valid @RequestBody final UserRegistration userRegistration, BindingResult result, WebRequest request, Errors errors) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), BAD_REQUEST);
        }

        try {
            User user = userService.createUser(userRegistration);
            return new ResponseEntity<>(user, CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
        }
    }
}
