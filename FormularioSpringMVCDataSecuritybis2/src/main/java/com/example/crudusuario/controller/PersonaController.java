package com.example.crudusuario.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.crudusuario.model.Persona;
import com.example.crudusuario.service.PersonaService;

@Controller
@RequestMapping("/user/eventos") // Manteniendo la ruta correcta
public class PersonaController {
    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping
    public String listarEventos(Model model) {
        model.addAttribute("eventos", personaService.obtenerTodasLasPersonas()); // Método corregido
        return "persona/index"; // Asegúrate de que este archivo existe en templates/persona/
    }

    @GetMapping("/crear")
    public String crearForm(Model model) {
        model.addAttribute("evento", new Persona()); // Manteniendo la nomenclatura "evento"
        return "persona/crear";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("evento") Persona evento) {
        personaService.agregarPersona(evento);
        return "redirect:/user/eventos";
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
        return "redirect:/user/eventos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        personaService.eliminarPersona(id);
        return "redirect:/user/eventos";
    }
}
