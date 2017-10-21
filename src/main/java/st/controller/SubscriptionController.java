package st.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import st.service.UserService;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/subscribe")
public class SubscriptionController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/{bookId}", method = PUT)
    public boolean subscribe(@PathVariable String bookId) {
        return true;
    }
}
