package com.crud.J2n.controller;

import com.crud.J2n.model.Tutorial;
import com.crud.J2n.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tutorial")
@CrossOrigin(origins = "*")
public class TutorialController {

    @Autowired
    private TutorialRepository repository;

    @GetMapping
    public List<Tutorial> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Tutorial create(@RequestBody Tutorial account) {
        return repository.save(account);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tutorial> update(@PathVariable Long id, @RequestBody Tutorial newTutorial) {
        return repository.findById(id).map(tutorial -> {
            tutorial.setTitle(newTutorial.getTitle());
            tutorial.setDescription(newTutorial.getDescription());
            return ResponseEntity.ok(repository.save(tutorial));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/{id}")
    public Tutorial edit(@PathVariable Long id){
        return repository.getById(id);
    }

}
