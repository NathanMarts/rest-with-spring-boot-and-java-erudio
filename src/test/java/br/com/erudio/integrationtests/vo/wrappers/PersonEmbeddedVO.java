package br.com.erudio.integrationtests.vo.wrappers;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.erudio.integrationtests.vo.PersonVO;

public class PersonEmbeddedVO  implements Serializable {
  
  private static final long serialVersionUID = 1L;

  @JsonProperty("personVOList")
  private List<PersonVO> people;


  public PersonEmbeddedVO() {}

  public List<PersonVO> getPeople() {
    return this.people;
  }

  public void setPeople(List<PersonVO> people) {
    this.people = people;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PersonEmbeddedVO)) {
            return false;
        }
        PersonEmbeddedVO personEmbeddedVO = (PersonEmbeddedVO) o;
        return Objects.equals(people, personEmbeddedVO.people);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(people);
  }

}
