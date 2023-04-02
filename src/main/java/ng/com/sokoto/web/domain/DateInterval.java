package ng.com.sokoto.web.domain;

import java.io.Serializable;
import ng.com.sokoto.web.domain.enumeration.DateIntervalMeasureEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A DateInterval.
 */
@Document(collection = "date_interval")
public class DateInterval implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("interval")
    private Integer interval;

    @Field("measure")
    private DateIntervalMeasureEnum measure;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public DateInterval id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getInterval() {
        return this.interval;
    }

    public DateInterval interval(Integer interval) {
        this.setInterval(interval);
        return this;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public DateIntervalMeasureEnum getMeasure() {
        return this.measure;
    }

    public DateInterval measure(DateIntervalMeasureEnum measure) {
        this.setMeasure(measure);
        return this;
    }

    public void setMeasure(DateIntervalMeasureEnum measure) {
        this.measure = measure;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DateInterval)) {
            return false;
        }
        return id != null && id.equals(((DateInterval) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DateInterval{" +
            "id=" + getId() +
            ", interval=" + getInterval() +
            ", measure='" + getMeasure() + "'" +
            "}";
    }
}
