package martin.karle.petclinic.services;

import java.util.Set;
import martin.karle.petclinic.model.Owner;

/**
 * Created by carlosmartinez on 5/8/18 21:33
 */
public interface OwnerService {

  Owner findByLastName(String lastName);

  Owner findById(Long id);

  Owner save(Owner owner);

  Set<Owner> findAll();
}
