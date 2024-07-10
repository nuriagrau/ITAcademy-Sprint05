package cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n01.model.dto;

import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n01.enums.SucursalType;

import java.util.Arrays;
import java.util.List;

public class SucursalDTO{

    private int pk_SucursalID;
    private String nomSucursal;
    private String paisSucursal;
    private String tipusSucursal;
    private static List<String> euCountries;

    {
        euCountries = Arrays.asList("Austria", "Belgium", "Bulgaria", "Croatia", "Cyprus", "Czechia", "Denmark", "Estonia", "Finland", "France", "Germany", "Greece", "Hungary", "Ireland", "Italy", "Latvia", "Lithuania", "Luxembourg", "Malta", "Netherlands", "Poland", "Portugal", "Romania", "Slovakia", "Slovenia", "Spain", "Sweden");
    }

    public SucursalDTO() {};

    public SucursalDTO( String nomSucursal, String paisSucursal) {
        this.nomSucursal = nomSucursal;
        this.paisSucursal = paisSucursal;
        this.tipusSucursal = findTipusSucursal(paisSucursal);
    }
    public SucursalDTO(int pk_SucursalID, String nomSucursal, String paisSucursal) {
        this.pk_SucursalID = pk_SucursalID;
        this.nomSucursal = nomSucursal;
        this.paisSucursal = paisSucursal;
        this.tipusSucursal = findTipusSucursal(paisSucursal);
    }

    public int getPk_SucursalID() {
        return pk_SucursalID;
    }

    public void setPk_SucursalID(int pk_SucursalID) {
        this.pk_SucursalID = pk_SucursalID;
    }

    public String getNomSucursal() {
        return nomSucursal;
    }

    public void setNomSucursal(String nomSucursal) {
        this.nomSucursal = nomSucursal;
    }

    public String getPaisSucursal() {
        return paisSucursal;
    }

    public void setPaisSucursal(String paisSucursal) {
        this.paisSucursal = paisSucursal;
    }

    public String getTipusSucursal() {
        return tipusSucursal;
    }

    public void setTipusSucursal(String tipusSucursal) {
        this.tipusSucursal = tipusSucursal;
    }

    public static String findTipusSucursal(String paisSucursal){
       return (euCountries.contains(paisSucursal)? SucursalType.UE: SucursalType.ForaUE).toString();
    };




}
