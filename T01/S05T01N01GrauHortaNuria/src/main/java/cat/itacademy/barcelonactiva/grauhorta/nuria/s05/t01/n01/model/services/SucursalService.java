package cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n01.model.services;

import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n01.model.domain.Sucursal;
import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n01.model.dto.SucursalDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SucursalService {

    SucursalDTO createSucursal(SucursalDTO sucursalDto);

    SucursalDTO updateSucursal(int pk_SucursalID, SucursalDTO sucursalDto);

    Integer deleteSucursal(int pk_SucursalID);

    SucursalDTO getOneSucursal(int pk_SucursalID);

    List<SucursalDTO> getAllSucursals();

}
