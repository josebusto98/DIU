package com.tutorials.tutorials.repository;

import com.tutorials.tutorials.model.TutorialsVO;
import com.tutorials.tutorials.model.dto.TutorialsDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorialsRepository extends MongoRepository <TutorialsVO,
        String>{

    List<TutorialsVO> findByTitulo(String titulo);

}
