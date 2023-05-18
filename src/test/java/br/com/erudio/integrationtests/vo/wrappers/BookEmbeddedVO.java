package br.com.erudio.integrationtests.vo.wrappers;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.erudio.integrationtests.vo.BookVO;

public class BookEmbeddedVO  implements Serializable {
  
  private static final long serialVersionUID = 1L;

  @JsonProperty("bookVOList")
  private List<BookVO> books;


  public BookEmbeddedVO() {}

  public List<BookVO> getBooks() {
    return this.books;
  }

  public void setBooks(List<BookVO> books) {
    this.books = books;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BookEmbeddedVO)) {
            return false;
        }
        BookEmbeddedVO personEmbeddedVO = (BookEmbeddedVO) o;
        return Objects.equals(books, personEmbeddedVO.books);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(books);
  }

}
