package com.example.crudusuario.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.crudusuario.model.Persona;
import com.example.crudusuario.service.PersonaService;

@Controller
@RequestMapping("/admin/eventos") 
public class PersonaAdminController {
    private final PersonaService personaService;

    public PersonaAdminController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping
    public String listarEventos(Model model) {
        model.addAttribute("eventos", personaService.obtenerTodasLasPersonas()); 
        model.addAttribute("ciudades", personaService.obtenerCiudadesUnicas()); 
        model.addAttribute("artistas", personaService.obtenerArtistasUnicos()); 
        return "persona/index"; 
    }

    @GetMapping("/crear")
    public String crearForm(Model model) {
        model.addAttribute("evento", new Persona()); 
        return "persona/crear"; 
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("evento") Persona evento) {
        personaService.agregarPersona(evento);
        return "redirect:/admin/eventos";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        Persona evento = personaService.obtenerPersonaPorId(id);
        model.addAttribute("evento", evento);
        return "persona/editar";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute("evento") Persona evento) {
        personaService.actualizarPersona(id, evento);
        return "redirect:/admin/eventos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        personaService.eliminarPersona(id);
        return "redirect:/admin/eventos";
    }

    @GetMapping("/filtrar")
    public String filtrarEventos(@RequestParam(required = false) String ciudad, 
                                 @RequestParam(required = false) String artista, 
                                 Model model) {
        List<Persona> eventosFiltrados = personaService.filtrarEventos(ciudad, artista);
        model.addAttribute("eventos", eventosFiltrados);
        model.addAttribute("ciudades", personaService.obtenerCiudadesUnicas());
        model.addAttribute("artistas", personaService.obtenerArtistasUnicos());
        return "persona/index"; 
    }
}
