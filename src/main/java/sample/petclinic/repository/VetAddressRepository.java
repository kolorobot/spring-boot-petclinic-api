package sample.petclinic.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import sample.petclinic.model.VetAddress;

public interface VetAddressRepository extends PagingAndSortingRepository<VetAddress, Integer> {

}
