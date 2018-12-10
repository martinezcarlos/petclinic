package martin.karle.petclinic.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public class Person extends BaseEntity {

  @Column(name = "first_name")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;
}
