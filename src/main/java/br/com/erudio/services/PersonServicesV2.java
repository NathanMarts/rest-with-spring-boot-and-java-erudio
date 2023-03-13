package br.com.erudio.services;

import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.erudio.controllers.PersonControllerV2;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.exceptions.ResouceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.mapper.custom.PersonMapper;
import br.com.erudio.models.Person;
import br.com.erudio.repositories.PersonRepository;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PersonServicesV2 {
  private Logger logger = Logger.getLogger(PersonServices.class.getName());

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private PersonMapper personMapper;

  public List<PersonVOV2> findAll() {
    logger.info("Finding all people.");

    var persons = DozerMapper.parseListObjects(personRepository.findAll(), PersonVOV2.class);
    persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonControllerV2.class).findById(p.getKey())).withSelfRel()));
    return persons;
  }

  public PersonVOV2 findById(Long id) {
    logger.info("Finding one person.");

    var entity = personRepository.findById(id)
        .orElseThrow(() -> new ResouceNotFoundException("No records found for this ID"));

    PersonVOV2 vo = DozerMapper.parseObject(entity, PersonVOV2.class);
    vo.add(linkTo(methodOn(PersonControllerV2.class).findById(id)).withSelfRel());

    return vo;
  }

  public PersonVOV2 create(PersonVOV2 person) {
    logger.info("Creating one person V2!");

    if (person == null) throw new RequiredObjectIsNullException();

    var entity = DozerMapper.parseObject(person, Person.class);
    var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVOV2.class);
    vo.add(linkTo(methodOn(PersonControllerV2.class).findById(vo.getKey())).withSelfRel());

    return vo;
  }

  public PersonVOV2 update(PersonVOV2 person) {
    logger.info("Updating one person!");
    
    if (person == null) throw new RequiredObjectIsNullException();

    var entity = personRepository.findById(person.getKey())
        .orElseThrow(() -> new ResouceNotFoundException("No records found for this ID"));

    entity.setFirstName(person.getFirstName());
    entity.setLastName(person.getLastName());
    entity.setAddress(person.getAddress());
    entity.setGender(person.getGender());

    PersonVOV2 vo = DozerMapper.parseObject(personRepository.save(entity), PersonVOV2.class);
    vo.add(linkTo(methodOn(PersonControllerV2.class).findById(vo.getKey())).withSelfRel());

    return vo;
  }

  public void delete(long id) {
    logger.info("Deleting one person!");

    var entity = personRepository.findById(id)
        .orElseThrow(() -> new ResouceNotFoundException("No records found for this ID"));

    personRepository.delete(entity);
  }
}
