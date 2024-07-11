package cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n02.model.dto;

import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n02.enums.TipusFlor;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;


@Data
@NoArgsConstructor

public class FlorDTO {

    private int pk_FlorID;

    private String nomFlor;

    private String paisFlor;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "The type of Flor, read-only")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String tipusFlor;


    private static List<String> euCountries;

    {
        euCountries = Arrays.asList("Austria", "Belgium", "Bulgaria", "Croatia", "Cyprus", "Czechia", "Denmark", "Estonia", "Finland", "France", "Germany", "Greece", "Hungary", "Ireland", "Italy", "Latvia", "Lithuania", "Luxembourg", "Malta", "Netherlands", "Poland", "Portugal", "Romania", "Slovakia", "Slovenia", "Spain", "Sweden");
    }


    public FlorDTO(String nomFlor, String paisFlor) {
        this.nomFlor = nomFlor;
        this.paisFlor = paisFlor;
        this.tipusFlor = findTipusFlor(paisFlor);
    }

    public FlorDTO(int pk_FlorID, String nomFlor, String paisFlor) {
        this.pk_FlorID = pk_FlorID;
        this.nomFlor = nomFlor;
        this.paisFlor = paisFlor;
        this.tipusFlor = findTipusFlor(paisFlor);
    }

    public int getPk_FlorID() {
        return pk_FlorID;
    }

    public void setPk_FlorID(int pk_FlorID) {
        this.pk_FlorID = pk_FlorID;
    }

    public String getNomFlor() {
        return nomFlor;
    }

    public void setNomFlor(String nomFlor) {
        this.nomFlor = nomFlor;
    }

    public String getPaisFlor() {
        return paisFlor;
    }

    public void setPaisFlor(String paisFlor) {
        this.paisFlor = paisFlor;
    }

    public String getTipusFlor() {
        return tipusFlor;
    }

    public void setTipusFlor(String tipusFlor) {
        this.tipusFlor = tipusFlor;
    }


    public static String findTipusFlor(String paisFlor){
        return (euCountries.contains(paisFlor)? TipusFlor.UE : TipusFlor.ForaUE).toString();
    };
}
