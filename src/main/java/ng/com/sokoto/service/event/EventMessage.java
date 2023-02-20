package ng.com.sokoto.service.event;

import java.io.Serializable;
import java.util.HashMap;
import ng.com.sokoto.service.SubscriptionTransaction;
import org.springframework.context.ApplicationEvent;

public class EventMessage<T> extends ApplicationEvent implements Serializable {

    private String title;
    private SubscriptionTransaction subscriptionTransaction;
    private T payload;

    public EventMessage(String title, T payload, SubscriptionTransaction subscriptionTransaction) {
        super(payload);
        this.title = title;
        this.payload = payload;
        this.subscriptionTransaction = subscriptionTransaction;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public SubscriptionTransaction getSubscriptionTransaction() {
        return subscriptionTransaction;
    }

    public void setSubscriptionTransaction(SubscriptionTransaction subscriptionTransaction) {
        this.subscriptionTransaction = subscriptionTransaction;
    }

    @Override
    public String toString() {
        return "EventMessage{" + "title:'" + title + '\'' + ", payload:" + payload + ", source:" + source + '}';
    }
}
