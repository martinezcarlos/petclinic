package martin.karle.petclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import martin.karle.petclinic.model.PetType;
import martin.karle.petclinic.repositories.PetTypeRepository;
import martin.karle.petclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by carlosmartinez on 2018-12-10 15:34
 */
@Service
@Profile("springdatajpa")
@RequiredArgsConstructor
public class PetTypeSDJpaService implements PetTypeService {

  private final PetTypeRepository petTypeRepository;

  @Override
  public Set<PetType> findAll() {
    final Set<PetType> petTypeSet = new HashSet<>();
    petTypeRepository.findAll().iterator().forEachRemaining(petTypeSet::add);
    return petTypeSet;
  }

  @Override
  public PetType findById(final Long aLong) {
    return petTypeRepository.findById(aLong).orElse(null);
  }

  @Override
  public PetType save(final PetType object) {
    return petTypeRepository.save(object);
  }

  @Override
  public void delete(final PetType object) {
    petTypeRepository.delete(object);
  }

  @Override
  public void deleteById(final Long aLong) {
    petTypeRepository.deleteById(aLong);
  }
}
