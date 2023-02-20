package ng.com.sokoto.web.dto;

import io.swagger.annotations.ApiModel;
import ng.com.sokoto.web.domain.Wallet;

@ApiModel
public class SystemParametersDto extends AbstractDto<String> {

    private String id;
    private Wallet collectorWallet;

    public SystemParametersDto() {}

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setCollectorWallet(Wallet collectorWallet) {
        this.collectorWallet = collectorWallet;
    }

    public Wallet getCollectorWallet() {
        return this.collectorWallet;
    }
}
