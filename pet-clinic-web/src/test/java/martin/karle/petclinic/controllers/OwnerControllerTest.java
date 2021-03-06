package martin.karle.petclinic.controllers;

import java.util.ArrayList;
import java.util.Collections;
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

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
  void findOwners() throws Exception {
    mockMvc.perform(get("/owners/find"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/findOwners"))
        .andExpect(model().attributeExists("owner"));
    verifyZeroInteractions(ownerService);
  }

  @Test
  void processFindOwnerReturnMany() throws Exception {
    when(ownerService.findAllByLastName(anyString())).thenReturn(new ArrayList<>(owners));

    mockMvc.perform(get("/owners"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/ownersList"))
        .andExpect(model().attribute("selections", hasSize(2)));
  }

  @Test
  void processFindOwnerReturnOne() throws Exception {
    when(ownerService.findAllByLastName(anyString())).thenReturn(
        Collections.singletonList(Owner.builder().id(1L).build()));

    mockMvc.perform(get("/owners"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/owners/1"));
  }

  @Test
  void showOwner() throws Exception {
    when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

    mockMvc.perform(get("/owners/123"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/ownerDetails"))
        .andExpect(model().attribute("owner", hasProperty("id", is(1L))));
  }

  @Test
  void initCreationForm() throws Exception {
    mockMvc.perform(get("/owners/new"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/createOrUpdateOwnerForm"))
        .andExpect(model().attributeExists("owner"));
    verifyZeroInteractions(ownerService);
  }

  @Test
  void processCreationForm() throws Exception {
    when(ownerService.save(any())).thenReturn(Owner.builder().id(1L).build());

    mockMvc.perform(post("/owners/new"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/owners/1"))
        .andExpect(model().attributeExists("owner"));

    verify(ownerService).save(any());
  }

  @Test
  void initUpdateOwnerForm() throws Exception {
    when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1l).build());

    mockMvc.perform(get("/owners/1/edit"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/createOrUpdateOwnerForm"))
        .andExpect(model().attributeExists("owner"));

    verifyZeroInteractions(ownerService);
  }

  @Test
  void processUpdateOwnerForm() throws Exception {
    when(ownerService.save(any())).thenReturn(Owner.builder().id(1L).build());

    mockMvc.perform(post("/owners/1/edit"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/owners/1"))
        .andExpect(model().attributeExists("owner"));

    verify(ownerService).save(any());
  }
}
