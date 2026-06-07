package com.praxis.service;

import com.praxis.DTO.ExperimentDTO;
import com.praxis.model.Experiment;
import com.praxis.repository.ExperimentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperimentService {

    private final ExperimentRepository experimentRepository;

    public ExperimentService(ExperimentRepository experimentRepository) {
        this.experimentRepository = experimentRepository;
    }

    public List<ExperimentDTO> findAll() {
        return experimentRepository.findAll()
                .stream()
                .map(experiment -> new ExperimentDTO(
                        experiment.getId(),
                        experiment.getName(),
                        experiment.getDescription()
                )).toList();
    }
}
