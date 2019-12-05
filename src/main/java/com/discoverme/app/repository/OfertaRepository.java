package com.discoverme.app.repository;

import com.discoverme.app.domain.Experiencia;
import com.discoverme.app.domain.Oferta;
import java.util.List;

/**
 * Interfaz que representa un repositorio de oferta
 * 
 * @author Manuel Leyva
 */
public interface OfertaRepository {
    List<Oferta>getAllOfertas();
    List<Oferta>getOfertasServicios();
    List<Oferta> getOfertasByExperiencia(Experiencia experiencia);
    Oferta getOfertaByCodigo(String codigo);
    Oferta getOfertaByNombre(String nombre);
    Oferta getOfertaById(Integer id);
    void addOferta(Oferta oferta);
    void deleteOferta(Oferta oferta);
}
