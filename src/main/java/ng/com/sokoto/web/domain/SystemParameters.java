package ng.com.sokoto.web.domain;

import javax.persistence.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SystemParameters {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Wallet getCollectorWallet() {
        return collectorWallet;
    }

    public void setCollectorWallet(Wallet collectorWallet) {
        this.collectorWallet = collectorWallet;
    }

    @Id
    private String id;

    private Wallet collectorWallet;
}
