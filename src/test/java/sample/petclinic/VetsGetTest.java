package sample.petclinic;

import junitparams.Parameters;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import sample.petclinic.model.Vet;

import java.net.URI;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;


public class VetsGetTest extends BaseIntegrationTest {

    @Test
    public void getsVetResource() {
        // arrange
        URI uri = localUriBuilder().path("vets/{id}")
                                   .buildAndExpand(1).toUri();
        ParameterizedTypeReference<Resource<Vet>> responseType
            = new ParameterizedTypeReference<Resource<Vet>>() {};

        // act
        ResponseEntity<Resource<Vet>> responseEntity
            = restTemplate.exchange(uri, GET, null, responseType);

        // assert
        Vet vet = responseEntity.getBody().getContent();
        assertEquals("James", vet.getFirstName());
    }

    @Test
    @Parameters
    public void getsVetEntity(String expectedName, int id) {
        // arrange
        URI uri = localUriBuilder().path("vets/{id}")
                                   .buildAndExpand(id).toUri();
        // act
        ResponseEntity<Vet> responseEntity
            = restTemplate.getForEntity(uri, Vet.class);

        // assert
        Vet vet = responseEntity.getBody();
        assertEquals(expectedName, vet.getFirstName());
    }

    public Object[] parametersForGetsVetEntity() {
        return $(
            $("James", 1),
            $("Helen", 2),
            $("Linda", 3),
            $("Rafael", 4)
        );
    }
}
