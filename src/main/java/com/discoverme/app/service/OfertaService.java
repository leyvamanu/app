package com.discoverme.app.service;

import com.discoverme.app.domain.Experiencia;
import com.discoverme.app.domain.Oferta;
import java.util.List;

/**
 * Interficie que nos da servicio para acceder al repositorio ofertas
 * @author Manuel Leyva
 */
public interface OfertaService {
    List<Oferta> getAllOfertas();
    List<Oferta> getOfertasByExperiencia(Experiencia experiencia);
    Oferta getOfertaByNombre(String nombre);
    Oferta getOfertaById(Integer id);
    boolean getOfertaByCodigo(String codigo);
    void addOferta(Integer idExperiencia,String nombre,String fecha_inicio,String fecha_fin,String hora_inicio,String hora_fin,String descripcion);
    void updateOferta(Integer id,String nombre,String fecha_inicio,String fecha_fin,String hora_inicio,String hora_fin,String descripcion);
    void deleteOferta(Oferta oferta);
}
