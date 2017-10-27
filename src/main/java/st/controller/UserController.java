package st.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import st.dto.User;
import st.dto.UserRegistration;
import st.service.UserService;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping(method = GET)
    public Iterable<User> getAll() {
        return userService.getAllUsers();
    }

    @RequestMapping(method = POST)
    public ResponseEntity<User> save(@RequestBody UserRegistration user) {
        User user1 = userService.createUser(user);
        return new ResponseEntity<>(user1, CREATED);
    }

    @RequestMapping(value = "/{userId:.+}", method = PUT)
    public ResponseEntity<User> update(@RequestBody User user) {
        User result = userService.update(user);
        return new ResponseEntity<>(result, OK);
    }

    @ResponseBody
    @RequestMapping(value = "/{userId:.+}", method = GET)
    public ResponseEntity<User> get(@PathVariable String userId) {
        User user = userService.getUser(userId);
        return new ResponseEntity<>(user, OK);
    }

    @RequestMapping(value = "/{userId:.+}", method = DELETE)
    public ResponseEntity<Object> remove(@PathVariable String userId) {
        userService.delete(userId);

        return new ResponseEntity<>(NO_CONTENT);
    }

    @ResponseBody
    @RequestMapping(value = "/profile", method = GET)
    public User profile() {
        return userService.getProfile();
    }
}
