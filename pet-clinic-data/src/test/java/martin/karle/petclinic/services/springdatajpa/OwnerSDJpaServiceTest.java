package martin.karle.petclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import martin.karle.petclinic.model.Owner;
import martin.karle.petclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by carlosmartinez on 2019-01-29 20:55
 */
@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

  private static final String LAST_NAME = "Smith";
  @Mock
  private OwnerRepository ownerRepository;
  @InjectMocks
  private OwnerSDJpaService service;

  private Owner owner;

  @BeforeEach
  void setUp() {
    owner = Owner.builder().id(1L).lastName(LAST_NAME).build();
  }

  @Test
  void findByLastName() {
    final Owner owner = Owner.builder().id(1L).lastName(LAST_NAME).build();

    when(ownerRepository.findByLastName(any())).thenReturn(owner);

    final Owner smith = service.findByLastName(LAST_NAME);

    verify(ownerRepository).findByLastName(any());
    assertEquals(LAST_NAME, smith.getLastName());
  }

  @Test
  void findAll() {
    final Set<Owner> ownersResponse = new HashSet<>();
    ownersResponse.add(Owner.builder().id(1L).build());
    ownersResponse.add(Owner.builder().id(2L).build());

    when(ownerRepository.findAll()).thenReturn(ownersResponse);

    final Set<Owner> owners = service.findAll();

    assertNotNull(owners);
    assertEquals(2, owners.size());
  }

  @Test
  void findById() {
    when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner));

    final Owner owner = service.findById(1L);

    assertNotNull(owner);
  }

  @Test
  void findByIdNotFound() {
    when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

    final Owner owner = service.findById(1L);

    assertNull(owner);
  }

  @Test
  void save() {
    final Owner ownerToSave = Owner.builder().id(1L).build();

    when(ownerRepository.save(any())).thenReturn(owner);

    final Owner savedOwner = service.save(ownerToSave);

    assertNotNull(savedOwner);
    verify(ownerRepository).save(any());
  }

  @Test
  void delete() {
    service.delete(owner);

    verify(ownerRepository, times(1)).delete(any());
  }

  @Test
  void deleteById() {
    service.deleteById(1L);

    verify(ownerRepository).deleteById(anyLong());
  }
}
