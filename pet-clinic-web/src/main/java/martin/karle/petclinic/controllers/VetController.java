package martin.karle.petclinic.controllers;

import martin.karle.petclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by carlosmartinez on 11/vets/8/vets/18 1:02
 */
@Controller
public class VetController {

  private final VetService vetService;

  public VetController(final VetService vetService) {
    this.vetService = vetService;
  }

  @RequestMapping({
      "/vets",
      "/vets/index",
      "/vets/index.html",
      "/vets.html"
  })
  public String listVets(final Model model) {
    model.addAttribute("vets", vetService.findAll());
    return "vets/index";
  }
}
