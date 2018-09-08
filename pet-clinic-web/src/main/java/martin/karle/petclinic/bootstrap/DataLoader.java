package martin.karle.petclinic.bootstrap;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import martin.karle.petclinic.model.Owner;
import martin.karle.petclinic.model.Person;
import martin.karle.petclinic.model.Pet;
import martin.karle.petclinic.model.PetType;
import martin.karle.petclinic.model.Vet;
import martin.karle.petclinic.services.OwnerService;
import martin.karle.petclinic.services.PetTypeService;
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
  private final PetTypeService petTypeService;

  public DataLoader(final OwnerService ownerService, final VetService vetService,
      final PetTypeService petTypeService) {
    this.ownerService = ownerService;
    this.vetService = vetService;
    this.petTypeService = petTypeService;
  }

  @Override
  public void run(final String... args) {
    final PetType dog = new PetType("Dog");
    petTypeService.save(dog);
    final PetType cat = new PetType("Cat");
    petTypeService.save(cat);
    System.out.println("Loaded PetTypes...");

    final Pet mikesPet = createPet("Rosco", dog);
    final Owner michael = createOwner("Michael", "Weston", "Fake str 123", "Madrid", "0000000",
        mikesPet);
    ownerService.save(michael);
    final Pet fionaCat = createPet("Mishi", cat);
    final Owner fiona = createOwner("Fiona", "Glenanne", "Fake str 123", "Madrid", "0000000",
        fionaCat);
    ownerService.save(fiona);
    System.out.println("Loaded Owners...");

    vetService.save(createPerson(Vet.class, "Sam", "Axe"));
    vetService.save(createPerson(Vet.class, "Jessie", "Porter"));
    System.out.println("Loaded Vets...");
  }

  private static Pet createPet(final String name, final PetType petType) {
    final Pet pet = new Pet();
    pet.setName(name);
    pet.setPetType(petType);
    pet.setBirthDate(LocalDate.now());
    return pet;
  }

  private static Owner createOwner(final String firstName, final String lastName,
      final String address, final String city, final String telephone, final Pet... pets) {
    final Owner owner = new Owner();
    owner.setAddress(address);
    owner.setCity(city);
    owner.setTelephone(telephone);
    owner.setPets(new HashSet<>(Arrays.asList(pets)));
    owner.setFirstName(firstName);
    owner.setLastName(lastName);
    return owner;
  }

  private static <T extends Person> T createPerson(final Class<T> clazz, final String firstName,
      final String lastName) {
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
