package martin.karle.petclinic.controllers;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.util.Map;
import javax.validation.Valid;
import martin.karle.petclinic.model.Pet;
import martin.karle.petclinic.model.Visit;
import martin.karle.petclinic.services.PetService;
import martin.karle.petclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by carlosmartinez on 2019-03-11 18:19
 */
@RequestMapping("/owners/{ownerId}/pets")
@Controller
public class VisitController {

  private final VisitService visitService;
  private final PetService petService;

  public VisitController(final VisitService visitService, final PetService petService) {
    this.visitService = visitService;
    this.petService = petService;
  }

  @InitBinder
  public void dataBinder(final WebDataBinder dataBinder) {
    dataBinder.setDisallowedFields("id");

    dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
      @Override
      public void setAsText(final String text) throws IllegalArgumentException {
        setValue(LocalDate.parse(text));
      }
    });
  }

  /**
   * Called before each and every @RequestMapping annotated method. 2 goals: - Make sure we always
   * have fresh data - Since we do not use the session scope, make sure that Pet object always has
   * an id (Even though id is not part of the form fields)
   *
   * @param petId
   *
   * @return Pet
   */
  @ModelAttribute("visit")
  public Visit loadPetWithVisit(@PathVariable final Long petId, final Map<String, Object> model) {
    final Pet pet = petService.findById(petId);
    model.put("pet", pet);
    final Visit visit = new Visit();
    visit.setPet(pet);
    pet.getVisits().add(visit);
    return visit;
  }

  // Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is called
  @GetMapping("/{petId}/visits/new")
  public String initNewVisitForm() {
    return "pets/createOrUpdateVisitForm";
  }

  // Spring MVC calls method loadPetWithVisit(...) before processNewVisitForm is called
  @PostMapping("/{petId}/visits/new")
  public String processNewVisitForm(@PathVariable final Long ownerId, @Valid final Visit visit,
      final BindingResult result) {
    if (result.hasErrors()) {
      return "pets/createOrUpdateVisitForm";
    }
    visitService.save(visit);
    return "redirect:/owners/" + ownerId;
  }
}
