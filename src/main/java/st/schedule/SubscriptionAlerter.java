package st.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import st.repository.BorrowRepository;
import st.service.MailSendingService;

import javax.annotation.PostConstruct;
import java.util.concurrent.ScheduledExecutorService;

import static java.time.LocalDate.now;
import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;
import static java.util.concurrent.TimeUnit.SECONDS;

@Component
public class SubscriptionAlerter {
    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private MailSendingService mailSendingService;

    private ScheduledExecutorService executorService = newSingleThreadScheduledExecutor();
    private Runnable alerter = initRunnable();

    @PostConstruct
    private void init() {
        executorService.schedule(alerter, 10, SECONDS);
    }

    private void destroy() {
        executorService.shutdownNow();
    }

    private Runnable initRunnable() {
        return () -> {
            borrowRepository.findAllOutdated(now()).forEach(borrowEntity -> mailSendingService.send(borrowEntity));
            executorService.schedule(alerter, 30, SECONDS);
        };
    }
}
