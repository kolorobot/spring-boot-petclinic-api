package sample.petclinic;

import junitparams.Parameters;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import sample.petclinic.model.Owner;

import java.net.URI;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;

public class OwnersGetTests extends BaseIntegrationTest {

    @Test
    public void getsOwnerResource() {
        // arrange
        URI uri = localUriBuilder().path("owners/{id}")
                                   .buildAndExpand(1).toUri();
        ParameterizedTypeReference<Resource<Owner>> responseType = new ParameterizedTypeReference<Resource<Owner>>() {
        };
        // act
        ResponseEntity<Resource<Owner>> responseEntity =
            restTemplate.exchange(uri, GET, null, responseType);

        // assert
        Owner owner = responseEntity.getBody().getContent();
        assertEquals("George", owner.getFirstName());
    }

    @Test
    @Parameters
    public void getsOwnerEntity(String expectedName, int id) {
        // arrange
        URI uri = localUriBuilder().path("owners/{id}")
                                   .buildAndExpand(id).toUri();
        // act
        ResponseEntity<Owner> responseEntity = restTemplate.getForEntity(uri, Owner.class);
        // assert
        Owner owner = responseEntity.getBody();
        assertEquals(expectedName, owner.getFirstName());
    }

    public Object[] parametersForGetsOwnerEntity() {
        return $(
            $("George", 1),
            $("Betty", 2),
            $("Eduardo", 3),
            $("Harold", 4)
        );
    }
}
