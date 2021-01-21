package com.tutorials.tutorials.controller.impl;

import com.tutorials.tutorials.controller.TutorialsAPI;
import com.tutorials.tutorials.model.TutorialsVO;
import com.tutorials.tutorials.model.dto.TutorialsDTO;
import com.tutorials.tutorials.repository.TutorialsRepository;
import com.tutorials.tutorials.service.TutorialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TutorialsController implements TutorialsAPI {

    @Autowired
    private TutorialsService tutorialsService;

    @Autowired
    private TutorialsRepository tutorialsRepository;

    @Override
    public ResponseEntity<List<TutorialsDTO>> getAllTutorials() {
        return ResponseEntity.ok( tutorialsService.getAllTutorials() );
    }

    @Override
    public ResponseEntity<List<TutorialsDTO>> getAllTutorials(String titulo) {
       return ResponseEntity.ok( tutorialsService.getAllTutorials(titulo) );
    }

    @Override
    public ResponseEntity<TutorialsDTO> getTutorialById(String id) {

        Optional<TutorialsVO> tutorialData = tutorialsRepository.findById(id);

        return new ResponseEntity(tutorialData.get(), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<TutorialsDTO>> findByPublished() {
        return ResponseEntity.ok( tutorialsService.getByPublished() );
    }

    @Override
    public ResponseEntity<TutorialsDTO> create(TutorialsDTO tutorialsDTO) {

        return new ResponseEntity<>(tutorialsService.create(tutorialsDTO), HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<TutorialsDTO> updateTutorial(String id, TutorialsDTO tutorialsDTO) {
        return new ResponseEntity<>(tutorialsService.update(id, tutorialsDTO), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Boolean> deleteTutorial(String id) {

        return tutorialsService.delete( id )
                ? ResponseEntity.ok( true )
                : new ResponseEntity <>( false, HttpStatus.NOT_FOUND );

    }

    @Override
    public ResponseEntity<Boolean> deleteAllTutorials() {
        return tutorialsService.deleteAll()
                ? ResponseEntity.ok( true )
                : new ResponseEntity <>( false, HttpStatus.NOT_FOUND );
    }
}
