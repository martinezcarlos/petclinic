package martin.karle.petclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import martin.karle.petclinic.model.Visit;
import martin.karle.petclinic.repositories.VisitRepository;
import martin.karle.petclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by carlosmartinez on 2018-12-10 15:10
 */
@Service
@Profile("springdatajpa")
@RequiredArgsConstructor
public class VisitSDJpaService implements VisitService {

  private final VisitRepository visitRepository;

  @Override
  public Set<Visit> findAll() {
    final Set<Visit> visits = new HashSet<>();
    visitRepository.findAll().forEach(visits::add);
    return visits;
  }

  @Override
  public Visit findById(final Long aLong) {
    return visitRepository.findById(aLong).orElse(null);
  }

  @Override
  public Visit save(final Visit object) {
    return visitRepository.save(object);
  }

  @Override
  public void delete(final Visit object) {
    visitRepository.delete(object);
  }

  @Override
  public void deleteById(final Long aLong) {
    visitRepository.deleteById(aLong);
  }
}
