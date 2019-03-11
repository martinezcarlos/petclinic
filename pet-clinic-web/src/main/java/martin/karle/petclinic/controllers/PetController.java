package martin.karle.petclinic.controllers;

import java.util.Collection;
import javax.validation.Valid;
import martin.karle.petclinic.model.Owner;
import martin.karle.petclinic.model.Pet;
import martin.karle.petclinic.model.PetType;
import martin.karle.petclinic.services.OwnerService;
import martin.karle.petclinic.services.PetService;
import martin.karle.petclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by carlosmartinez on 2019-03-11 16:00
 */
@RequestMapping("/owners/{ownerId}")
@Controller
public class PetController {
  private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

  private final OwnerService ownerService;
  private final PetService petService;
  private final PetTypeService petTypeService;

  public PetController(final OwnerService ownerService, final PetService petService,
      final PetTypeService petTypeService) {
    this.ownerService = ownerService;
    this.petService = petService;
    this.petTypeService = petTypeService;
  }

  @ModelAttribute("types")
  public Collection<PetType> populatePetTypes() {
    return petTypeService.findAll();
  }

  @ModelAttribute("owner")
  public Owner findOwner(@PathVariable final Long ownerId) {
    return ownerService.findById(ownerId);
  }

  @InitBinder
  public void initOwnerBinder(final WebDataBinder dataBinder) {
    dataBinder.setDisallowedFields("id");
  }

  @GetMapping("/pets/new")
  public ModelAndView initCreationForm(final Owner owner) {
    final Pet pet = Pet.builder().build();
    pet.setOwner(owner);
    owner.getPets().add(pet);

    final ModelAndView mav = new ModelAndView(VIEWS_PETS_CREATE_OR_UPDATE_FORM);
    mav.addObject(pet);
    return mav;
  }

  @PostMapping("/pets/new")
  public String processCreationForm(final Owner owner, @Valid final Pet pet,
      final BindingResult result) {
    if (StringUtils.hasLength(pet.getName())
        && pet.isNew()
        && owner.getPet(pet.getName(), true) != null) {
      result.rejectValue("name", "duplicate", "already exists");
    }
    if (result.hasErrors()) {
      return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }
    pet.setOwner(owner);
    owner.getPets().add(pet);
    petService.save(pet);
    return "redirect:/owners/" + owner.getId();
  }

  @GetMapping("/pets/{petId}/edit")
  public ModelAndView initUpdateForm(@PathVariable final Long petId) {
    final ModelAndView mav = new ModelAndView(VIEWS_PETS_CREATE_OR_UPDATE_FORM);
    mav.addObject(petService.findById(petId));
    return mav;
  }

  @PostMapping("/pets/{petId}/edit")
  public String processUpdateForm(final Owner owner, @Valid final Pet pet,
      final BindingResult result) {
    if (result.hasErrors()) {
      return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }
    owner.getPets().add(pet);
    petService.save(pet);
    return "redirect:/owners/" + owner.getId();
  }
}
