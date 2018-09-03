package martin.karle.petclinic.controller;

import martin.karle.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
