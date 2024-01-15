package cat.itacademy.barcelonactiva.garcia.ana.s05.t01.n01.S05T01N01GarciaAna.model.services;

import cat.itacademy.barcelonactiva.garcia.ana.s05.t01.n01.S05T01N01GarciaAna.model.domain.Sucursal;
import cat.itacademy.barcelonactiva.garcia.ana.s05.t01.n01.S05T01N01GarciaAna.model.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.garcia.ana.s05.t01.n01.S05T01N01GarciaAna.model.repository.SucursalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SucursalServiceImpl implements SucursalService{

    @Autowired
    private SucursalRepository sucursalRepository;

    @Override
    public SucursalDTO addSucursal(SucursalDTO sucursalDTO) {
        Sucursal sucursal = convertirDtoAEntidad(sucursalDTO);
        Sucursal nuevaSucursal = sucursalRepository.save(sucursal);
        return convertirEntidadADto(nuevaSucursal);
    }

    @Override
    public SucursalDTO updateSucursal(Integer id, SucursalDTO sucursalDTO) {
        // Verifica que la sucursal con el ID exista
        Sucursal existingSucursal = sucursalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la sucursal con ID: " + id));

        // Actualiza los campos de la entidad existente con los valores del DTO
        existingSucursal.setNomSucursal(sucursalDTO.getNomSucursal());
        existingSucursal.setPaisSucursal(sucursalDTO.getPaisSucursal());

        // Guarda la entidad actualizada en el repositorio
        Sucursal updatedSucursal = sucursalRepository.save(existingSucursal);

        // Convierte y devuelve el DTO actualizado
        return convertirEntidadADto(updatedSucursal);
    }

    @Override
    public void deleteSucursal(Integer id) {
        Sucursal existingSucursal = sucursalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la sucursal con ID: " + id));

        sucursalRepository.delete(existingSucursal);
    }

    @Override
    public SucursalDTO getOneSucursal(Integer id) {
        Sucursal sucursal = sucursalRepository.findById(id).orElse(null);
        return (sucursal != null) ? convertirEntidadADto(sucursal) : null;
    }

    @Override
    public List<SucursalDTO> getAllSucursales() {
        List<Sucursal> sucursales = sucursalRepository.findAll();
        return sucursales.stream()
                .map(this::convertirEntidadADto)
                .collect(Collectors.toList());
    }

    // Método para convertir de entidad a DTO
    private SucursalDTO convertirEntidadADto(Sucursal sucursal) {
        SucursalDTO sucursalDTO = new SucursalDTO();
        BeanUtils.copyProperties(sucursal, sucursalDTO);
        sucursalDTO.setTipusSucursal(sucursalDTO.calcularTipusSucursal(sucursalDTO.getPaisSucursal()));
        return sucursalDTO;
    }

    // Método para convertir de DTO a entidad
    private Sucursal convertirDtoAEntidad(SucursalDTO sucursalDTO) {
        Sucursal sucursal = new Sucursal();
        BeanUtils.copyProperties(sucursalDTO, sucursal);
        return sucursal;
    }
}

