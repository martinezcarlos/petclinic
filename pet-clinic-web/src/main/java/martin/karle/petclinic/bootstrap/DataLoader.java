package martin.karle.petclinic.bootstrap;

import martin.karle.petclinic.model.Owner;
import martin.karle.petclinic.model.Person;
import martin.karle.petclinic.model.Vet;
import martin.karle.petclinic.services.OwnerService;
import martin.karle.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by carlosmartinez on 15/8/18 23:14
 */
@Component
public class DataLoader implements CommandLineRunner {

  private final OwnerService ownerService;
  private final VetService vetService;

  public DataLoader(final OwnerService ownerService, final VetService vetService) {
    this.ownerService = ownerService;
    this.vetService = vetService;
  }

  @Override
  public void run(final String... args) {
    ownerService.save(createPerson(Owner.class, "Michael", "Weston"));
    ownerService.save(createPerson(Owner.class, "Fiona", "Glenanne"));
    System.out.println("Loaded Owners...");

    vetService.save(createPerson(Vet.class, "Sam", "Axe"));
    vetService.save(createPerson(Vet.class, "Jessie", "Porter"));
    System.out.println("Loaded Vets...");
  }

  private <T extends Person> T createPerson(final Class<T> clazz,
      final String firstName, final String lastName) {
    T t = null;
    try {
      t = clazz.newInstance();
      t.setFirstName(firstName);
      t.setLastName(lastName);
    } catch (final InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
    }
    return t;
  }
}
