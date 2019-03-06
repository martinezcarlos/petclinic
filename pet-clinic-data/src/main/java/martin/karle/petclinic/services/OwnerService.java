package martin.karle.petclinic.services;

import java.util.List;
import martin.karle.petclinic.model.Owner;

/**
 * Created by carlosmartinez on 5/8/18 21:33
 */
public interface OwnerService extends CrudService<Owner, Long> {

  Owner findByLastName(String lastName);

  List<Owner> findAllByLastNameLike(String lastName);
}
