package com.fitr.bntu.bookwebservice.service.impl;

import com.fitr.bntu.bookwebservice.DTO.AuthorDTO;
import com.fitr.bntu.bookwebservice.DTO.BookDTO;
import com.fitr.bntu.bookwebservice.entity.Author;
import com.fitr.bntu.bookwebservice.entity.Book;
import com.fitr.bntu.bookwebservice.repository.AuthorRepository;
import com.fitr.bntu.bookwebservice.service.AuthorService;
import com.fitr.bntu.bookwebservice.service.ServiceException;
import com.fitr.bntu.bookwebservice.validator.AuthorValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private static final Logger logger = LogManager.getLogger();

    private final AuthorRepository repository;

    private final AuthorValidator authorValidator;

    private final ModelMapper mapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository repository, AuthorValidator authorValidator, ModelMapper mapper) {
        this.authorValidator = authorValidator;
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AuthorDTO add(AuthorDTO authorDTO) {
        if(!authorValidator.isAuthorForCreateValid(authorDTO.getName(),authorDTO.getLastName())){
            throw new ServiceException("Invalid Author");
        }
        Author author = convertFromDTO(authorDTO);
        AuthorDTO result;
        if(isExists(authorDTO)){
            result = convertToDTO(repository.findByNameAndLastName(author.getName(),author.getLastName()));
        }
        else {
            result = convertToDTO(repository.save(author));
        }

        return result;
    }


    @Override
    public AuthorDTO update(Integer id, String name, String lastName) {
        Author author = new Author(id, name, lastName);
        authorValidator.isAuthorValid(author);
        return convertToDTO(repository.save(author));
    }

    @Override
    public List<AuthorDTO> findAll(int pageNumber, int numberOfElementsPerPage) {
        if (pageNumber < 1 || numberOfElementsPerPage < 1) {
            throw new ServiceException("Invalid page number");
        }
        return repository.findAll(PageRequest.of(pageNumber - 1, numberOfElementsPerPage)).getContent()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDTO findById(Integer id) {
        Author author = repository.findById(id).orElseThrow(() -> new ServiceException("Author is not exist"));
        return convertToDTO(author) ;
    }

    @Override
    public boolean isExists(AuthorDTO author) {
        return repository.existsByNameAndLastName(author.getName(), author.getLastName());

    }

    public AuthorDTO convertToDTO(Author author) {
        return mapper.map(author, AuthorDTO.class);
    }

    public Author convertFromDTO(AuthorDTO authorDTO) {
        return mapper.map(authorDTO, Author.class);
    }

}
