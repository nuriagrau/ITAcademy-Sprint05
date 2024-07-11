package cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n01.model.dto;

import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n01.model.domain.Sucursal;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Collectors;


public class Mapper {

    public SucursalDTO toDto(Sucursal sucursal) {

        int pk_SucursalID = sucursal.getPk_SucursalID();

        String nomSucursal = sucursal.getNomSucursal();

        String paisSucursal = sucursal.getPaisSucursal();

        SucursalDTO sucursalDTO = new SucursalDTO(pk_SucursalID, nomSucursal, paisSucursal);
        return sucursalDTO;
    }

    public Sucursal toSucursal(SucursalDTO sucursaldto) {
        return new Sucursal(sucursaldto.getNomSucursal(), sucursaldto.getPaisSucursal());
    }

    public List<SucursalDTO> toSucursalDTOList(List<Sucursal> sucursalList) {
        List<SucursalDTO> sucursalDTOList = sucursalList.stream()
                .map(sucursal -> toDto(sucursal))
                .collect(Collectors.toList());
        return sucursalDTOList;
    }

}
