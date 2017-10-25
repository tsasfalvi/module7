package st.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import st.dto.Profile;
import st.dto.UserRegistration;
import st.entity.UserEntity;
import st.service.UserService;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(method = GET)
    public Iterable<UserEntity> getAll() {
        return userService.getAllUsers();
    }

    @RequestMapping(method = POST)
    public void save(@RequestBody UserRegistration user) {
        userService.createUser(user);
    }

    @RequestMapping(value = "/{userId}", method = PUT)
    public void update(@RequestBody UserRegistration user) {
        userService.saveOrUpdate(user);
    }

    @ResponseBody
    @RequestMapping(value = "/{userId}", method = GET)
    public UserEntity get(@PathVariable String userId) {
        return userService.getUser(userId);
    }

    @RequestMapping(value = "/{userId}", method = DELETE)
    public void remove(@PathVariable String userId) {
        userService.delete(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/profile", method = GET)
    public Profile profile() {
        return userService.getProfile();
    }
}
