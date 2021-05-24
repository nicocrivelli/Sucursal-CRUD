package fravega.ncrivelli.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fravega.ncrivelli.interfaceService.INodoService;
import fravega.ncrivelli.interfaces.INodo;
import fravega.ncrivelli.modelo.Nodo;

@Service
public class NodoService implements INodoService {

	@Autowired
	private INodo data;

	@Override
	public List<Nodo> listar() {
		return (List<Nodo>) data.findAll();
	}

	@Override
	public Optional<Nodo> listarID(int id) {
		return data.findById(id);
	}

	@Override
	public int guardar(Nodo n) {

		int respuesta = 0;
		Nodo nodo = data.save(n);

		if (!nodo.equals(null))
			respuesta = 1;

		return respuesta;
	}

	@Override
	public void eliminar(int id) {
		data.deleteById(id);
	}

	@Override
	public Nodo getCercana(double lat, double lon) {
		List<Nodo> nodos = this.listar();
		double distancia = this.getDistancia(nodos.get(0).getLatitud(), nodos.get(0).getLongitud(), lat, lon);
		Nodo nodoCercano = nodos.get(0);

		for (Nodo n : nodos) {
			double distanciaProvisoria = this.getDistancia(n.getLatitud(), n.getLongitud(), lat, lon);
			if(distanciaProvisoria < distancia) {
				distancia = distanciaProvisoria;
				nodoCercano = n;
			}
		}

		return nodoCercano;
	}

	@Override
	public double getDistancia(double lat1, double lon1, double lat2, double lon2) {
		return Math.sqrt(Math.pow((lat2 - lat1), 2) + Math.pow((lon2 - lon1), 2));
	}

}
