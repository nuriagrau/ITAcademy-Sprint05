package cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n02.model.services;

import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n02.model.dto.FlorDTO;

import java.util.List;

public interface FlorService {


    FlorDTO createFlor(FlorDTO florDto);

    FlorDTO updateFlor(int id, FlorDTO florDTO);

    int deleteFlor(int pk_FlorID);

    FlorDTO getOneFlor(int pk_FlorID);

    List<FlorDTO> getAllFlors();
}
