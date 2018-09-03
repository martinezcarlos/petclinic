package martin.karle.petclinic.model;

import java.time.LocalDate;
import lombok.Data;

/**
 * Created by carlosmartinez on 3/9/18 21:48
 */
@Data
public class Visit extends BaseEntity {

  private LocalDate date;
  private String description;
  private Pet pet;
}
