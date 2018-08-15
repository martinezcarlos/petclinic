package martin.karle.petclinic.services.map;

import java.util.Set;
import martin.karle.petclinic.model.Vet;
import martin.karle.petclinic.services.VetService;
import org.springframework.stereotype.Service;

/**
 * Created by carlosmartinez on 10/8/18 23:54
 */
@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

  @Override
  public Set<Vet> findAll() {
    return super.findAll();
  }

  @Override
  public Vet findById(final Long id) {
    return super.findById(id);
  }

  @Override
  public void deleteById(final Long id) {
    super.deleteById(id);
  }

  @Override
  public void delete(final Vet object) {
    super.delete(object);
  }

  @Override
  public Vet save(final Vet object) {
    return super.save(object.getId(), object);
  }
}
