package br.com.erudio.services;

import org.springframework.stereotype.Service;

import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.exceptions.ResouceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.mapper.custom.PersonMapper;
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

    return DozerMapper.parseListObject(personRepository.findAll(), PersonVOV2.class);
  }

  public PersonVOV2 findById(Long id) {
    logger.info("Finding one person.");

    var entity = personRepository.findById(id)
        .orElseThrow(() -> new ResouceNotFoundException("No records found for this ID"));

    return DozerMapper.parseObject(entity, PersonVOV2.class);
  }

  public PersonVOV2 create(PersonVOV2 person) {
    logger.info("Creating one person V2!");
    var entity = personMapper.convertVoToEntity(person);
    return personMapper.convertEntityToVo(personRepository.save(entity));
  }

  public PersonVOV2 update(PersonVOV2 person) {
    logger.info("Updating one person!");

    var entity = personRepository.findById(person.getId())
        .orElseThrow(() -> new ResouceNotFoundException("No records found for this ID"));

    entity.setFirstName(person.getFirstName());
    entity.setLastName(person.getLastName());
    entity.setAddress(person.getAddress());
    entity.setGender(person.getGender());

    return DozerMapper.parseObject(personRepository.save(entity), PersonVOV2.class);
  }

  public void delete(long id) {
    logger.info("Deleting one person!");

    var entity = personRepository.findById(id)
        .orElseThrow(() -> new ResouceNotFoundException("No records found for this ID"));

    personRepository.delete(entity);
  }
}
