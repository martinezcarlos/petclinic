package martin.karle.petclinic.bootstrap;

import java.time.LocalDate;
import java.util.Arrays;
import martin.karle.petclinic.model.Owner;
import martin.karle.petclinic.model.Person;
import martin.karle.petclinic.model.Pet;
import martin.karle.petclinic.model.PetType;
import martin.karle.petclinic.model.Specialty;
import martin.karle.petclinic.model.Vet;
import martin.karle.petclinic.model.Visit;
import martin.karle.petclinic.services.OwnerService;
import martin.karle.petclinic.services.PetTypeService;
import martin.karle.petclinic.services.SpecialtyService;
import martin.karle.petclinic.services.VetService;
import martin.karle.petclinic.services.VisitService;
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
  private final SpecialtyService specialtyService;
  private final VisitService visitService;

  public DataLoader(final OwnerService ownerService, final VetService vetService,
      final PetTypeService petTypeService, final SpecialtyService specialtyService,
      final VisitService visitService) {
    this.ownerService = ownerService;
    this.vetService = vetService;
    this.petTypeService = petTypeService;
    this.specialtyService = specialtyService;
    this.visitService = visitService;
  }

  private <T extends Person> T createPerson(final Class<T> clazz, final String firstName,
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

  @Override
  public void run(final String... args) {
    if (petTypeService.findAll().size() == 0) {
      loadData();
    }
  }

  private void loadData() {
    final PetType dog = PetType.builder().name("Dog").build();
    petTypeService.save(dog);
    final PetType cat = PetType.builder().name("Cat").build();
    petTypeService.save(cat);
    System.out.println("Loaded PetTypes...");

    final Pet mikesPet = createPet("Rosco", dog);
    final Pet fionaCat = createPet("Mishi", cat);

    final Owner michael = createOwner("Michael", "Weston", "Fake str 123", "Madrid", "0000000",
        mikesPet);
    ownerService.save(michael);
    final Owner fiona = createOwner("Fiona", "Glenanne", "Fake str 123", "Madrid", "0000000",
        fionaCat);
    ownerService.save(fiona);
    System.out.println("Loaded Owners...");

    visitService.save(createVisit(LocalDate.now(), "Rosco's visit", mikesPet));
    visitService.save(createVisit(LocalDate.now(), "Mishi's visit", fionaCat));
    System.out.println("Loaded Visits...");

    final Specialty radiology = new Specialty("Radiology");
    specialtyService.save(radiology);
    final Specialty surgery = new Specialty("Surgery");
    specialtyService.save(surgery);
    final Specialty dentistry = new Specialty("Dentistry");
    specialtyService.save(dentistry);
    System.out.println("Loaded Specialties...");

    vetService.save(createVet("Sam", "Axe", radiology));
    vetService.save(createVet("Jessie", "Porter", surgery));
    System.out.println("Loaded Vets...");
  }

  private Pet createPet(final String name, final PetType petType) {
    final Pet pet = Pet.builder().build();
    pet.setName(name);
    pet.setPetType(petType);
    pet.setBirthDate(LocalDate.now());
    return pet;
  }

  private Owner createOwner(final String firstName, final String lastName, final String address,
      final String city, final String telephone, final Pet... pets) {
    final Owner owner = new Owner();
    owner.setAddress(address);
    owner.setCity(city);
    owner.setTelephone(telephone);
    owner.getPets().addAll(Arrays.asList(pets));
    owner.setFirstName(firstName);
    owner.setLastName(lastName);
    Arrays.stream(pets).forEach(pet -> pet.setOwner(owner));
    return owner;
  }

  private Visit createVisit(final LocalDate visitDate, final String description, final Pet pet) {
    final Visit visit = new Visit();
    visit.setDate(visitDate);
    visit.setDescription(description);
    visit.setPet(pet);
    return visit;
  }

  private Vet createVet(final String firstName, final String lastName,
      final Specialty... specialties) {
    final Vet vet = new Vet();
    vet.getSpecialties().addAll(Arrays.asList(specialties));
    vet.setFirstName(firstName);
    vet.setLastName(lastName);
    return vet;
  }
}
