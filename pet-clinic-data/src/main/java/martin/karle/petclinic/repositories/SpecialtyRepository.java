package martin.karle.petclinic.repositories;

import martin.karle.petclinic.model.Specialty;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by carlosmartinez on 2018-12-10 15:02
 */
public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {
}
