package br.com.erudio.integrationtests.vo.wrappers;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WrapperBookVO implements Serializable {
  
  private static final long serialVersionUID = 1L;

  @JsonProperty("_embedded")
  private BookEmbeddedVO embedded;

  public WrapperBookVO() {}

  public BookEmbeddedVO getEmbedded() {
    return this.embedded;
  }

  public void setEmbedded(BookEmbeddedVO embedded) {
    this.embedded = embedded;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof WrapperBookVO)) {
            return false;
        }
        WrapperBookVO wrapperBookVo = (WrapperBookVO) o;
        return Objects.equals(embedded, wrapperBookVo.embedded);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(embedded);
  }
}