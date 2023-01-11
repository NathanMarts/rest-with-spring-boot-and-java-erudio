package br.com.erudio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.services.PersonServicesV2;
import br.com.erudio.util.MediaType;

@RestController
@RequestMapping("/person/v2")
public class PersonControllerV2 {

  @Autowired
  private PersonServicesV2 personService;

  @GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
  public List<PersonVOV2> findAll() {
    return personService.findAll();
  }

  @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
      MediaType.APPLICATION_YML })
  public PersonVOV2 findById(@PathVariable(name = "id") Long id) {
    return personService.findById(id);
  }

  @PostMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
      MediaType.APPLICATION_YML }, produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
          MediaType.APPLICATION_YML })
  public PersonVOV2 createV2(@RequestBody PersonVOV2 person) {
    return personService.create(person);
  }

  @PutMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
      MediaType.APPLICATION_YML }, produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
          MediaType.APPLICATION_YML })
  public PersonVOV2 update(@RequestBody PersonVOV2 person) {
    return personService.update(person);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
    personService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
