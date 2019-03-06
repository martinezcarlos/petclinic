package martin.karle.petclinic.repositories;

import java.util.List;
import martin.karle.petclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by carlosmartinez on 2018-12-10 14:58
 */
public interface OwnerRepository extends CrudRepository<Owner, Long> {

  Owner findByLastName(String lastName);

  List<Owner> findAllByLastNameLike(String lastName);
}
