package st.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import st.dto.User;
import st.service.SubscriptionFacade;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/subscribe")
public class SubscriptionController {
    private SubscriptionFacade subscriptionFacade;

    public SubscriptionController(SubscriptionFacade subscriptionFacade) {
        this.subscriptionFacade = subscriptionFacade;
    }

    @ResponseBody
    @RequestMapping(value = "/{bookId}", method = PUT)
    public ResponseEntity<User> subscribe(@PathVariable int bookId) {
        User result = subscriptionFacade.subscribe(bookId);
        return new ResponseEntity<>(result, OK);
    }
}
