package martin.karle.petclinic.services.map;

import java.util.Set;
import martin.karle.petclinic.model.Pet;
import martin.karle.petclinic.model.Visit;
import martin.karle.petclinic.services.VisitService;
import org.springframework.stereotype.Service;

/**
 * Created by carlosmartinez on 2018-12-19 12:42
 */
@Service
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {

  @Override
  public Set<Visit> findAll() {
    return super.findAll();
  }

  @Override
  public Visit findById(final Long id) {
    return super.findById(id);
  }

  @Override
  public Visit save(final Visit entity) {
    if (entity == null) {
      throw new RuntimeException("The entity being saved cannot be null");
    }
    final Pet pet = entity.getPet();
    if (pet == null
        || pet.getId() == null
        || pet.getOwner() == null
        || pet.getOwner().getId() == null) {
      throw new RuntimeException("The entity being saved is not valid");
    }
    return super.save(entity);
  }

  @Override
  public void deleteById(final Long id) {
    super.deleteById(id);
  }

  @Override
  public void delete(final Visit entity) {
    super.delete(entity);
  }
}
