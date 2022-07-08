package ng.com.sokoto.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import ng.com.sokoto.IntegrationTest;
import org.springframework.test.context.web.WebAppConfiguration;

@CucumberContextConfiguration
@IntegrationTest
@WebAppConfiguration
public class CucumberTestContextConfiguration {}
