package martin.karle.petclinic.model;

import java.io.Serializable;
import lombok.Data;

/**
 * Created by carlosmartinez on 5/8/18 21:35
 */
@Data
public class BaseEntity implements Serializable {

  private Long id;
}
