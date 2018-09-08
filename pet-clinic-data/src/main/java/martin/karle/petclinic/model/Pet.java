package martin.karle.petclinic.model;

import java.time.LocalDate;
import lombok.Data;

@Data
public class Pet extends BaseEntity {

  private String name;
  private PetType petType;
  private Owner owner;
  private LocalDate birthDate;
}
