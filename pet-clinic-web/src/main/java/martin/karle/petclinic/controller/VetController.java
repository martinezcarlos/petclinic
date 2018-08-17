package martin.karle.petclinic.controller;

import martin.karle.petclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by carlosmartinez on 11/8/18 1:02
 */
@RequestMapping("/vets")
@Controller
public class VetController {

  private final VetService vetService;

  public VetController(final VetService vetService) {
    this.vetService = vetService;
  }

  @RequestMapping({"", "/", "/index", "/index.html"})
  public String listVets(final Model model) {
    model.addAttribute("vets", vetService.findAll());
    return "vets/index";
  }
}
