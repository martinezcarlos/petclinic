package martin.karle.petclinic.repositories;

import martin.karle.petclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by carlosmartinez on 2018-12-10 14:59
 */
public interface VetRepository extends CrudRepository<Vet, Long> {
}
