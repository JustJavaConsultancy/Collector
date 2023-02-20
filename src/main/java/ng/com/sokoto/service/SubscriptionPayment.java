package ng.com.sokoto.service;

import java.util.Arrays;
import java.util.List;
import ng.com.sokoto.web.domain.Journal;
import ng.com.sokoto.web.domain.JournalLine;
import ng.com.sokoto.web.domain.Subscription;
import ng.com.sokoto.web.domain.SystemParameters;
import ng.com.sokoto.web.service.SystemParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionPayment implements SubscriptionTransaction {

    @Autowired
    private SystemParametersService systemParametersService;

    @Autowired
    @Lazy
    private SubscriptionService subscriptionService;

    @Autowired
    private JournalService journalService;

    @Autowired
    private SubscriberService subscriberService;

    @Override
    public void processSubscription(Subscription subscription) {
        Double subscriberBalance = subscription.getSubscriber().getWallet().getAmount();
        Double amountOwing = Math.abs(subscription.getReceivable().getAmount());
        Double amountToDeduct = amountOwing >= subscriberBalance ? subscriberBalance : amountOwing;
        SystemParameters systemParameters = systemParametersService.load();

        JournalLine subscribberWallet = subscription.getSubscriber().getWallet().debit(amountToDeduct);
        JournalLine collector = systemParameters.getCollectorWallet().credit(amountToDeduct);
        JournalLine receivable = subscription.getReceivable().credit(amountToDeduct);
        JournalLine cash = subscription.getCash().debit(amountToDeduct);
        List<JournalLine> lines = Arrays.asList(subscribberWallet, collector, receivable, cash);
        Journal journal = journalService.createJournal(
            subscription.getAllocationRefNumber() + "_" + subscription.getFacility().getDescription(),
            subscription.getId(),
            lines
        );

        subscriberService.save(subscription.getSubscriber());
        systemParametersService.save(systemParameters);
        subscriptionService.save(subscription);
        System.out.println("4 Stage ................................................................................");
    }
}
