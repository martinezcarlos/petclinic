package martin.karle.petclinic.services;

import java.util.Set;
import martin.karle.petclinic.model.Vet;

/**
 * Created by carlosmartinez on 5/8/18 21:33
 */
public interface VetService {

  Vet findById(Long id);

  Vet save(Vet owner);

  Set<Vet> findAll();
}
