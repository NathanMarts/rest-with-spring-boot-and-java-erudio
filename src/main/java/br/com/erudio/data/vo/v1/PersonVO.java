package br.com.erudio.data.vo.v1;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.github.dozermapper.core.Mapping;

public class PersonVO extends RepresentationModel<PersonVO> implements Serializable {
  
  private static final long serialVersionUID = 1L;

  @Mapping("id")
  private Long key;
  private String firstName;
  private String lastName;
  private String address;
  private String gender;

  public PersonVO() {
  }

  public Long getKey() {
    return this.key;
  }

  public void setKey(Long key) {
    this.key = key;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String adress) {
    this.address = adress;
  }

  public String getGender() {
    return this.gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PersonVO)) {
            return false;
        }
        PersonVO person = (PersonVO) o;
        return key == person.key && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(address, person.address) && Objects.equals(gender, person.gender);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, firstName, lastName, address, gender);
  }

}
