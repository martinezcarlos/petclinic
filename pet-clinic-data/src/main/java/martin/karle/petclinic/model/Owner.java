package martin.karle.petclinic.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "owners")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"pets"})
public class Owner extends Person {

  @Column(name = "address")
  private String address;
  @Column(name = "city")
  private String city;
  @Column(name = "telephone")
  private String telephone;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
  private Set<Pet> pets = new HashSet<>();

  @Builder
  public Owner(final Long id, final String firstName, final String lastName, final String address,
      final String city, final String telephone, final Set<Pet> pets) {
    super(id, firstName, lastName);
    this.address = address;
    this.city = city;
    this.telephone = telephone;
    if (pets != null) {
      this.pets = pets;
    }
  }

  public Pet getPet(final String name) {
    return getPet(name, false);
  }

  public Pet getPet(final String name, final boolean ignoreNew) {
    return pets.stream()
        .filter(p -> (!ignoreNew || !p.isNew()) && p.getName().equalsIgnoreCase(name))
        .findFirst()
        .orElse(null);
  }
}
