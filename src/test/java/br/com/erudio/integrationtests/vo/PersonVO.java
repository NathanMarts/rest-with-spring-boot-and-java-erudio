package br.com.erudio.integrationtests.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class PersonVO implements Serializable {
  
  private static final long serialVersionUID = 1L;

  private Long id;
  private String firstName;
  private String lastName;
  private String address;
  private String gender;
  private Date birthday;

  public PersonVO() {
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public void setAddress(String address) {
    this.address = address;
  }

  public String getGender() {
    return this.gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Date getBirthday() {
    return this.birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PersonVO)) {
            return false;
        }
        PersonVO personVOV2 = (PersonVO) o;
        return Objects.equals(id, personVOV2.id) && Objects.equals(firstName, personVOV2.firstName) && Objects.equals(lastName, personVOV2.lastName) && Objects.equals(address, personVOV2.address) && Objects.equals(gender, personVOV2.gender) && Objects.equals(birthday, personVOV2.birthday);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, address, gender, birthday);
  }


}
