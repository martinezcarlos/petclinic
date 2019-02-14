package martin.karle.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "types")
@Data
@EqualsAndHashCode(callSuper = true)
public class PetType extends BaseEntity {

  @Column(name = "name")
  private final String name;
}
