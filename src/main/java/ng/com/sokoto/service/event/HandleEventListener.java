package ng.com.sokoto.service.event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ng.com.sokoto.service.JournalService;
import ng.com.sokoto.service.SubscriptionService;
import ng.com.sokoto.web.domain.Journal;
import ng.com.sokoto.web.domain.JournalLine;
import ng.com.sokoto.web.domain.Subscription;
import ng.com.sokoto.web.domain.enumeration.DebitCreditEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HandleEventListener {

    @Autowired
    private JournalService journalService;

    @Autowired
    private SubscriptionService subscriptionService;

    @EventListener
    public void handleEventSource(final EventMessage<Subscription> eventPayload) {
        System.out.println("1 The Event has just been fired Subscription....................." + eventPayload.getPayload());
        eventPayload.getSubscriptionTransaction().processSubscription(eventPayload.getPayload());
    }
}
