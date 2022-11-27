package com.bookstore.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bookstore.Author;
import com.bookstore.api.exceptions.AuthorNotFoundException;
import com.bookstore.api.repositories.AuthorRepository;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
public class AuthorController {
    @Autowired
    AuthorRepository repository;

    @GetMapping("/author")
    public Iterable<Author> all (){
        return repository.findAll();
    }

    @PostMapping("/author")
    public Author newAuthor(@RequestBody Author newAuthor) {
        return repository.save(newAuthor);
    }

    @GetMapping("/author/{id}")
    public Author one(@PathVariable Long id) {
        return repository.findById(id)
                         .orElseThrow(() -> new AuthorNotFoundException(id));
    }

    @PutMapping("/author/{id}")
    public Author replaceAuthor(@RequestBody Author newAuthor, @PathVariable Long id) {

        return repository.findById(id)
                .map(author -> {
                    author.setFirstName(newAuthor.getFirstName());
                    author.setLastName(newAuthor.getLastName());
                    return repository.save(author);
                })

                .orElseGet(() -> {
                    newAuthor.setId(id);
                    return repository.save(newAuthor);
                });
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.ok().build();
            //.body("Author "+ id +" deleted")
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author " + id + " not found");
    }
}
