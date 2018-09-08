package martin.karle.petclinic.services.map;

import java.util.Set;
import martin.karle.petclinic.model.Specialty;
import martin.karle.petclinic.services.SpecialtyService;
import org.springframework.stereotype.Service;

/**
 * Created by carlosmartinez on 8/9/18 20:13
 */
@Service
public class SpecialtyServiceMap extends AbstractMapService<Specialty, Long>
    implements SpecialtyService {

  @Override
  public Set<Specialty> findAll() {
    return super.findAll();
  }

  @Override
  public Specialty findById(final Long id) {
    return super.findById(id);
  }

  @Override
  public Specialty save(final Specialty object) {
    return super.save(object);
  }

  @Override
  public void deleteById(final Long id) {
    super.deleteById(id);
  }

  @Override
  public void delete(final Specialty object) {
    super.delete(object);
  }
}
