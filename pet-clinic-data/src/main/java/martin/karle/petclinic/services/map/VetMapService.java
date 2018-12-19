package martin.karle.petclinic.services.map;

import java.util.Set;
import martin.karle.petclinic.model.Vet;
import martin.karle.petclinic.services.SpecialtyService;
import martin.karle.petclinic.services.VetService;
import org.springframework.stereotype.Service;

/**
 * Created by carlosmartinez on 10/8/18 23:54
 */
@Service
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

  private final SpecialtyService specialtyService;

  public VetMapService(final SpecialtyService specialtyService) {
    this.specialtyService = specialtyService;
  }

  @Override
  public Set<Vet> findAll() {
    return super.findAll();
  }

  @Override
  public Vet findById(final Long id) {
    return super.findById(id);
  }

  @Override
  public Vet save(final Vet entity) {
    if (entity == null) {
      throw new RuntimeException("The entity being saved cannot be null");
    }
    if (entity.getSpecialties() != null) {
      entity.getSpecialties().forEach(specialty -> {
        if (specialty.getId() == null) {
          specialtyService.save(specialty);
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
  public void delete(final Vet entity) {
    super.delete(entity);
  }
}
