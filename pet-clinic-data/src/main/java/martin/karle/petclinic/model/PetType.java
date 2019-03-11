package martin.karle.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "types")
public class PetType extends BaseEntity {

  @Column(name = "name")
  private String name;

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


