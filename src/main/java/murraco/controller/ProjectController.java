package murraco.controller;

import io.swagger.annotations.*;
import murraco.dto.ProjectResponseDTO;
import murraco.exception.CustomException;
import murraco.model.Project;
import murraco.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projects")
@Api(tags = "projects")
public class ProjectController {

  @Autowired
  private ProjectService projectService;

  @Autowired
  private ModelMapper modelMapper;

  @GetMapping(value = "")
  @ApiOperation(
          value = "${ProjectController.list}",
          authorizations = { @Authorization(value="apiKey") })
  @ApiResponses(value = {//
          @ApiResponse(code = 400, message = "Something went wrong"), //
          @ApiResponse(code = 403, message = "Access denied"), //
          @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public List<ProjectResponseDTO> list() {
    return projectService.find().stream()
            .map( p -> modelMapper.map(p, ProjectResponseDTO.class))
            .collect(Collectors.toList());
  }

  @RequestMapping(value = "/{id}/delete")
  @ApiOperation(
          value = "${ProjectController.delete}",
          httpMethod = "POST",
          authorizations = { @Authorization(value="apiKey") })
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 404, message = "The project doesn't exist"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public ProjectResponseDTO delete(@ApiParam("ID") @PathVariable int id) throws SQLException, IOException {
    Project p = projectService.findById(id).orElseThrow(() -> new CustomException("The project doesn't exist", HttpStatus.NOT_FOUND));

    projectService.delete(p);
    return modelMapper.map(p, ProjectResponseDTO.class);
  }
}
