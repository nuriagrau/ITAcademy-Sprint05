package cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n02.model.services.impl;

import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n02.exceptions.EmptyFlorListException;
import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n02.exceptions.FlorAlreadyExistsException;
import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n02.exceptions.FlorNotFoundException;
import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n02.model.domain.FlorEntity;
import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n02.model.dto.FlorDTO;
import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n02.model.dto.Mapper;
import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n02.model.repository.FlorRepository;
import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n02.model.services.FlorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class FlorServiceImpl implements FlorService {


    @Autowired
    private FlorRepository florRepository;
    private Mapper mapper;


    public FlorServiceImpl(FlorRepository florRepository, Mapper mapper) {
        this.florRepository = florRepository;
        this.mapper = mapper;
    }


    @Override
    public FlorDTO createFlor(FlorDTO florDto) {
        FlorEntity newFlorEntity = mapper.toEntity(florDto);
        florRepository.findByNomFlorIgnoreCase(newFlorEntity.getNomFlor())
                .ifPresent(foundFlorEntity -> {
                    throw  new FlorAlreadyExistsException("The flor" + florDto.getNomFlor() + " already exists");
                });

        return mapper.toDto(florRepository.save(newFlorEntity));
    }


    @Override
    public FlorDTO updateFlor(FlorDTO florDTO) {
        FlorEntity existingFlorEntity = florRepository.findById(florDTO.getPk_FlorID())
                .orElseThrow(() -> new FlorNotFoundException("Flor with id: " + florDTO.getPk_FlorID() + " not found."));
        if (florDTO.getNomFlor() != null && (florDTO.getNomFlor() != "")) {
            existingFlorEntity.setNomFlor(florDTO.getNomFlor());
        }
        if (florDTO.getPaisFlor() != null && (florDTO.getPaisFlor() != "")) {
            existingFlorEntity.setPaisFlor(florDTO.getPaisFlor());
        }

        return mapper.toDto(florRepository.save(existingFlorEntity));
    }


    @Override
    public int deleteFlor(int pk_FlorID) {
        FlorEntity existingFlorEntity = florRepository.findById(pk_FlorID)
                .orElseThrow(() -> new FlorNotFoundException("Flor with id: " + pk_FlorID + " not found."));
        florRepository.delete(existingFlorEntity);

        return pk_FlorID;
    }


    @Override
    public FlorDTO getOneFlor(int pk_FlorID) {

        return mapper.toDto(florRepository.findById(pk_FlorID).orElseThrow(() -> new FlorNotFoundException("Flor with id: " + pk_FlorID + " not found.")));
    }

    @Override
    public List<FlorDTO> getAllFlors() {
        List<FlorEntity> florEntityList = florRepository.findAll();
        if (florEntityList.isEmpty()) {
            throw new EmptyFlorListException("There are no flowers to show.");
        }

        return mapper.toFlorDTOList(florRepository.findAll());
    }
}
