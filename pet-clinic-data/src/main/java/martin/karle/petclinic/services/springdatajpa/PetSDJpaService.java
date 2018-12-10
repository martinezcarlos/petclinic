package martin.karle.petclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import martin.karle.petclinic.model.Pet;
import martin.karle.petclinic.repositories.PetRepository;
import martin.karle.petclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by carlosmartinez on 2018-12-10 15:34
 */
@Service
@Profile("springdatajpa")
@RequiredArgsConstructor
public class PetSDJpaService implements PetService {

  private final PetRepository petRepository;

  @Override
  public Set<Pet> findAll() {
    final Set<Pet> petSet = new HashSet<>();
    petRepository.findAll().iterator().forEachRemaining(petSet::add);
    return petSet;
  }

  @Override
  public Pet findById(final Long aLong) {
    return petRepository.findById(aLong).orElse(null);
  }

  @Override
  public Pet save(final Pet object) {
    return petRepository.save(object);
  }

  @Override
  public void delete(final Pet object) {
    petRepository.delete(object);
  }

  @Override
  public void deleteById(final Long aLong) {
    petRepository.deleteById(aLong);
  }
}
