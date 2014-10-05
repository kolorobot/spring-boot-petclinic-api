package sample.petclinic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import sample.petclinic.model.Owner;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest(value = "server.port=9000")
public class OwnerResourceTests {

    private RestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void getsOwner() {
        // arrange
        String ownerUrl = "http://localhost:9000/owners/1";
        ParameterizedTypeReference<Resource<Owner>> responseType = new ParameterizedTypeReference<Resource<Owner>>() {
        };
        // act
        ResponseEntity<Resource<Owner>> responseEntity =
                restTemplate.exchange(ownerUrl, GET, null, responseType);
        // assert
        Owner owner = responseEntity.getBody().getContent();
        assertEquals("George", owner.getFirstName());
    }


}
