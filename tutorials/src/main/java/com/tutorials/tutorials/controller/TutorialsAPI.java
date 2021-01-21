package com.tutorials.tutorials.controller;

import com.tutorials.tutorials.controller.constant.EndPointUris;
import com.tutorials.tutorials.model.dto.TutorialsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(EndPointUris.API + EndPointUris.V1 + EndPointUris.TUTORIALS)
public interface TutorialsAPI {

    @GetMapping
    ResponseEntity<List<TutorialsDTO>> getAllTutorials();

    @GetMapping(EndPointUris.NOMBRE + EndPointUris.TITULO)
    ResponseEntity<List<TutorialsDTO>> getAllTutorials(@PathVariable final String titulo);

    @GetMapping(EndPointUris.ID)
    ResponseEntity<TutorialsDTO> getTutorialById (@PathVariable final String id);

    @GetMapping(EndPointUris.PUBLISHED)
    ResponseEntity<List<TutorialsDTO>> findByPublished();

    @PostMapping
    ResponseEntity<TutorialsDTO> create(@RequestBody final TutorialsDTO tutorialsDTO);

    @PutMapping(EndPointUris.ID)
    ResponseEntity<TutorialsDTO> updateTutorial(@PathVariable final String id, @RequestBody final TutorialsDTO tutorialsDTO);

    @DeleteMapping(EndPointUris.ID)
    ResponseEntity<Boolean> deleteTutorial (@PathVariable final String id);

    @DeleteMapping
    ResponseEntity<Boolean> deleteAllTutorials();

}
