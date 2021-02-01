package com.tutorials.tutorials.service.impl;

import com.tutorials.tutorials.model.TutorialsVO;
import com.tutorials.tutorials.model.dto.TutorialsDTO;
import com.tutorials.tutorials.repository.TutorialsRepository;
import com.tutorials.tutorials.service.TutorialsService;
import com.tutorials.tutorials.service.converter.TutorialsConverterToDTO;
import com.tutorials.tutorials.service.converter.TutorialsConverterToVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TutorialsServiceImpl implements TutorialsService {

    @Autowired
    private TutorialsRepository tutorialsRepository;

    @Autowired
    private TutorialsConverterToDTO tutorialsConverterToDTO;

    @Autowired
    private TutorialsConverterToVO tutorialsConverterToVO;

    // Añadimos atributo para el evento notificacion

    public final ApplicationEventPublisher eventPublisher;

    // Implementacion de ApplicationEventPublisher en un constructor

    public TutorialsServiceImpl (ApplicationEventPublisher eventPublisher) {

        this.eventPublisher = eventPublisher;

    }

    @Override
    public List<TutorialsDTO> getAllTutorials() {
        return tutorialsRepository.findAll()
                .stream()
                .map(tutorialsConverterToDTO :: convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<TutorialsDTO> getAllTutorials(String titulo) {
        return tutorialsRepository.findByTitulo(titulo)
                .stream()
                .map(tutorialsConverterToDTO :: convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<TutorialsDTO> getByPublished() {
        return tutorialsRepository.findAll()
                .stream()
                .filter(TutorialsDTO -> TutorialsDTO.isPublicado() == true)
                .map(tutorialsConverterToDTO :: convert)
                .collect(Collectors.toList());
    }

    @Override
    public TutorialsDTO create(TutorialsDTO tutorials) {

        TutorialsVO tutorialsVO = tutorialsConverterToVO.convert(tutorials);

        // Insertamos el metodo publisNotifications para cuando creamos un nuevo
        // listado, notificamos a los demás usuarios de que se va a insertar un
        // nuevo tutorial

        try {

            publishNotifications();
            return tutorialsConverterToDTO.convert(tutorialsRepository.insert(tutorialsVO));

        } catch (Exception e) {

            return tutorialsConverterToDTO.convert(tutorialsRepository.insert(tutorialsVO));

        }

    }

    @Override
    public TutorialsDTO update(String id, TutorialsDTO tutorials) {

        Optional<TutorialsVO> tutorialData = tutorialsRepository.findById(id);

        TutorialsVO tutorialsVO = tutorialData.get();
        tutorialsVO.setTitulo(tutorials.getTitulo());
        tutorialsVO.setDescripcion(tutorials.getDescripcion());
        tutorialsVO.setPublicado(tutorials.isPublicado());

        return tutorialsConverterToDTO.convert(tutorialsRepository.save(tutorialsVO));

    }

    @Override
    public boolean delete(String id) {

        try {

            tutorialsRepository.deleteById(id);
            return Boolean.TRUE;

        } catch (Exception e) {

            return Boolean.FALSE;

        }

    }

    @Override
    public boolean deleteAll() {

        try {

            tutorialsRepository.deleteAll();
            return Boolean.TRUE;

        } catch (Exception e) {

            return Boolean.FALSE;

        }


    }

    // Declaramos metodo que creara la notificacion
    //@Scheduled (fixedRate = 4000, initialDelay = 2000)
    public void publishNotifications() throws InterruptedException {

        Notification nCome = new Notification( "Un nuevo tutorial se ha creado " , new Date());

        this.eventPublisher.publishEvent(nCome);

        //Thread.sleep(2000);

    }

}
