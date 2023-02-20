package ng.com.sokoto.service;

import ng.com.sokoto.web.domain.Subscription;

public interface SubscriptionTransaction {
    public void processSubscription(Subscription subscription);
}
