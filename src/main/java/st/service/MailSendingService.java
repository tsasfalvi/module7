package st.service;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import st.entity.BorrowEntity;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class MailSendingService {
    private static final Logger LOG = getLogger(MailSendingService.class);


    public void send(BorrowEntity borrowEntity) {
        LOG.warn("EMAIL SENT: Borrow outdated {}, {}", borrowEntity.getBookId(), borrowEntity.getUser().getEmail());
    }
}
