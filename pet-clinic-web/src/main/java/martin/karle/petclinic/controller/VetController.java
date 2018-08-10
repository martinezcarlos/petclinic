package martin.karle.petclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by carlosmartinez on 11/8/18 1:02
 */
@Controller
public class VetController {

  @RequestMapping({"/vets", "/vets/index", "vets/index.html"})
  public String listVets() {
    return "vets/index";
  }
}
