package martin.karle.petclinic.services.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import martin.karle.petclinic.model.BaseEntity;

/**
 * Created by carlosmartinez on 10/8/18 23:40
 */
public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {

  protected Map<Long, T> map = new HashMap<>();

  Set<T> findAll() {
    return new HashSet<>(map.values());
  }

  T findById(final ID id) {
    return map.get(id);
  }

  T save(final T entity) {
    if (entity != null) {
      if (entity.getId() == null) {
        entity.setId(getNextId());
      }
      map.put(entity.getId(), entity);
    } else {
      throw new RuntimeException("The entity being saved cannot be null");
    }
    return entity;
  }

  private Long getNextId() {
    Long nextId;
    try {
      nextId = Collections.max(map.keySet()) + 1;
    } catch (final NoSuchElementException e) {
      nextId = 1L;
    }
    return nextId;
  }

  void deleteById(final ID id) {
    map.remove(id);
  }

  void delete(final T entity) {
    map.entrySet().removeIf(entry -> entry.getValue().equals(entity));
  }
}
