package com.tutorials.tutorials.service.impl;

import com.tutorials.tutorials.model.TutorialsVO;
import com.tutorials.tutorials.model.dto.TutorialsDTO;
import com.tutorials.tutorials.repository.TutorialsRepository;
import com.tutorials.tutorials.service.TutorialsService;
import com.tutorials.tutorials.service.converter.TutorialsConverterToDTO;
import com.tutorials.tutorials.service.converter.TutorialsConverterToVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

        return tutorialsConverterToDTO.convert(tutorialsRepository.insert(tutorialsVO));

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
}
