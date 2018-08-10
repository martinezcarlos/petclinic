package martin.karle.petclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by carlosmartinez on 11/8/18 0:38
 */
@Controller
public class IndexController {

  @RequestMapping({"", "/", "index", "index.html"})
  public String index() {
    return "index";
  }
}
