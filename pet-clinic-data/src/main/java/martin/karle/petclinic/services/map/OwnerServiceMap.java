package martin.karle.petclinic.services.map;

import java.util.Set;
import martin.karle.petclinic.model.Owner;
import martin.karle.petclinic.services.OwnerService;
import org.springframework.stereotype.Service;

/**
 * Created by carlosmartinez on 10/8/18 23:54
 */
@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

  @Override
  public Set<Owner> findAll() {
    return super.findAll();
  }

  @Override
  public Owner findById(final Long id) {
    return super.findById(id);
  }

  @Override
  public Owner save(final Owner entity) {
    return super.save(entity);
  }

  @Override
  public void deleteById(final Long id) {
    super.deleteById(id);
  }

  @Override
  public void delete(final Owner entity) {
    super.delete(entity);
  }

  @Override
  public Owner findByLastName(final String lastName) {
    return null;
  }
}
