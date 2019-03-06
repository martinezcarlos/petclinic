package martin.karle.petclinic.controller;

import martin.karle.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

  @RequestMapping({
      "",
      "/",
      "/index",
      "/index.html"
  })
  public String listOwners(final Model model) {
    model.addAttribute("owners", ownerService.findAll());
    return "owners/index";
  }

  @RequestMapping("/find")
  public String findOwners() {
    return "notimplemented";
  }

  @GetMapping("/{ownerId}")
  public ModelAndView showOwner(@PathVariable final long ownerId) {
    final ModelAndView mav = new ModelAndView("owners/ownerDetails");
    mav.addObject(ownerService.findById(ownerId));
    return mav;
  }
}
