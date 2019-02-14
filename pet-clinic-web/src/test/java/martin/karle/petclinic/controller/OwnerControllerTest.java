package martin.karle.petclinic.controller;

import java.util.HashSet;
import java.util.Set;
import martin.karle.petclinic.model.Owner;
import martin.karle.petclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by carlosmartinez on 2019-02-14 20:07
 */
@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

  @Mock
  private OwnerService ownerService;
  @InjectMocks
  private OwnerController ownerController;
  private MockMvc mockMvc;
  private Set<Owner> owners;

  @BeforeEach
  void setUp() {
    owners = new HashSet<>();
    owners.add(Owner.builder().id(1L).build());
    owners.add(Owner.builder().id(2L).build());

    mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
  }

  @Test
  void listOwners() throws Exception {
    when(ownerService.findAll()).thenReturn(owners);

    mockMvc.perform(get("/owners"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/index"))
        .andExpect(model().attribute("owners", hasSize(2)));
  }

  @Test
  void listOwnersByIndex() throws Exception {
    when(ownerService.findAll()).thenReturn(owners);

    mockMvc.perform(get("/owners/index"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/index"))
        .andExpect(model().attribute("owners", hasSize(2)));
  }

  @Test
  void findOwners() throws Exception {
    mockMvc.perform(get("/owners/find"))
        .andExpect(status().isOk())
        .andExpect(view().name("notimplemented"));
    verifyZeroInteractions(ownerService);
  }
}
