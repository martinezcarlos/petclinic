package martin.karle.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

/**
 * Created by carlosmartinez on 8/9/18 13:57
 */
@Entity
@Table(name = "specialties")
@Data
public class Specialty extends BaseEntity {

  @Column(name = "description")
  private final String description;
}
