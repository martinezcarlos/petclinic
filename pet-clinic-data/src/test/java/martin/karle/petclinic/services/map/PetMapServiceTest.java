package martin.karle.petclinic.services.map;

import java.util.Set;
import martin.karle.petclinic.model.Pet;
import martin.karle.petclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * JUnit5 test.
 *
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
class PetMapServiceTest {

  private final Long petId = 1L;
  private PetMapService petMapService;

  @BeforeEach
  void setUp() {

    petMapService = new PetMapService(new PetTypeMapService());

    petMapService.save(Pet.builder().id(petId).petType(PetType.builder().build()).build());
  }

  @Test
  void findAll() {

    final Set<Pet> petSet = petMapService.findAll();

    assertEquals(1, petSet.size());
  }

  @Test
  void findByIdExistingId() {

    final Pet pet = petMapService.findById(petId);

    assertEquals(petId, pet.getId());
  }

  @Test
  void findByIdNotExistingId() {

    final Pet pet = petMapService.findById(5L);

    assertNull(pet);
  }

  @Test
  void findByIdNullId() {

    final Pet pet = petMapService.findById(null);

    assertNull(pet);
  }

  @Test
  void saveExistingId() {

    final Long id = 2L;

    final Pet pet2 = Pet.builder().id(id).petType(PetType.builder().build()).build();

    final Pet savedPet = petMapService.save(pet2);

    assertEquals(id, savedPet.getId());
  }

  @Test
  void saveDuplicateId() {

    final Long id = 1L;

    final Pet pet2 = Pet.builder().id(id).petType(PetType.builder().build()).build();

    final Pet savedPet = petMapService.save(pet2);

    assertEquals(id, savedPet.getId());
    assertEquals(1, petMapService.findAll().size());
  }

  @Test
  void saveNoId() {

    final Pet savedPet = petMapService.save(
        Pet.builder().petType(PetType.builder().build()).build());

    assertNotNull(savedPet);
    assertNotNull(savedPet.getId());
    assertEquals(2, petMapService.findAll().size());
  }

  @Test
  void deletePet() {

    petMapService.delete(petMapService.findById(petId));

    assertEquals(0, petMapService.findAll().size());
  }

  @Test
  void deleteWithWrongId() {

    final Pet pet = Pet.builder().id(5L).build();

    petMapService.delete(pet);

    assertEquals(1, petMapService.findAll().size());
  }

  @Test
  void deleteWithNullId() {

    final Pet pet = Pet.builder().build();

    petMapService.delete(pet);

    assertEquals(1, petMapService.findAll().size());
  }

  @Test
  void deleteNull() {

    petMapService.delete(null);

    assertEquals(1, petMapService.findAll().size());
  }

  @Test
  void deleteByIdCorrectId() {

    petMapService.deleteById(petId);

    assertEquals(0, petMapService.findAll().size());
  }

  @Test
  void deleteByIdWrongId() {

    petMapService.deleteById(5L);

    assertEquals(1, petMapService.findAll().size());
  }

  @Test
  void deleteByIdNullId() {

    petMapService.deleteById(null);

    assertEquals(1, petMapService.findAll().size());
  }
}
