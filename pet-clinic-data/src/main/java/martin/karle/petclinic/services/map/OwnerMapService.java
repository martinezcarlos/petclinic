package martin.karle.petclinic.services.map;

import java.util.Set;
import martin.karle.petclinic.model.Owner;
import martin.karle.petclinic.services.OwnerService;
import martin.karle.petclinic.services.PetService;
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
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

  private final PetService petService;

  public OwnerMapService(final PetService petService) {
    this.petService = petService;
  }

  @Override
  public Owner findByLastName(final String lastName) {
    return findAll().stream()
        .filter(owner -> owner.getLastName().equalsIgnoreCase(lastName))
        .findFirst()
        .orElse(null);
  }

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
    if (entity == null) {
      throw new RuntimeException("The entity being saved cannot be null");
    }
    if (entity.getPets() != null) {
      entity.getPets().forEach(pet -> {
        if (pet.getId() == null) {
          petService.save(pet);
        }
      });
    }
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
}
