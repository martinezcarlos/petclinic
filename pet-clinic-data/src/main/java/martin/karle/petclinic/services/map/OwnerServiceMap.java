package martin.karle.petclinic.services.map;

import java.util.Set;
import martin.karle.petclinic.model.Owner;
import martin.karle.petclinic.services.CrudService;

/**
 * Created by carlosmartinez on 10/8/18 23:54
 */
public class OwnerServiceMap extends AbstractMapService<Owner, Long>
    implements CrudService<Owner, Long> {
  
  @Override
  public Set<Owner> findAll() {
    return super.findAll();
  }

  @Override
  public Owner findById(final Long id) {
    return super.findById(id);
  }

  @Override
  public void deleteById(final Long id) {
    super.deleteById(id);
  }

  @Override
  public void delete(final Owner object) {
    super.delete(object);
  }

  @Override
  public Owner save(final Owner object) {
    return super.save(object.getId(), object);
  }
}
