package martin.karle.petclinic.services.map;

import java.util.Set;
import martin.karle.petclinic.model.PetType;
import martin.karle.petclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

/**
 * Created by carlosmartinez on 8/9/18 14:55
 */
@Service
public class PetTypeServiceMap extends AbstractMapService<PetType, Long> implements PetTypeService {

  @Override
  public Set<PetType> findAll() {
    return super.findAll();
  }

  @Override
  public PetType findById(final Long id) {
    return super.findById(id);
  }

  @Override
  public PetType save(final PetType object) {
    return super.save(object);
  }

  @Override
  public void deleteById(final Long id) {
    super.deleteById(id);
  }

  @Override
  public void delete(final PetType object) {
    super.delete(object);
  }
}
