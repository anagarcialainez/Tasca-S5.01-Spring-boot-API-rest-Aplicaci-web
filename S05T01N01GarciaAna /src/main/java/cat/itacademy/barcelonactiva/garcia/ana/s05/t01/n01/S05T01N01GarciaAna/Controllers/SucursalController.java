package cat.itacademy.barcelonactiva.garcia.ana.s05.t01.n01.S05T01N01GarciaAna.Controllers;

import cat.itacademy.barcelonactiva.garcia.ana.s05.t01.n01.S05T01N01GarciaAna.model.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.garcia.ana.s05.t01.n01.S05T01N01GarciaAna.model.services.SucursalService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sucursal")
public class SucursalController {
    @Autowired
    private SucursalService sucursalService;

    @PostMapping("/add")
    public ResponseEntity<SucursalDTO> addSucursal(@RequestBody SucursalDTO sucursalDTO) {
        SucursalDTO nuevaSucursal = sucursalService.addSucursal(sucursalDTO);
        return new ResponseEntity<>(nuevaSucursal, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSucursal(@PathVariable Integer id, @RequestBody SucursalDTO sucursalDTO) {
        try {
            SucursalDTO updatedSucursal = sucursalService.updateSucursal(id, sucursalDTO);
            return ResponseEntity.ok(updatedSucursal);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La sucursal con el ID " + id + " no existe.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al procesar la solicitud.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSucursal(@PathVariable Integer id) {
        try {
            sucursalService.deleteSucursal(id);
            return ResponseEntity.ok().body("La sucursal con el ID " + id + " se eliminó correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La sucursal con el ID " + id + " no existe.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al procesar la solicitud.");
        }
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<SucursalDTO> getOneSucursal(@PathVariable Integer id) {
        SucursalDTO sucursal = sucursalService.getOneSucursal(id);

        if (sucursal != null) {
            return new ResponseEntity<>(sucursal, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SucursalDTO>> getAllSucursales() {
        List<SucursalDTO> sucursales = sucursalService.getAllSucursales();
        return new ResponseEntity<>(sucursales, HttpStatus.OK);
    }
}
