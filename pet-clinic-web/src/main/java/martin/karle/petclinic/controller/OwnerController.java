package martin.karle.petclinic.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import martin.karle.petclinic.model.Owner;
import martin.karle.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by carlosmartinez on 11/8/18 1:02
 */
@RequestMapping("/owners")
@Controller
public class OwnerController {
  private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
  private final OwnerService ownerService;

  public OwnerController(final OwnerService ownerService) {
    this.ownerService = ownerService;
  }

  @InitBinder
  public void setAllowFields(final WebDataBinder dataBinder) {
    dataBinder.setDisallowedFields("id");
  }

  @RequestMapping("/find")
  public String initFindForm(final Model model) {
    model.addAttribute("owner", Owner.builder().build());
    return "owners/findOwners";
  }

  @GetMapping
  public String processFindForm(final Owner owner, final BindingResult result, final Model model) {
    final List<Owner> results = ownerService.findAllByLastName(
        Optional.ofNullable(owner.getLastName()).orElse(""));
    if (results.isEmpty()) {
      result.rejectValue("lastName", "notfound", "Not found");
      return "owners/findOwners";
    } else if (results.size() == 1) {
      return "redirect:/owners/" + results.get(0).getId();
    } else {
      model.addAttribute("selections", results);
      return "owners/ownersList";
    }
  }

  @GetMapping("/{ownerId}")
  public ModelAndView showOwner(@PathVariable final Long ownerId) {
    final ModelAndView mav = new ModelAndView("owners/ownerDetails");
    mav.addObject(ownerService.findById(ownerId));
    return mav;
  }

  @GetMapping("/new")
  public ModelAndView initCreationForm() {
    final ModelAndView mav = new ModelAndView(VIEWS_OWNER_CREATE_OR_UPDATE_FORM);
    mav.addObject(Owner.builder().build());
    return mav;
  }

  @PostMapping("/new")
  public String processCreationForm(@Valid final Owner owner, final BindingResult result) {
    if (result.hasErrors()) {
      return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }
    final Owner savedOwner = ownerService.save(owner);
    return "redirect:/owners/" + savedOwner.getId();
  }

  @GetMapping("/{ownerId}/edit")
  public ModelAndView initUpdateOwnerForm(@PathVariable final Long ownerId) {
    final ModelAndView mav = new ModelAndView(VIEWS_OWNER_CREATE_OR_UPDATE_FORM);
    mav.addObject(ownerService.findById(ownerId));
    return mav;
  }

  @PostMapping("/{ownerId}/edit")
  public String processUpdateOwnerForm(@Valid final Owner owner, @PathVariable final Long ownerId,
      final BindingResult result) {
    if (result.hasErrors()) {
      return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }
    owner.setId(ownerId);
    final Owner savedOwner = ownerService.save(owner);
    return "redirect:/owners/" + savedOwner.getId();
  }
}
