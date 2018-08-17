package martin.karle.petclinic.services.map;

import java.util.Set;
import martin.karle.petclinic.model.Pet;
import martin.karle.petclinic.services.PetService;
import org.springframework.stereotype.Service;

/**
 * Created by carlosmartinez on 10/8/18 23:54
 */
@Service
public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {

  @Override
  public Set<Pet> findAll() {
    return super.findAll();
  }

  @Override
  public Pet findById(final Long id) {
    return super.findById(id);
  }

  @Override
  public Pet save(final Pet entity) {
    return super.save(entity);
  }

  @Override
  public void deleteById(final Long id) {
    super.deleteById(id);
  }

  @Override
  public void delete(final Pet entity) {
    super.delete(entity);
  }
}
