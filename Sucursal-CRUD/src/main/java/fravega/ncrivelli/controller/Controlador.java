package fravega.ncrivelli.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fravega.ncrivelli.interfaceService.INodoService;
import fravega.ncrivelli.modelo.Nodo;

@Controller
@RequestMapping
public class Controlador {

	@Autowired
	private INodoService service;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		List<Nodo> nodos = service.listar();
		
		model.addAttribute("nodos", nodos);
		return "index";
	}
	
	@GetMapping("/nuevo")
	public String agregar(Model model) {
		
		model.addAttribute("nodo", new Nodo());
		return "form";
	}

	@PostMapping("/guardar")
	public String guardar(@Validated Nodo n, Model model) {
		
		service.guardar(n);
		
		return "redirect:/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable int id, Model model) {
		Optional<Nodo> nodo = service.listarID(id);
		
		model.addAttribute("nodo", nodo);
		return "formActualizar";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable int id, Model model) {
		service.eliminar(id);
		
		return "redirect:/listar";
	}
	
	@GetMapping("/buscarCercana")
	public String buscarCercana(Model model) {
		
		model.addAttribute("nodo", new Nodo());
		return "formCercano";
	}
	
	@PostMapping("/listarCercana")
	public String listarCercana(@Validated Nodo n, Model model) {
		Nodo nodoCercano = service.getCercana(n.getLatitud(), n.getLongitud());
		
		model.addAttribute("cercano", nodoCercano);
		return "infoCercano";
	}
}
