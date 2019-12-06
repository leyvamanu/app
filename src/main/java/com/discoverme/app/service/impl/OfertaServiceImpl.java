package com.discoverme.app.service.impl;

import com.discoverme.app.domain.Experiencia;
import com.discoverme.app.domain.Oferta;
import com.discoverme.app.repository.ExperienciaRepository;
import com.discoverme.app.repository.OfertaRepository;
import com.discoverme.app.service.OfertaService;
import com.discoverme.app.utils.GeneradorCodigos;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase que nos da servicio para acceder al repositorio de ofertas
 * @author Manuel Leyva
 */
@Service
public class OfertaServiceImpl implements OfertaService {

    @Autowired
    OfertaRepository ofertaRepository;

    @Autowired
    ExperienciaRepository experienciaRepository;

    /**
     * Funcion que accede al repositorio servicios y devuelve todos las ofertas
     * @author Jose Luis Sanchez Escoda
     * @return
     *
     */
    @Override
    public List<Oferta> getAllOfertas() {
        return ofertaRepository.getAllOfertas();
    }

    /**
     * Funcion que devuelve ofertas por experiencia
     * @author Jose Luis Sanchez Escoda
     * @param experiencia
     * @return
     *
     */
    @Override
    public List<Oferta> getOfertasByExperiencia(Experiencia experiencia) {
        return ofertaRepository.getOfertasByExperiencia(experiencia);
    }

    /**
     * Funcion que agrega ofertas
     * @author Jose Luis Sanchez Escoda
     * @param idExperiencia
     * @param nombre
     * @param fecha_inicio
     * @param fecha_fin
     * @param hora_inicio
     * @param hora_fin
     * @param descripcion
     *
     *
     */
    @Override
    public void addOferta(Integer idExperiencia, String nombre, String fecha_inicio, String fecha_fin, String hora_inicio, String hora_fin, String descripcion) {
        Oferta oferta = new Oferta();
        //Experiencia de la oferta si la tiene
        if (idExperiencia != null) {
            oferta.setExperiencia(experienciaRepository.getExperienciaById(idExperiencia));
        }
        //Codigo de la oferta
        String codigo = "";
        do {
            codigo = GeneradorCodigos.getCodigo(10);
        } while (ofertaRepository.getOfertaByCodigo(codigo) != null);
        oferta.setCodigo(codigo);
        //Nombre de la Oferta
        oferta.setNombre(nombre);
        //Fechas de la oferta
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");//formato fechas
        if (!fecha_inicio.equals("")) {
            oferta.setFecha_inicio(LocalDate.parse(fecha_inicio, formatter));
        }
        if (!fecha_fin.equals("")) {
            oferta.setFecha_fin(LocalDate.parse(fecha_fin, formatter));
        }
        //Horas de la oferta
        if (!hora_inicio.equals("")) {
            oferta.setHora_inicio(LocalTime.parse(hora_inicio));
        }
        if (!hora_fin.equals("")) {
            oferta.setHora_fin(LocalTime.parse(hora_fin));
        }
        //Descripcion de la oferta
        oferta.setDescripcion(descripcion);
        ofertaRepository.addOferta(oferta);
    }

    /**
     * Funcion que actualiza ofertas
     * @author Jose Luis Sanchez Escoda
     * @param id
     * @param nombre
     * @param fecha_inicio
     * @param hora_inicio
     * @param fecha_fin
     * @param hora_fin
     * @param descripcion
     *
     */
    @Override
    public void updateOferta(Integer id, String nombre, String fecha_inicio, String fecha_fin, String hora_inicio, String hora_fin, String descripcion) {
        Oferta oferta = ofertaRepository.getOfertaById(id);
        //Nombre de la Oferta
        oferta.setNombre(nombre);
        //Fechas de la oferta
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");//formato fechas
        if (!fecha_inicio.equals("")) {
            oferta.setFecha_inicio(LocalDate.parse(fecha_inicio, formatter));
        }
        if (!fecha_fin.equals("")) {
            oferta.setFecha_fin(LocalDate.parse(fecha_fin, formatter));
        }
        //Horas de la oferta
        if (!hora_inicio.equals("")) {
            oferta.setHora_inicio(LocalTime.parse(hora_inicio));
        }
        if (!hora_fin.equals("")) {
            oferta.setHora_fin(LocalTime.parse(hora_fin));
        }
        //Descripcion de la oferta
        oferta.setDescripcion(descripcion);
        ofertaRepository.addOferta(oferta);
    }

    /**
     * Funcion que accede al repositorio en busca del código
     * @param codigo
     * @return devuelve un boolean si lo encuentra o no.
     * @author Carlos Litwiñiuk Zarza
     *
     */
    @Override
    public boolean getOfertaByCodigo(String codigo) {
        Boolean flag = false;
        if (ofertaRepository.getOfertaByCodigo(codigo) != null) {
            return flag = true;
        }
        return flag;
    }

    /**
     * Funcion que devuelve oferta por nombre
     * @param nombre
     * @return
     * @author Jose Luis Sanchez Escoda
     */
    @Override
    public Oferta getOfertaByNombre(String nombre) {
        return ofertaRepository.getOfertaByNombre(nombre);
    }

    /**
     * Funcion que devuelve oferta por id
     * @param id
     * @return
     * @author Jose Luis Sanchez Escoda
     */
    @Override
    public Oferta getOfertaById(Integer id) {
        return ofertaRepository.getOfertaById(id);
    }

    /**
     * Funcion que borra ofertas
     * @param oferta
     * @author Jose Luis Sanchez Escoda
     */
    @Override
    public void deleteOferta(Oferta oferta) {
        ofertaRepository.deleteOferta(oferta);
    }

}
