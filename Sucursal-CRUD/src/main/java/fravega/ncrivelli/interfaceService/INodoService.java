package fravega.ncrivelli.interfaceService;

import java.util.List;
import java.util.Optional;

import fravega.ncrivelli.modelo.Nodo;

public interface INodoService {

	public List<Nodo> listar();
	public Optional<Nodo> listarID(int id);
	public int guardar(Nodo n);
	public void eliminar(int id);
	public double getDistancia(double lat1, double lon1, double lat2, double lon2);
	public Nodo getCercana(double lat, double lon);
}
