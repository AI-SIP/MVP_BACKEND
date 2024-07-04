package com.aisip.OnO.backend.service;

import com.aisip.OnO.backend.Dto.Problem.ProblemRegisterDto;
import com.aisip.OnO.backend.Dto.Problem.ProblemResponseDto;

import java.util.List;

public interface ProblemService {

    ProblemResponseDto findProblemByUserId(Long userId, Long problemId);

    List<ProblemResponseDto> findAllProblemsByUserId(Long userId);
    ProblemResponseDto saveProblem(Long userId, ProblemRegisterDto problemRegisterDto);

    void deleteProblem(Long userId, Long problemId);
}
