package martin.karle.petclinic.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by carlosmartinez on 3/9/18 21:48
 */
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(exclude = {"pet"})
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {

  @Column(name = "date")
  private LocalDate date;
  @Column(name = "description")
  private String description;
  @ManyToOne
  @JoinColumn(name = "pet_id")
  private Pet pet;
}
