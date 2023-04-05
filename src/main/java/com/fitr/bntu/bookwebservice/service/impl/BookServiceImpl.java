package com.fitr.bntu.bookwebservice.service.impl;

import com.fitr.bntu.bookwebservice.DTO.AuthorDTO;
import com.fitr.bntu.bookwebservice.DTO.BookDTO;
import com.fitr.bntu.bookwebservice.DTO.GenreDTO;
import com.fitr.bntu.bookwebservice.entity.Author;
import com.fitr.bntu.bookwebservice.entity.Book;
import com.fitr.bntu.bookwebservice.entity.Genre;
import com.fitr.bntu.bookwebservice.repository.AuthorRepository;
import com.fitr.bntu.bookwebservice.repository.BookRepository;
import com.fitr.bntu.bookwebservice.repository.GenreRepository;
import com.fitr.bntu.bookwebservice.service.BookService;
import com.fitr.bntu.bookwebservice.service.ServiceException;
import com.fitr.bntu.bookwebservice.validator.AuthorValidator;
import com.fitr.bntu.bookwebservice.validator.BookValidator;
import com.fitr.bntu.bookwebservice.validator.PaginationValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger logger = LogManager.getLogger();

    private final BookValidator bookValidator;

    private final AuthorValidator authorValidator;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final BookRepository repository;

    private final PaginationValidator validator;

    private final ModelMapper mapper;

    @Autowired
    public BookServiceImpl(BookValidator bookValidator, AuthorValidator authorValidator, AuthorRepository authorRepository, GenreRepository genreRepository, BookRepository repository, PaginationValidator validator, ModelMapper mapper) {
        this.bookValidator = bookValidator;
        this.authorValidator = authorValidator;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.repository = repository;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public BookDTO add(BookDTO bookDTO) {
        if(!bookValidator.isBookValid(bookDTO.getName(),"bookDTO.getImagePath()",bookDTO.getCost().toString(),bookDTO.getGenre().toString())) {
            throw new ServiceException("Invalid data when add book");
        }
        Book book = convertFromDTO(bookDTO);
        authorValidator.isAuthorValid(book.getAuthor());
        return convertToDTO(repository.save(book));
    }

    @Override
    public BookDTO update(BookDTO bookDTO) {
        return convertToDTO(repository.save(convertFromDTO(bookDTO)));
    }

    @Override
    public BookDTO update(Integer id, String name, AuthorDTO author, BigDecimal cost, GenreDTO genre, String imagePath) {

        Author author1 = mapper.map(author, Author.class);
        Genre genre1 = mapper.map(genre, Genre.class);
        Book book = new Book(id, name, imagePath, cost, genre1, author1, false);
        if(!authorValidator.isAuthorValid(book.getAuthor())) {
            throw new ServiceException("Invalid data when update book");
        }
        return convertToDTO(repository.save(book));
    }

    @Override
    public List<BookDTO> findAllByParameters2(String name, int pageNumber, int number){
        int firstElement = pageNumber * number - number;
        int lastElement = pageNumber * number;
        List<Book> result;
        result =repository.findAllByParameters2(name, firstElement, lastElement);
        return result
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> findAllByParameters(String name, Author author, Genre genre, int pageNumber, int numberOfElementsPerPage) {
        if (pageNumber < 1 || numberOfElementsPerPage < 1 ) {
            throw new ServiceException("Invalid page number");
        }

        if (name == null) name = "";
        if (author == null) author = new Author(0, null, null);
        if (genre == null) genre = new Genre(0, null);

        String authorName = author.getName();
        String authorLastName = author.getLastName();

        List<Book> result;
        if ((authorName == null && authorLastName == null) && genre.getType() == null) {
            result = repository.findAllByNameContaining(name);
        } else if ((authorName != null && authorLastName != null) && genre.getType() == null) {
            result = repository.findAllByNameContainingAndAuthor(name,
                    authorRepository.findByNameAndLastName(authorName, authorLastName));
        } else if ((authorName == null && authorLastName == null) && genre.getType() != null) {
            result = repository.findAllByNameContainingAndGenre(name, genreRepository.findByType(genre.getType()));
        } else {
            result = repository.findAllByNameContainingAndAuthorAndGenre(name,
                    authorRepository.findByNameAndLastName(authorName, authorLastName),
                    genreRepository.findByType(genre.getType()));
        }
        return result
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> findAll(int pageNumber, int numberOfElementsPerPage) {

        if (pageNumber < 1 || numberOfElementsPerPage < 1) {
            throw new ServiceException("Invalid page number");
        }

        return repository.findAllByIsDeleted(PageRequest.of(pageNumber - 1, numberOfElementsPerPage), false)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO findById(Integer id) {
        Book book = repository.findById(id).orElseThrow(() -> new ServiceException("Book is not exist"));
        return convertToDTO(book);
    }

    @Override
    public void deleteById(Integer id) {
        logger.warn("Deleting certificate with id={}", id);
        repository.deleteById(id);
        logger.warn("Certificate deleted");
    }

    public BookDTO convertToDTO(Book book) {
        return mapper.map(book, BookDTO.class);
    }
    public Book convertFromDTO(BookDTO bookDTO) {
        return mapper.map(bookDTO, Book.class);
    }

}
