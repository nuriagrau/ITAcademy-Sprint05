package cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n01.model.services.impl;

import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n01.exceptions.SucursalAlreadyExistsException;
import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n01.exceptions.SucursalNotFoundException;
import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n01.model.domain.Sucursal;
import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n01.model.dto.Mapper;
import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n01.model.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n01.model.repository.SucursalRepository;
import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n01.model.services.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class SucursalServiceImpl implements SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;
    private Mapper mapper;

    public SucursalServiceImpl(SucursalRepository sucursalRepository, Mapper mapper) {
        this.sucursalRepository = sucursalRepository;
        this.mapper = mapper;
    }

    @Override
    public SucursalDTO createSucursal(SucursalDTO sucursalDto) {
        Sucursal newSucursal = mapper.toSucursal(sucursalDto);
        sucursalRepository.findBynomSucursalIgnoreCase(newSucursal.getNomSucursal())
                .ifPresent(foundSucursal -> {
                    throw new SucursalAlreadyExistsException("The sucursal " + sucursalDto.getNomSucursal() + " already exists");
                });
        return mapper.toDto(sucursalRepository.save(newSucursal));
    }

    @Override
    public SucursalDTO updateSucursal(int pk_SucursalID, SucursalDTO sucursalDTO) {
        /*if (int == null) {
            throw new IllegalArgumentException("Sucursal Id cannot be null");
        }*/
        Sucursal existingSucursal = sucursalRepository.findById(pk_SucursalID)
                .orElseThrow(()-> new SucursalNotFoundException("Sucursal with id " + pk_SucursalID + " not found."));
        if (sucursalDTO.getNomSucursal() != null && (sucursalDTO.getNomSucursal() != "")) {
            existingSucursal.setNomSucursal(sucursalDTO.getNomSucursal());
        }
        if (sucursalDTO.getPaisSucursal() != null && (sucursalDTO.getPaisSucursal() != "")) {
            existingSucursal.setPaisSucursal(sucursalDTO.getPaisSucursal());
        }
        return mapper.toDto(sucursalRepository.save(existingSucursal));
    }

    @Override
    public Integer deleteSucursal(int pk_SucursalID) {
        Sucursal existingSucursal = sucursalRepository.findById(pk_SucursalID)
                .orElseThrow(() -> new SucursalNotFoundException("Sucursal with id " + pk_SucursalID + " not found."));
        sucursalRepository.deleteById(existingSucursal.getPk_SucursalID());
        return pk_SucursalID;
    }

    @Override
    public SucursalDTO getOneSucursal(int id) {;
        return mapper.toDto(sucursalRepository.findById(id).orElseThrow(() -> new SucursalNotFoundException("Sucursal with id " + id + " not found")));
    }

    @Override
    public List<SucursalDTO> getAllSucursals() {
        List<SucursalDTO> dtoSucursals = sucursalRepository.findAll().stream()
                .map(mapper::toDto)
                .collect(toList());
        return dtoSucursals;
    }



}
