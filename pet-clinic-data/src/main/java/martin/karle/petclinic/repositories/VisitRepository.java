package martin.karle.petclinic.repositories;

import martin.karle.petclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by carlosmartinez on 2018-12-10 15:00
 */
public interface VisitRepository extends CrudRepository<Visit, Long> {
}
