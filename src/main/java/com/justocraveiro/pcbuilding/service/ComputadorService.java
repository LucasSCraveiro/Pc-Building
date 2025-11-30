package com.justocraveiro.pcbuilding.service;

import com.justocraveiro.pcbuilding.dto.ComputadorFormDto;
import com.justocraveiro.pcbuilding.model.Computador;

public interface ComputadorService {
    Computador createFromDto(ComputadorFormDto dto);
    Computador updateFromDto(Long id, ComputadorFormDto dto);
}
