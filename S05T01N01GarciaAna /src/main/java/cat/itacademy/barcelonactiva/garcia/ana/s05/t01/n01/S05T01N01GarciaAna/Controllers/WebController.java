package cat.itacademy.barcelonactiva.garcia.ana.s05.t01.n01.S05T01N01GarciaAna.Controllers;

import cat.itacademy.barcelonactiva.garcia.ana.s05.t01.n01.S05T01N01GarciaAna.model.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.garcia.ana.s05.t01.n01.S05T01N01GarciaAna.model.services.SucursalService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sucursales")
public class WebController {
    private final SucursalService sucursalService;

    // Inyección del servicio en el constructor
    @Autowired
    public WebController(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    // Muestra la lista de sucursales
    @GetMapping
    public String listSucursales(Model model) {
        List<SucursalDTO> sucursales = sucursalService.getAllSucursales();
        model.addAttribute("sucursales", sucursales);
        return "sucursales";
    }

    // Muestra el formulario para agregar una nueva sucursal
    @GetMapping("/formularioNuevaSucursal")
    public String showFormularioNuevaSucursal(Model model) {
        model.addAttribute("nuevaSucursal", new SucursalDTO()); // Asegúrate de reemplazar 'NuevaSucursal' con el nombre de tu clase.
        return "formularioNuevaSucursal";
    }

    // Agrega una nueva sucursal
    @PostMapping("/nueva")
    public String addNuevaSucursal(@ModelAttribute SucursalDTO nuevaSucursal) {
        // Puedes manejar la creación de la nueva sucursal aquí
        sucursalService.addSucursal(nuevaSucursal);
        return "redirect:/sucursales";
    }

    //metodo para ver detalles de sucursal
    @GetMapping("/detalles/{id}")
    public String showDetalles(@PathVariable Integer id, Model model) {
        try {
            SucursalDTO sucursalDTO = sucursalService.getOneSucursal(id);
            model.addAttribute("sucursal", sucursalDTO);
            return "detallesSucursal";
        } catch (EntityNotFoundException e) {
            return "redirect:/sucursales";
        }
    }

    // Muestra el formulario para editar una sucursal existente
    @GetMapping("/editar/{id}")
    public String showFormularioEditarSucursal(@PathVariable Integer id, Model model) {
        try {
            SucursalDTO sucursalDTO = sucursalService.getOneSucursal(id);
            model.addAttribute("sucursal", sucursalDTO);
            return "formularioEditarSucursal";
        } catch (EntityNotFoundException e) {
            return "redirect:/sucursales";
        }
    }

    // Actualiza una sucursal existente
    @PostMapping("/editar/{id}")
    public String updateSucursal(@PathVariable Integer id, @ModelAttribute SucursalDTO sucursalDTO) {
        try {
            sucursalService.updateSucursal(id, sucursalDTO);
            return "redirect:/sucursales";
        } catch (EntityNotFoundException e) {
            return "redirect:/sucursales";
        }
    }

    // Muestra el formulario de confirmación para eliminar una sucursal
    @GetMapping("/eliminar/{id}")
    public String showConfirmacionEliminar(@PathVariable Integer id, Model model) {
        try {
            SucursalDTO sucursalDTO = sucursalService.getOneSucursal(id);
            model.addAttribute("sucursal", sucursalDTO);
            return "confirmacionEliminar";
        } catch (EntityNotFoundException e) {
            return "redirect:/sucursales";
        }
    }

    // Elimina una sucursal
    @PostMapping("/eliminar/{id}")
    public String eliminarSucursal(@PathVariable Integer id) {
        try {
            sucursalService.deleteSucursal(id);
            return "redirect:/sucursales";
        } catch (EntityNotFoundException e) {
            return "redirect:/sucursales";
        }
    }

}
