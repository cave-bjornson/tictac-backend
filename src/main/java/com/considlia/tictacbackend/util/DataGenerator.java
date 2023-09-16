package com.considlia.tictacbackend.util;

import com.considlia.tictacbackend.domain.model.Activity;
import com.considlia.tictacbackend.domain.model.Address;
import com.considlia.tictacbackend.domain.model.Customer;
import com.considlia.tictacbackend.domain.model.Person;
import com.considlia.tictacbackend.domain.model.Project;
import com.considlia.tictacbackend.domain.model.TimeLog;
import com.considlia.tictacbackend.domain.model.User;
import com.considlia.tictacbackend.domain.repository.ActivityRepository;
import com.considlia.tictacbackend.domain.repository.AddressRepository;
import com.considlia.tictacbackend.domain.repository.CustomerRepository;
import com.considlia.tictacbackend.domain.repository.ProjectRepository;
import com.considlia.tictacbackend.domain.repository.TimeLogRepository;
import com.considlia.tictacbackend.domain.repository.UserRepository;
import com.github.javafaker.Faker;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@Profile("dev")
public class DataGenerator implements CommandLineRunner {

    final int MIN_PROJECTS = 5;
    final int MAX_PROJECTS = 10;
    final int MIN_PROJECT_ACTIVITIES = 5;
    final int MAX_PROJECT_ACTIVITIES = 10;
    final int MIN_USERS = 5;
    final int MAX_USERS = 10;
    final int N_PROJECTS, N_PROJECTS_ACTIVITES, N_USERS;

    final UserRepository userRepository;
    final AddressRepository addressRepository;
    final ProjectRepository projectRepository;
    final ActivityRepository activityRepository;
    final CustomerRepository customerRepository;
    final TimeLogRepository timeLogRepository;
    final Faker faker;
    final ModelMapper modelMapper;

    public DataGenerator(UserRepository userRepository, AddressRepository addressRepository, ProjectRepository projectRepository, ActivityRepository activityRepository, CustomerRepository customerRepository, TimeLogRepository timeLogRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.projectRepository = projectRepository;
        this.activityRepository = activityRepository;
        this.customerRepository = customerRepository;
        this.timeLogRepository = timeLogRepository;
        this.faker = Faker.instance(new Locale("sv"), new Random(1));
        this.modelMapper = new ModelMapper();
        N_PROJECTS = faker.random().nextInt(MIN_PROJECTS, MAX_PROJECTS);
        N_PROJECTS_ACTIVITES = faker.random().nextInt(MIN_PROJECT_ACTIVITIES, MAX_PROJECT_ACTIVITIES);
        N_USERS = faker.random().nextInt(MIN_USERS, MAX_USERS);
    }

    class Identity {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = new StringBuilder()
                .append(asciify(firstName))
                .append('.')
                .append(asciify(lastName))
                .append('@')
                .append(faker.pokemon().name().toLowerCase())
                .append('.')
                .append(faker.internet().domainSuffix()).toString();

        String username = asciify(String.valueOf(firstName.toLowerCase().charAt(0))) + asciify(lastName);

        String asciify(String string) {
            return string.toLowerCase()
                         .replace('ö', 'o')
                         .replaceAll("[åä]", "a");
        }
    }

    Activity generateActivity() {
        TimeLog timeLog = TimeLog.builder()
                                 .withDuration(Duration.between(
                                         faker.date().past(4, TimeUnit.HOURS).toInstant(),
                                         faker.date().future(4, TimeUnit.HOURS).toInstant()
                                 ))
                                 .withLocalDateTime(LocalDateTime.ofInstant(faker.date().past(100, TimeUnit.DAYS).toInstant(), ZoneId.systemDefault()))
                                 .withIsReported(faker.random().nextBoolean())
                                 .withIsSubmitted(false)
                                 .build();
        timeLogRepository.save(timeLog);
        Activity activity = Activity.builder()
                                    .withTitle(faker.job().keySkills())
                                    .withTimeLogs(Set.of(timeLog))
                                    .build();
        activityRepository.save(activity);
        timeLog.setActivity(activity);
        timeLogRepository.save(timeLog);
        return activity;
    }

    Project generateProject() {
        Project project = Project.builder()
                                 .withTitle(faker.company().industry())
                                 .build();
        project.setActivities(new HashSet<>());
        project.setUsers(new HashSet<>());
        return projectRepository.save(project);
    }

    Address generateAddress() {
        return addressRepository.save(
                Address.builder()
                       .withCity(faker.address().city())
                       .withPostalCode(faker.address().zipCode())
                       .withStreet(faker.address().streetAddress())
                       .build()
        );
    }

    Person generatePerson(Identity identity) {
        return Person.builder()
                     .withAddress(generateAddress())
                     .withEmail(identity.email)
                     .withFirstName(identity.firstName)
                     .withLastName(identity.lastName)
                     .withPhone(faker.phoneNumber().cellPhone())
                     .build();
    }

    User generateUser() {
        Identity identity = new Identity();
        User user = modelMapper.map(generatePerson(identity), User.class);
        user.setPassword(faker.internet().password());
        user.setUsername(identity.username);
        user.setRole("AUTH_USER");
        user.setProjects(new HashSet<>());
        return userRepository.save(user);
    }

    Customer generateCustomer() {
        Identity identity = new Identity();
        Customer customer = modelMapper.map(generatePerson(identity), Customer.class);
        customer.setProjects(new HashSet<>());
        return customerRepository.save(customer);
    }

    @Override
    public void run(String... args) {
        List<User> userList = new ArrayList<>();
        List<Project> projectList = new ArrayList<>();
        List<Activity> activityList = new ArrayList<>();
        List<Customer> customerList = new ArrayList<>();
        List<Address> addressList = new ArrayList<>();

        for (int i = 0; i < N_PROJECTS; i++) {
            Project project = generateProject();
            projectList.add(project);
            for (int j = 0; j < N_PROJECTS_ACTIVITES; j++) {
                Activity activity = generateActivity();
                activity.setProject(project);
                activityList.add(activity);
            }
            Customer customer = generateCustomer();
            project.setCustomer(customer);
        }
        activityRepository.saveAll(activityList);
        projectRepository.saveAll(projectList);

        for (int i = 0; i < N_USERS; i++) {
            userList.add(generateUser());
        }

        projectList.forEach(project -> {
            userList.get(faker.random().nextInt(0, N_USERS - 1)).getProjects().add(project);
        });
        userRepository.saveAll(userList);
    }
}
