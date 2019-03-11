package martin.karle.petclinic.formatters;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;
import martin.karle.petclinic.model.PetType;
import martin.karle.petclinic.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

/**
 * Created by carlosmartinez on 2019-03-11 17:32
 */
@Component
public class PetTypeFormatter implements Formatter<PetType> {

  private final PetTypeService petTypeService;

  public PetTypeFormatter(final PetTypeService petTypeService) {
    this.petTypeService = petTypeService;
  }

  @Override
  public PetType parse(final String s, final Locale locale) throws ParseException {
    final Collection<PetType> petTypes = petTypeService.findAll();
    return petTypes.stream()
        .filter(pt -> pt.getName().equalsIgnoreCase(s))
        .findFirst()
        .orElseThrow(() -> new ParseException("type not found " + s, 0));
  }

  @Override
  public String print(final PetType petType, final Locale locale) {
    return petType.getName();
  }
}
