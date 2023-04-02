package ng.com.sokoto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ng.com.sokoto.web.domain.Facility;
import ng.com.sokoto.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FacilityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Facility.class);
        Facility facility1 = new Facility();
        facility1.setId("id1");
        Facility facility2 = new Facility();
        facility2.setId(facility1.getId());
        assertThat(facility1).isEqualTo(facility2);
        facility2.setId("id2");
        assertThat(facility1).isNotEqualTo(facility2);
        facility1.setId(null);
        assertThat(facility1).isNotEqualTo(facility2);
    }
}
