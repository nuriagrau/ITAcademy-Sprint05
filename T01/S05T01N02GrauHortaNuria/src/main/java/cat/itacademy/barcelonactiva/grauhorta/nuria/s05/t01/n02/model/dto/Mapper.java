package cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n02.model.dto;

import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n02.model.domain.FlorEntity;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {


    public FlorDTO toDto(FlorEntity florEntity) {
        int pk_FlorID = florEntity.getPk_FlorID();
        String nomFlor = florEntity.getNomFlor();
        String paisFlor = florEntity.getPaisFlor();

        FlorDTO florDTO = new FlorDTO(pk_FlorID, nomFlor, paisFlor);
        return florDTO;
    }

    public FlorEntity toEntity(FlorDTO florDto) {

        return new FlorEntity(florDto.getNomFlor(), florDto.getPaisFlor());
    }


    public List<FlorDTO> toFlorDTOList(List<FlorEntity> florEntityList) {
        List<FlorDTO> florDTOList = florEntityList.stream()
                .map(flor -> toDto(flor))
                .collect(Collectors.toList());
        return florDTOList;
    }
}
