package cat.itacademy.barcelonactiva.garcia.ana.s05.t01.n01.S05T01N01GarciaAna.model.dto;

import java.util.*;

public class SucursalDTO {
    private Integer pk_SucursalID;
    private String nomSucursal;
    private String paisSucursal;
    private String tipusSucursal;
    private static final List<String> paisesUE = Arrays.asList("Alemania", "Austria", "Bélgica", "Bulgaria", "Chipre", "Croacia", "Dinamarca", "Eslovaquia", "Eslovenia", "España", "Estonia", "Finlandia", "Francia", "Grecia", "Hungría", "Irlanda", "Italia", "Letonia", "Lituania", "Luxemburgo", "Malta", "Países Bajos", "Polonia", "Portugal", "República Checa", "Rumanía", "Suecia");

    // constructores
    public SucursalDTO() {

    }
    public SucursalDTO(Integer pk_SucursalID, String nomSucursal, String paisSucursal) {
        this.pk_SucursalID = pk_SucursalID;
        this.nomSucursal = nomSucursal;
        this.paisSucursal = paisSucursal;
        this.tipusSucursal = calcularTipusSucursal(paisSucursal);
    }

    // Getters y setters
    public Integer getPk_SucursalID() {
        return pk_SucursalID;
    }

    public void setPk_SucursalID(Integer pk_SucursalID) {
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

    //Otros métodos
    public String calcularTipusSucursal(String pais) {
        return paisesUE.contains(pais) ? "UE" : "Fuera de la UE";
    }

}
