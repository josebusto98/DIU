package com.tutorials.tutorials.service;

import com.tutorials.tutorials.model.dto.TutorialsDTO;

import java.util.List;

public interface TutorialsService {

    List <TutorialsDTO> getAllTutorials();

    List <TutorialsDTO> getAllTutorials(String titulo);

    List <TutorialsDTO> getByPublished();

    TutorialsDTO create ( final TutorialsDTO tutorials );

    TutorialsDTO update ( String id, final TutorialsDTO tutorials );

    boolean delete ( final String id );

    boolean deleteAll ();

}
