package martin.karle.petclinic.services.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by carlosmartinez on 10/8/18 23:40
 */
public abstract class AbstractMapService<T, ID> {

  protected Map<ID, T> map = new HashMap<>();

  Set<T> findAll() {
    return new HashSet<>(map.values());
  }

  T findById(final ID id) {
    return map.get(id);
  }

  T save(final ID id, final T object) {
    map.put(id, object);
    return object;
  }

  void deleteById(ID id) {
    map.remove(id);
  }

  void delete(final T object) {
    map.entrySet().removeIf(entry -> entry.getValue().equals(object));
  }
}
