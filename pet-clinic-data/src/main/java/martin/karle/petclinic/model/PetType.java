package martin.karle.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "types")
public class PetType extends BaseEntity {

  @Column(name = "name")
  private final String name;

  @Builder
  public PetType(final Long id, final String name) {
    super(id);
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}


