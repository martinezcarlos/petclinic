package martin.karle.petclinic.services;

import java.util.Set;
import martin.karle.petclinic.model.Pet;

/**
 * Created by carlosmartinez on 5/8/18 21:33
 */
public interface PerService {

  Pet findById(Long id);

  Pet save(Pet pet);

  Set<Pet> findAll();
}
