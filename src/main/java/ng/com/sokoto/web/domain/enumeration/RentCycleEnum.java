package ng.com.sokoto.web.domain.enumeration;

import java.io.Serializable;

/**
 * The RentCycleEnum enumeration.
 */
public enum RentCycleEnum implements Serializable {
    Daily("DAILY"),
    Weekly("WEEKLY"),
    Monthly("MONTHLY"),
    Yearly("YEARLY");

    RentCycleEnum(String frequency) {}
}
