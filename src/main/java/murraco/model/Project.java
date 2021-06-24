package murraco.model;

import javax.persistence.*;

@Entity
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }

  @Column(unique = true, nullable = false)
  private String name;
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  @ManyToOne
  private User owner;
  public User getOwner() { return owner; }
  public void setOwner(User owner) { this.owner = owner; }
}
