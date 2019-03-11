package martin.karle.petclinic.controllers;

import java.util.HashSet;
import java.util.Set;
import martin.karle.petclinic.model.Owner;
import martin.karle.petclinic.model.Pet;
import martin.karle.petclinic.model.PetType;
import martin.karle.petclinic.services.OwnerService;
import martin.karle.petclinic.services.PetService;
import martin.karle.petclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by carlosmartinez on 2019-03-11 16:20
 */
@ExtendWith(MockitoExtension.class)
class PetControllerTest {

  @Mock
  private OwnerService ownerService;
  @Mock
  private PetService petService;
  @Mock
  private PetTypeService petTypeService;

  @InjectMocks
  private PetController petController;

  private MockMvc mockMvc;

  private Owner owner;
  private Set<PetType> petTypes;

  @BeforeEach
  void setUp() {
    owner = Owner.builder().id(1L).build();
    petTypes = new HashSet<>();
    petTypes.add(PetType.builder().id(1L).name("Dog").build());
    petTypes.add(PetType.builder().id(2L).name("Cat").build());
    mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
  }

  @Test
  void initCreationForm() throws Exception {
    when(ownerService.findById(anyLong())).thenReturn(owner);
    when(petTypeService.findAll()).thenReturn(petTypes);

    mockMvc.perform(get("/owners/1/pets/new"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("owner"))
        .andExpect(model().attributeExists("pet"))
        .andExpect(view().name("pets/createOrUpdatePetForm"));
  }

  @Test
  void processCreationForm() throws Exception {
    when(ownerService.findById(anyLong())).thenReturn(owner);
    when(petTypeService.findAll()).thenReturn(petTypes);

    mockMvc.perform(post("/owners/1/pets/new"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/owners/1"));

    verify(petService).save(any());
  }

  @Test
  void initUpdateForm() throws Exception {
    when(ownerService.findById(anyLong())).thenReturn(owner);
    when(petService.findById(anyLong())).thenReturn(Pet.builder().id(1L).build());
    when(petTypeService.findAll()).thenReturn(petTypes);

    mockMvc.perform(get("/owners/1/pets/1/edit"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("owner"))
        .andExpect(model().attributeExists("pet"))
        .andExpect(view().name("pets/createOrUpdatePetForm"));
  }

  @Test
  void processUpdateForm() throws Exception {
    when(ownerService.findById(anyLong())).thenReturn(owner);
    when(petTypeService.findAll()).thenReturn(petTypes);

    mockMvc.perform(post("/owners/1/pets/1/edit"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/owners/1"));

    verify(petService).save(any());
  }
}
