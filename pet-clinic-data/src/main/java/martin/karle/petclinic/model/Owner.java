package martin.karle.petclinic.model;

import java.util.Set;
import lombok.Data;

@Data
public class Owner extends Person {

  private Set<Pet> pets;
}
