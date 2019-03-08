package martin.karle.petclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import martin.karle.petclinic.model.Owner;
import martin.karle.petclinic.repositories.OwnerRepository;
import martin.karle.petclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by carlosmartinez on 2018-12-10 15:10
 */
@Service
@Profile("springdatajpa")
@RequiredArgsConstructor
public class OwnerSDJpaService implements OwnerService {

  private final OwnerRepository ownerRepository;

  @Override
  public Owner findByLastName(final String lastName) {
    return ownerRepository.findByLastName(lastName);
  }

  @Override
  public List<Owner> findAllByLastName(final String lastName) {
    return ownerRepository.findAllByLastNameContainingIgnoreCase(lastName);
  }

  @Override
  public Set<Owner> findAll() {
    final Set<Owner> owners = new HashSet<>();
    ownerRepository.findAll().forEach(owners::add);
    return owners;
  }

  @Override
  public Owner findById(final Long aLong) {
    return ownerRepository.findById(aLong).orElse(null);
  }

  @Override
  public Owner save(final Owner object) {
    return ownerRepository.save(object);
  }

  @Override
  public void delete(final Owner object) {
    ownerRepository.delete(object);
  }

  @Override
  public void deleteById(final Long aLong) {
    ownerRepository.deleteById(aLong);
  }
}
