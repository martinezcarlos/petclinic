package martin.karle.petclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import martin.karle.petclinic.model.Vet;
import martin.karle.petclinic.repositories.VetRepository;
import martin.karle.petclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by carlosmartinez on 2018-12-10 15:26
 */
@Service
@Profile("springdatajpa")
@RequiredArgsConstructor
public class VetSDJpaService implements VetService {

  private final VetRepository vetRepository;

  @Override
  public Set<Vet> findAll() {
    final Set<Vet> vets = new HashSet<>();
    vetRepository.findAll().iterator().forEachRemaining(vets::add);
    return vets;
  }

  @Override
  public Vet findById(final Long aLong) {
    return vetRepository.findById(aLong).orElse(null);
  }

  @Override
  public Vet save(final Vet object) {
    return vetRepository.save(object);
  }

  @Override
  public void delete(final Vet object) {
    vetRepository.delete(object);
  }

  @Override
  public void deleteById(final Long aLong) {
    vetRepository.deleteById(aLong);
  }
}
