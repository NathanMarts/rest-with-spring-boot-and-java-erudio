package br.com.erudio.data.vo.v2;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

@JsonPropertyOrder({ "id", "firstName", "lastName", "address", "gender", "birthday" })
public class PersonVOV2 extends RepresentationModel<PersonVOV2> implements Serializable {
  
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  @Mapping("id")
  private Long key;
  private String firstName;
  private String lastName;
  private String address;
  private String gender;
  private Date birthday;

  public PersonVOV2() {
  }

  public Date getBirthday() {
    return this.birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
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
        if (!(o instanceof PersonVOV2)) {
            return false;
        }
        PersonVOV2 personVOV2 = (PersonVOV2) o;
        return Objects.equals(key, personVOV2.key) && Objects.equals(firstName, personVOV2.firstName) && Objects.equals(lastName, personVOV2.lastName) && Objects.equals(address, personVOV2.address) && Objects.equals(gender, personVOV2.gender) && Objects.equals(birthday, personVOV2.birthday);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, firstName, lastName, address, gender, birthday);
  }

}
