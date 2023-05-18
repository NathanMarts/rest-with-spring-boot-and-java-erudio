package br.com.erudio.integrationtests.vo.wrappers;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WrapperPersonVO implements Serializable {
  
  private static final long serialVersionUID = 1L;

  @JsonProperty("_embedded")
  private PersonEmbeddedVO embedded;

  public WrapperPersonVO() {}

  public PersonEmbeddedVO getEmbedded() {
    return this.embedded;
  }

  public void setEmbedded(PersonEmbeddedVO embedded) {
    this.embedded = embedded;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof WrapperPersonVO)) {
            return false;
        }
        WrapperPersonVO wrapperPersonVo = (WrapperPersonVO) o;
        return Objects.equals(embedded, wrapperPersonVo.embedded);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(embedded);
  }
}