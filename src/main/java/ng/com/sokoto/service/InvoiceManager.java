package ng.com.sokoto.service;

import java.util.Arrays;
import java.util.List;
import ng.com.sokoto.service.event.EventMessage;
import ng.com.sokoto.web.domain.Journal;
import ng.com.sokoto.web.domain.JournalLine;
import ng.com.sokoto.web.domain.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceManager implements SubscriptionTransaction {

    @Autowired
    private JournalService journalService;

    @Autowired
    private SubscriptionService subscriptionService;

    public void processSubscription(Subscription subscription) {
        System.out.println("1 The Event has just been fired Subscription....................." + subscription);

        subscription = subscription.raiseInvoice();
        for (Integer count = 0; count < subscription.getInvoice().getCycle(); count++) {
            processEvent(subscription);
        }
        subscriptionService.save(subscription);
    }

    private void processEvent(Subscription subscription) {
        System.out.println("2 The Event has just been fired Subscription....................." + subscription);
        Double amount = subscription.getFacility().calculateTotalAmount();

        JournalLine receivable = subscription.getReceivable().debit(amount);
        JournalLine revenue = subscription.getRevenue().credit(amount);
        List<JournalLine> lines = Arrays.asList(receivable, revenue);
        Journal journal = journalService.createJournal(
            subscription.getAllocationRefNumber() + "_" + subscription.getFacility().getDescription(),
            subscription.getId(),
            lines
        );
        System.out.println(" The Event has just been fired...........journal" + journal);
    }
}
