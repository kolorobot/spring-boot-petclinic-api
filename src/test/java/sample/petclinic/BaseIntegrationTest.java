package sample.petclinic;

import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.concurrent.TimeUnit;

@RunWith(JUnitParamsRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public abstract class BaseIntegrationTest {

    @ClassRule
    public static final SpringClassRule SCR = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    private static final Logger LOG = LoggerFactory.getLogger(BaseIntegrationTest.class);

    TestRestTemplate restTemplate = new TestRestTemplate();

    /* port the application is running */
    @Value("${local.server.port}")
    private int port;

    @Before
    public void setUp() throws Exception {
        LOG.info("\n\n\tLocal server port: {}\n", port);
    }

    public UriComponentsBuilder localUriBuilder() {
        return UriComponentsBuilder.fromHttpUrl("http://localhost:" + port);
    }

    @Test
    public void simulatesLongRunningOperation() throws InterruptedException {
        Thread.sleep(TimeUnit.SECONDS.toMillis(5));
    }
}
