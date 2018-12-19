package martin.karle.petclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import martin.karle.petclinic.model.Specialty;
import martin.karle.petclinic.repositories.SpecialtyRepository;
import martin.karle.petclinic.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by carlosmartinez on 2018-12-10 15:26
 */
@Service
@Profile("springdatajpa")
@RequiredArgsConstructor
public class SpecialtySDJpaService implements SpecialtyService {

  private final SpecialtyRepository specialtyRepository;

  @Override
  public Set<Specialty> findAll() {
    final Set<Specialty> specialties = new HashSet<>();
    specialtyRepository.findAll().forEach(specialties::add);
    return specialties;
  }

  @Override
  public Specialty findById(final Long aLong) {
    return specialtyRepository.findById(aLong).orElse(null);
  }

  @Override
  public Specialty save(final Specialty object) {
    return specialtyRepository.save(object);
  }

  @Override
  public void delete(final Specialty object) {
    specialtyRepository.delete(object);
  }

  @Override
  public void deleteById(final Long aLong) {
    specialtyRepository.deleteById(aLong);
  }
}
