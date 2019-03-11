package martin.karle.petclinic.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.CollectionUtils;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(exclude = {"owner"})
@Table(name = "pets")
@Entity
public class Pet extends BaseEntity {

  @Column(name = "name")
  private String name;
  @ManyToOne
  @JoinColumn(name = "type_id")
  private PetType petType;
  @ManyToOne
  @JoinColumn(name = "owner_id")
  private Owner owner;
  @Column(name = "birth_date")
  private LocalDate birthDate;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
  private Set<Visit> visits = new HashSet<>();

  @Builder
  public Pet(final Long id, final String name, final PetType petType, final Owner owner,
      final LocalDate birthDate, final Set<Visit> visits) {
    super(id);
    this.name = name;
    this.petType = petType;
    this.owner = owner;
    this.birthDate = birthDate;
    if (!CollectionUtils.isEmpty(visits)) {
      this.visits = visits;
    }
  }
}


