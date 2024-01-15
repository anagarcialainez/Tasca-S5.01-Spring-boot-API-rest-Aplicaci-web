package cat.itacademy.barcelonactiva.garcia.ana.s05.t01.n01.S05T01N01GarciaAna.model.services;

import cat.itacademy.barcelonactiva.garcia.ana.s05.t01.n01.S05T01N01GarciaAna.model.dto.SucursalDTO;

import java.util.List;

public interface SucursalService {
    SucursalDTO addSucursal(SucursalDTO sucursalDTO);

    SucursalDTO updateSucursal(Integer id, SucursalDTO sucursalDTO);

    void deleteSucursal(Integer id);

    SucursalDTO getOneSucursal(Integer id);

    List<SucursalDTO> getAllSucursales();
}
