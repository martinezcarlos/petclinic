package martin.karle.petclinic.repositories;

import martin.karle.petclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by carlosmartinez on 2018-12-10 15:02
 */
public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
