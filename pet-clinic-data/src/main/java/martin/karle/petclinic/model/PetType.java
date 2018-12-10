package martin.karle.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "types")
@Data
public class PetType extends BaseEntity {

  @Column(name = "name")
  private final String name;
}
