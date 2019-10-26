package com.discoverme.app.service.impl;

import com.discoverme.app.domain.Foto;
import com.discoverme.app.domain.Experiencia;
import com.discoverme.app.domain.Tipo;
import com.discoverme.app.domain.Usuario;
import com.discoverme.app.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.discoverme.app.service.TipoService;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.discoverme.app.repository.ExperienciaRepository;
import com.discoverme.app.service.ExperienciaService;
import com.discoverme.app.utils.Imagenes;

/**
 * Clase que nos da servicio para acceder al repositorio de
 * experiencias/actividades
 *
 * @author Manuel Leyva
 */
@Service
public class ExperienciaServiceImpl implements ExperienciaService {

    @Autowired
    FotoService fotoService;

    @Autowired
    TipoService tiposService;

    @Autowired
    ExperienciaRepository experienciaRepository;

    /**
     * Funcion que devuelve todas las experiencias/actividades
     * @return Listado experiencias/actividades
     * @author Manuel Leyva
     */
    @Override
    public List<Experiencia> getAllExperiencias() {
        return experienciaRepository.getAllExperiencias();
    }

    /**
    * Funcion que accede al repositorio de experiencias/actividades y devuelve todos los experiencias/actividades
    * @param orden
    * @return Listado de experiencias/actividades
    * @author Manuel Leyva    
     */
    @Override
    public List<Experiencia> getAllExperiencias(String orden) {
        return experienciaRepository.getAllExperiencias(orden);
    }

    /**
    * Funcion que accede al repositorio de experiencias/actividades y devuelve el experiencia/actividad del cual se paso el identificador
    * @param id Identificador del sitio/actividad
    * @return Experiencia de la BD
    * @author Manuel Leyva    
     */
    @Override
    public Experiencia getExperienciaById(Integer id) {
        return experienciaRepository.getExperienciaById(id);
    }

    /**
     * Funcion que devuelve experiencia por nombre
     * @param nombre
     * @return
     * @author Manuel Leyva
     */
    @Override
    public Experiencia getExperienciaByNombre(String nombre) {
        return experienciaRepository.getExperienciaByNombre(nombre);
    }

    /**
    * Funcion que accede al repositorio de experiencias/actividades y añade el experiencia/actividad a la BD
    * @param experiencia Experiencia/Actividad para añadir a la BD
    * @author Manuel Leyva    
     */
    @Override
    public void addExperiencia(Usuario usuario, String nombre, String fecha_inicio, String fecha_fin, String hora_inicio, String hora_fin, String descripcion,
            String precio, String distancia, String mapa, MultipartFile imagenDestacada, MultipartFile imagen1, MultipartFile imagen2, MultipartFile imagen3, String tipo) {
        Experiencia experiencia = new Experiencia();
        experiencia.setUsuario(usuario); //usuario que crea la experiencia
        experiencia.setNombre(nombre);//nombre de la experiencia
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");//formato fechas
        if (!fecha_inicio.equals("")) {
            experiencia.setFecha_inicio(LocalDate.parse(fecha_inicio, formatter));//fecha_inicio de la experiencia
        }
        if (!fecha_fin.equals("")) {
            experiencia.setFecha_fin(LocalDate.parse(fecha_fin, formatter));//fecha_fin de la experiencia
        }
        if (!hora_inicio.equals("")) {
            experiencia.setHora_inicio(LocalTime.parse(hora_inicio));//hora_inicio de la experiencia
        }
        if (!hora_fin.equals("")) {
            experiencia.setHora_fin(LocalTime.parse(hora_fin));//hora_fin de la experiencia
        }
        experiencia.setDescripcion(descripcion);//descripcion de la experiencia
        if (!precio.equals("")) {
            experiencia.setPrecio(Integer.parseInt(precio));//precio de la experiencia
        } else {
            experiencia.setPrecio(0);//precio de la experiencia
        }
        experiencia.setPuntuacion(0);//puntuacion de la experiencia
        experiencia.setMins_distancia(Integer.parseInt(distancia));//distancia de la experiencia
        experiencia.setMapa(mapa);//mapa de la experiencia
        //fotos de la experiencia
        List<Foto> fotos = new ArrayList();
        //Foto destacada de la experiencia
        Foto fotodestacada = Imagenes.subirFotosServer(imagenDestacada, true, experiencia.getNombre(), 0, null);
        fotos.add(fotodestacada);
        fotoService.addFoto(fotodestacada);
        //foto 1 de la experiencia
        Foto foto1 = Imagenes.subirFotosServer(imagen1, false, experiencia.getNombre(), 1, null);
        fotos.add(foto1);
        fotoService.addFoto(foto1);
        //foto 2 de la experiencia
        Foto foto2 = Imagenes.subirFotosServer(imagen2, false, experiencia.getNombre(), 2, null);
        fotos.add(foto2);
        fotoService.addFoto(foto2);
        //foto 3 de la experiencia
        Foto foto3 = Imagenes.subirFotosServer(imagen3, false, experiencia.getNombre(), 3, null);
        fotos.add(foto3);
        fotoService.addFoto(foto3);
        experiencia.setFotos(fotos);//fotos de la experiencia
        //tipos de la experiencia
        List<Tipo> tipos = new ArrayList();
        for (String s : tipo.split(",")) {
            System.out.println("Tipo: " + s);
            tipos.add(tiposService.getTipoById(Integer.parseInt(s)));
        }
        experiencia.setTipos(tipos);
        experienciaRepository.addExperiencia(experiencia);
    }

    /**
    * Funcion que accede al repositorio de experiencias/actividades y devuelve un listado de experiencias filtrado por las preferencias pasadas como parametro
    * @param preferencias Array con las diferentes preferencias/tipos a filtrar
    * @return Listado de experiencias/actividades
    * @author Manuel Leyva    
     */
    @Override
    public List<Experiencia> getExperienciasByPreferencias(String[] preferencias, String orden) {

        List<Experiencia> experiencias = new ArrayList<Experiencia>();
        for (Experiencia s : getAllExperiencias(orden)) {
            boolean encontrado = false;
            for (Tipo t : s.getTipos()) {
                for (String p : preferencias) {
                    if (t.getId().equals(Integer.parseInt(p))) {
                        experiencias.add(s);
                        encontrado = true;
                        break;
                    }
                }
                if (encontrado) {
                    break;
                }
            }
        }
        return experiencias;
    }

    /**
     * Funcion que devuelve experiencias por usuario
     * @param usuario
     * @return
     * @author Manuel Leyva
     */
    @Override
    public List<Experiencia> getExperienciaByUsuario(Usuario usuario) {
        return experienciaRepository.getExperienciaByUsuario(usuario);
    }

    /**
     * Funcion que borra experiencias
     * @param experiencia
     * @author Jose Luis Sanchez Escoda
     */
    @Override
    public void deleteExperiencia(Experiencia experiencia) {
        //eliminamos archivos del servidor
        for (Foto foto : experiencia.getFotos()) {
            System.out.println("Foto: " + foto.getFoto());
            Imagenes.eliminarFotosServer(foto);
        }
        experienciaRepository.deleteExperiencia(experiencia);
    }

    /**
     * Funcion que actualiza experiencias
     * @param id
     * @param usuario
     * @param nombre
     * @param fecha_inicio
     * @param fecha_fin
     * @param hora_inicio
     * @param hora_fin
     * @param descripcion
     * @param precio
     * @param distancia
     * @param mapa
     * @param imagenDestacada
     * @param imagen1
     * @param imagen2
     * @param imagen3
     * @param tipo
     * @author Manuel Leyva
     */
    @Override
    public void updateExperiencia(Integer id, Usuario usuario, String nombre, String fecha_inicio, String fecha_fin, String hora_inicio, String hora_fin, String descripcion,
            String precio, String distancia, String mapa, MultipartFile imagenDestacada, MultipartFile imagen1, MultipartFile imagen2, MultipartFile imagen3, String tipo) {
        Experiencia experiencia = experienciaRepository.getExperienciaById(id);
        experiencia.setNombre(nombre);//nombre de la experiencia
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");//formato fechas
        if (!fecha_inicio.equals("")) {
            experiencia.setFecha_inicio(LocalDate.parse(fecha_inicio, formatter));//fecha_inicio de la experiencia
        }
        if (!fecha_fin.equals("")) {
            experiencia.setFecha_fin(LocalDate.parse(fecha_fin, formatter));//fecha_fin de la experiencia
        }
        if (!hora_inicio.equals("")) {
            experiencia.setHora_inicio(LocalTime.parse(hora_inicio));//hora_inicio de la experiencia
        }
        if (!hora_fin.equals("")) {
            experiencia.setHora_fin(LocalTime.parse(hora_fin));//hora_fin de la experiencia
        }
        experiencia.setDescripcion(descripcion);//descripcion de la experiencia
        if (!precio.equals("")) {
            experiencia.setPrecio(Integer.parseInt(precio));//precio de la experiencia
        }
        experiencia.setMins_distancia(Integer.parseInt(distancia));//distancia de la experiencia
        experiencia.setMapa(mapa);//mapa de la experiencia
        List<Foto> fotos = new ArrayList();
        //Foto destacada de la experiencia
        if (!imagenDestacada.isEmpty()) {
            Imagenes.eliminarFotosServer(experiencia.getFotos().get(0));
            Foto fotodestacada = Imagenes.subirFotosServer(imagenDestacada, true, experiencia.getNombre(), 0, experiencia.getFotos().get(0).getId());
            fotos.add(fotodestacada);
            fotoService.addFoto(fotodestacada);
        } else {
            fotos.add(experiencia.getFotos().get(0));
        }
        //foto 1 de la experiencia
        if (!imagen1.isEmpty()) {
            Imagenes.eliminarFotosServer(experiencia.getFotos().get(1));
            Foto foto1 = Imagenes.subirFotosServer(imagen1, false, experiencia.getNombre(), 1, experiencia.getFotos().get(1).getId());
            fotos.add(foto1);
            fotoService.addFoto(foto1);
        } else {
            fotos.add(experiencia.getFotos().get(1));
        }
        //foto 2 de la experiencia
        if (!imagen2.isEmpty()) {
            Imagenes.eliminarFotosServer(experiencia.getFotos().get(2));
            Foto foto2 = Imagenes.subirFotosServer(imagen2, false, experiencia.getNombre(), 2, experiencia.getFotos().get(2).getId());
            fotos.add(foto2);
            fotoService.addFoto(foto2);
        } else {
            fotos.add(experiencia.getFotos().get(2));
        }
        //foto 3 de la experiencia
        if (!imagen3.isEmpty()) {
            Imagenes.eliminarFotosServer(experiencia.getFotos().get(3));
            Foto foto3 = Imagenes.subirFotosServer(imagen3, false, experiencia.getNombre(), 3, experiencia.getFotos().get(3).getId());
            fotos.add(foto3);
            fotoService.addFoto(foto3);
        } else {
            fotos.add(experiencia.getFotos().get(3));
        }
        experiencia.setFotos(fotos);//fotos de la experiencia
        //tipos de la experiencia
        List<Tipo> tipos = new ArrayList();
        for (String s : tipo.split(",")) {
            tipos.add(tiposService.getTipoById(Integer.parseInt(s)));
        }
        experiencia.setTipos(tipos);
        experienciaRepository.addExperiencia(experiencia);
    }

}
