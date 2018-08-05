package martin.karle.petclinic.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by carlosmartinez on 5/8/18 21:35
 */
@Getter
@Setter
public class BaseEntity implements Serializable {

  private Long id;
}
