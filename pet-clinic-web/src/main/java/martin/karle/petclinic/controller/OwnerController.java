package martin.karle.petclinic.controller;

import java.util.List;
import martin.karle.petclinic.model.Owner;
import martin.karle.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by carlosmartinez on 11/8/18 1:02
 */
@RequestMapping("/owners")
@Controller
public class OwnerController {

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

  @RequestMapping
  public String processFindForm(final Owner owner, final BindingResult result, final Model model) {
    String lastName = owner.getLastName();
    if (lastName == null) {
      lastName = "";
    }
    final List<Owner> results = ownerService.findAllByLastName(lastName);
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
  public ModelAndView showOwner(@PathVariable final long ownerId) {
    final ModelAndView mav = new ModelAndView("owners/ownerDetails");
    mav.addObject(ownerService.findById(ownerId));
    return mav;
  }
}
