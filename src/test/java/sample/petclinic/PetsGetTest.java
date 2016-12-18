package sample.petclinic;

import junitparams.Parameters;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import sample.petclinic.model.Pet;

import java.net.URI;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;


public class PetsGetTest extends BaseIntegrationTest {

    @Test
    public void getsPetResource() {
        // arrange
        URI uri = localUriBuilder().path("pets/{id}")
                                   .buildAndExpand(1).toUri();
        ParameterizedTypeReference<Resource<Pet>> responseType
            = new ParameterizedTypeReference<Resource<Pet>>() {};

        // act
        ResponseEntity<Resource<Pet>> responseEntity
            = restTemplate.exchange(uri, GET, null, responseType);

        // assert
        Pet vet = responseEntity.getBody().getContent();
        assertEquals("Leo", vet.getName());
    }

    @Test
    @Parameters
    public void getsPetEntity(String expectedName, int id) {
        // arrange
        URI uri = localUriBuilder().path("pets/{id}")
                                   .buildAndExpand(id).toUri();
        // act
        ResponseEntity<Pet> responseEntity
            = restTemplate.getForEntity(uri, Pet.class);

        // assert
        Pet vet = responseEntity.getBody();
        assertEquals(expectedName, vet.getName());
    }

    public Object[] parametersForGetsPetEntity() {
        return $(
            $("Leo", 1),
            $("Basil", 2),
            $("Rosy", 3),
            $("Jewel", 4)
        );
    }
}
