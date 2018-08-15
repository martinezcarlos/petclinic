package martin.karle.petclinic.bootstrap;

import martin.karle.petclinic.model.Owner;
import martin.karle.petclinic.model.Person;
import martin.karle.petclinic.model.Vet;
import martin.karle.petclinic.services.OwnerService;
import martin.karle.petclinic.services.VetService;
import martin.karle.petclinic.services.map.OwnerServiceMap;
import martin.karle.petclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by carlosmartinez on 15/8/18 23:14
 */
@Component
public class DataLoader implements CommandLineRunner {

  private final OwnerService ownerService;
  private final VetService vetService;

  public DataLoader() {
    ownerService = new OwnerServiceMap();
    vetService = new VetServiceMap();
  }

  @Override
  public void run(final String... args) {
    ownerService.save(createPerson(Owner.class, 1L, "Michael", "Weston"));
    ownerService.save(createPerson(Owner.class, 2L, "Fiona", "Glenanne"));
    System.out.println("Loaded Owners...");

    vetService.save(createPerson(Vet.class, 1L, "Sam", "Axe"));
    vetService.save(createPerson(Vet.class, 2L, "Jessie", "Porter"));
    System.out.println("Loaded Vets...");
  }

  private <T extends Person> T createPerson(final Class<T> clazz, final Long id,
      final String firstName, final String lastName) {
    T t = null;
    try {
      t = clazz.newInstance();
      t.setId(id);
      t.setFirstName(firstName);
      t.setLastName(lastName);
    } catch (final InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
    }
    return t;
  }
}
