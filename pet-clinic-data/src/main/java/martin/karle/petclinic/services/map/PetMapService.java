package martin.karle.petclinic.services.map;

import java.util.Set;
import martin.karle.petclinic.model.Pet;
import martin.karle.petclinic.services.PetService;
import martin.karle.petclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by carlosmartinez on 10/8/18 23:54
 */
@Service
@Profile({
    "default",
    "map"
})
public class PetMapService extends AbstractMapService<Pet, Long> implements PetService {

  private final PetTypeService petTypeService;

  public PetMapService(final PetTypeService petTypeService) {
    this.petTypeService = petTypeService;
  }

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
    if (entity == null) {
      throw new RuntimeException("The entity being saved cannot be null");
    }
    if (entity.getPetType() == null) {
      throw new RuntimeException("The PetType is requiered");
    }
    if (entity.getPetType().getId() == null) {
      petTypeService.save(entity.getPetType());
    }
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
