package murraco;

import java.util.ArrayList;
import java.util.Arrays;

import murraco.model.Project;
import murraco.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import murraco.model.Role;
import murraco.model.User;
import murraco.service.UserService;

@SpringBootApplication
public class JwtAuthServiceApp implements CommandLineRunner {

  @Autowired
  UserService userService;

  @Autowired
  ProjectService projectService;

  public static void main(String[] args) {
    SpringApplication.run(JwtAuthServiceApp.class, args);
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Override
  public void run(String... params) throws Exception {
    User admin = new User();
    admin.setUsername("admin");
    admin.setPassword("admin");
    admin.setEmail("admin@email.com");
    admin.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)));

    userService.signup(admin);

    User client = new User();
    client.setUsername("client");
    client.setPassword("client");
    client.setEmail("client@email.com");
    client.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));

    userService.signup(client);

    Project p = new Project();
    p.setId(1);
    p.setName("Example Project 1");
    p.setOwner(admin);
    projectService.create(p);

    Project p2 = new Project();
    p2.setId(2);
    p2.setName("Example Project 2");
    p2.setOwner(client);
    projectService.create(p2);
  }
}
