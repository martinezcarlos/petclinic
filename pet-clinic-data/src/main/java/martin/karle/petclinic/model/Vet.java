package martin.karle.petclinic.model;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class Vet extends Person {

  private Set<Specialty> specialties = new HashSet<>();
}
