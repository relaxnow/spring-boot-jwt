package murraco.dto;

import io.swagger.annotations.ApiModelProperty;
import murraco.model.Role;

import java.util.List;

public class ProjectResponseDTO {

  @ApiModelProperty(position = 0)
  private Integer id;
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }

  @ApiModelProperty(position = 1)
  private String name;
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  @ApiModelProperty(position = 2)
  private String owner;
  public String getOwner() { return owner; }
  public void setOwner(String owner) { this.owner = owner; }
}
