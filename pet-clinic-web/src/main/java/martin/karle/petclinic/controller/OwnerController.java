package martin.karle.petclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by carlosmartinez on 11/8/18 1:02
 */
@RequestMapping("/owners")
@Controller
public class OwnerController {

  @RequestMapping({"", "/", "/index", "/index.html"})
  public String listOwners() {
    return "owners/index";
  }
}
