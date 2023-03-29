package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exceptions.ResouceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonServices {
  private Logger logger = Logger.getLogger(PersonServices.class.getName());

  @Autowired
  private PersonRepository personRepository;

  public List<PersonVO> findAll() {
    logger.info("Finding all people.");

    return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
  }

  public PersonVO findById(Long id) {
    logger.info("Finding one person.");

    var entity = personRepository.findById(id)
        .orElseThrow(() -> new ResouceNotFoundException("No records found for this ID"));

    return DozerMapper.parseObject(entity, PersonVO.class);
  }

  public PersonVO create(PersonVO person) {
    logger.info("Creating one person!");
    var entity = DozerMapper.parseObject(person, Person.class);
    return DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
  }

  public PersonVO update(PersonVO person) {
    logger.info("Updating one person!");

    var entity = personRepository.findById(person.getKey())
        .orElseThrow(() -> new ResouceNotFoundException("No records found for this ID"));

    entity.setFirstName(person.getFirstName());
    entity.setLastName(person.getLastName());
    entity.setAddress(person.getAddress());
    entity.setGender(person.getGender());

    return DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
  }

  public void delete(long id) {
    logger.info("Deleting one person!");

    var entity = personRepository.findById(id)
        .orElseThrow(() -> new ResouceNotFoundException("No records found for this ID"));

    personRepository.delete(entity);
  }
}
