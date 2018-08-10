package martin.karle.petclinic.services;

import java.util.Set;

/**
 * Created by carlosmartinez on 10/8/18 21:25
 */
public interface CrudService<T, ID> {

  Set<T> findAll();

  T findById(ID id);

  T save(T object);

  void delete(T object);

  void deleteById(ID id);
}
