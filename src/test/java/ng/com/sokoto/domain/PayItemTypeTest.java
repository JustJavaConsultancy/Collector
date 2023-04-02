package ng.com.sokoto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ng.com.sokoto.web.domain.PayItemType;
import ng.com.sokoto.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PayItemTypeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PayItemType.class);
    }
}
