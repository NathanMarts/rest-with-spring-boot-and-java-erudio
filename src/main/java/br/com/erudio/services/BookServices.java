package br.com.erudio.services;

import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.erudio.controllers.BookController;
import br.com.erudio.data.vo.v2.BookVO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.exceptions.ResouceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.model.Book;
import br.com.erudio.repositories.BookRepository;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class BookServices {
  private Logger logger = Logger.getLogger(BookServices.class.getName());

  @Autowired
  private BookRepository bookRepository;

  public List<BookVO> findAll() {
    logger.info("Finding all books.");

    var books = DozerMapper.parseListObjects(bookRepository.findAll(), BookVO.class);
    books.stream().forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
    return books;
  }

  public BookVO findById(Long id) {
    logger.info("Finding one book.");

    var entity = bookRepository.findById(id)
        .orElseThrow(() -> new ResouceNotFoundException("No records found for this ID"));

    BookVO vo = DozerMapper.parseObject(entity, BookVO.class);
    vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());

    return vo;
  }

  public BookVO create(BookVO book) {
    logger.info("Creating one book V2!");

    if (book == null) throw new RequiredObjectIsNullException();

    var entity = DozerMapper.parseObject(book, Book.class);
    var vo = DozerMapper.parseObject(bookRepository.save(entity), BookVO.class);
    vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());

    return vo;
  }

  public BookVO update(BookVO book) {
    logger.info("Updating one book!");
    
    if (book == null) throw new RequiredObjectIsNullException();

    var entity = bookRepository.findById(book.getKey())
        .orElseThrow(() -> new ResouceNotFoundException("No records found for this ID"));

    entity.setAuthor(book.getAuthor());
    entity.setLaunchDate(book.getLaunchDate());
    entity.setPrice(book.getPrice());
    entity.setTitle(book.getTitle());

    BookVO vo = DozerMapper.parseObject(bookRepository.save(entity), BookVO.class);
    vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());

    return vo;
  }

  public void delete(long id) {
    logger.info("Deleting one book!");

    var entity = bookRepository.findById(id)
        .orElseThrow(() -> new ResouceNotFoundException("No records found for this ID"));

    bookRepository.delete(entity);
  }
}
