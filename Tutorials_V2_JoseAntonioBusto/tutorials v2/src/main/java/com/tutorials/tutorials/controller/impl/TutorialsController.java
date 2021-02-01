package com.tutorials.tutorials.controller.impl;

import com.tutorials.tutorials.controller.TutorialsAPI;
import com.tutorials.tutorials.model.TutorialsVO;
import com.tutorials.tutorials.model.dto.TutorialsDTO;
import com.tutorials.tutorials.repository.TutorialsRepository;
import com.tutorials.tutorials.service.TutorialsService;
import com.tutorials.tutorials.service.impl.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@CrossOrigin
@RestController
public class TutorialsController implements TutorialsAPI {

    @Autowired
    private TutorialsService tutorialsService;

    @Autowired
    private TutorialsRepository tutorialsRepository;

    // Añadimos atributo para poder implementar el método de la interfaz TutorialsAPI

    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

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

        if(tutorialData.isPresent())
        return new ResponseEntity(tutorialData.get(), HttpStatus.OK);
        return null;
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

    // Implementamos el listener del evento notificacion

    @EventListener
    public void onNotification (Notification notification) {

        List<SseEmitter> deadEmitters = new ArrayList<>();

        this.emitters.forEach(emitter -> {

            try {

                emitter.send(notification);

            } catch (Exception e) {

                deadEmitters.add(emitter);

            }

        });

        this.emitters.remove(deadEmitters);

    }

    // Implementamos el metodo con el que se enviará la notificacion

    @Override
    public SseEmitter getNewNotification() {

        SseEmitter emitter = new SseEmitter();

        this.emitters.add(emitter);

        emitter.onCompletion(() -> this.emitters.remove(emitter));
        emitter.onTimeout(() -> {

            emitter.complete();
            this.emitters.remove(emitter);

        });

        return emitter;
    }
}
