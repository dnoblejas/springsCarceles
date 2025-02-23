package com.example.crudusuario.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.crudusuario.model.Persona;
import com.example.crudusuario.repository.PersonaRepository;

@Service
public class PersonaService {
    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public List<Persona> obtenerTodasLasPersonas() {
        return personaRepository.findAll();
    }

    public Persona obtenerPersonaPorId(Long id) {
        return personaRepository.findById(id).orElse(null);
    }

    public void agregarPersona(Persona persona) {
        personaRepository.save(persona);
    }

    public void actualizarPersona(Long id, Persona persona) {
        if (personaRepository.existsById(id)) {
            persona.setId(id);
            personaRepository.save(persona);
        }
    }

    public void eliminarPersona(Long id) {
        personaRepository.deleteById(id);
    }

    public List<String> obtenerCiudadesUnicas() {
        return personaRepository.findAll().stream()
                .map(Persona::getUbicacion) 
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> obtenerArtistasUnicos() {
        return personaRepository.findAll().stream()
                .map(Persona::getArtista) 
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Persona> filtrarEventos(String ciudad, String artista) {
        return personaRepository.findAll().stream()
                .filter(e -> (ciudad == null || ciudad.isEmpty() || "Todas".equals(ciudad) || e.getUbicacion().equalsIgnoreCase(ciudad)))
                .filter(e -> (artista == null || artista.isEmpty() || "Todos".equals(artista) || e.getArtista().equalsIgnoreCase(artista)))
                .collect(Collectors.toList());
    }
}
