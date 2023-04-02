package ng.com.sokoto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ng.com.sokoto.web.domain.FacilityType;
import ng.com.sokoto.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FacilityTypeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FacilityType.class);
    }
}
