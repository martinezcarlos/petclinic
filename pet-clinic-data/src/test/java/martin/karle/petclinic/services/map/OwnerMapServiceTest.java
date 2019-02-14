package martin.karle.petclinic.services.map;

import java.util.Set;
import martin.karle.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Created by carlosmartinez on 2019-01-29 20:52
 */
class OwnerMapServiceTest {

  private static final Long OWNER_ID = 1L;
  private static final String OWNER_LAST_NAME = "Smith";
  private OwnerMapService ownerMapService;

  @BeforeEach
  void setUp() {
    ownerMapService = new OwnerMapService(new PetMapService(new PetTypeMapService()));
    ownerMapService.save(Owner.builder().id(OWNER_ID).lastName(OWNER_LAST_NAME).build());
  }

  @Test
  void findAll() {
    final Set<Owner> owners = ownerMapService.findAll();
    assertEquals(1, owners.size());
  }

  @Test
  void findById() {
    final Owner owner = ownerMapService.findById(OWNER_ID);
    assertEquals(OWNER_ID, owner.getId());
  }

  @Test
  void saveExistingId() {
    final Long id = 2L;
    final Owner owner = Owner.builder().id(id).build();
    final Owner savedOwner = ownerMapService.save(owner);
    assertEquals(id, savedOwner.getId());
  }

  @Test
  void saveNoId() {
    final Owner savedOwner = ownerMapService.save(Owner.builder().build());
    assertNotNull(savedOwner);
    assertNotNull(savedOwner.getId());
  }

  @Test
  void deleteById() {
    ownerMapService.deleteById(OWNER_ID);
    assertNull(ownerMapService.findById(OWNER_ID));
  }

  @Test
  void delete() {
    ownerMapService.delete(ownerMapService.findById(OWNER_ID));
    assertEquals(0, ownerMapService.findAll().size());
  }

  @Test
  void findByLastName() {
    final Owner smith = ownerMapService.findByLastName(OWNER_LAST_NAME);
    assertNotNull(smith);
    assertEquals(OWNER_ID, smith.getId());
  }
}
