package ng.com.sokoto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ng.com.sokoto.web.domain.DateInterval;
import ng.com.sokoto.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DateIntervalTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DateInterval.class);
        DateInterval dateInterval1 = new DateInterval();
        dateInterval1.setId("id1");
        DateInterval dateInterval2 = new DateInterval();
        dateInterval2.setId(dateInterval1.getId());
        assertThat(dateInterval1).isEqualTo(dateInterval2);
        dateInterval2.setId("id2");
        assertThat(dateInterval1).isNotEqualTo(dateInterval2);
        dateInterval1.setId(null);
        assertThat(dateInterval1).isNotEqualTo(dateInterval2);
    }
}
