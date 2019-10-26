package com.discoverme.app.service.impl;


import com.discoverme.app.domain.Foto;
import com.discoverme.app.domain.Servicio;

import com.discoverme.app.repository.ServicioRepository;
import com.discoverme.app.service.FotoService;
import com.discoverme.app.service.ServicioService;
import com.discoverme.app.utils.Imagenes;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 * Clase que nos da servicio para acceder al repositorio de servicios
 * @author Jose Luis Sanchez Escoda
 */
@Service
public class ServicioServiceImpl implements ServicioService {

    @Autowired
    ServicioRepository servicioRepository;

    @Autowired
    FotoService fotoService;

    /**
     * Funcion que accede al repositorio servicios y devuelve todos los
     * servicios
     * @author Jose Luis Sanchez Escoda
     * @return
     *
     */
    @Override
    public List<Servicio> getAllServicios() {
        return servicioRepository.getAllServicios();
    }

    /**
     * Funcion que accede al repositorio servicios y devuelve servicios por id
     * @author Jose Luis Sanchez Escoda
     * @param id
     * @return
     *
     */
    @Override
    public Servicio getServicioById(Integer id) {
        return servicioRepository.getServicioById(id);

    }

    /**
     * Funcion que accede al repositorio servicios y devuelve servicios por
     * Nombre
     * @author Jose Luis Sanchez Escoda
     * @param nombre
     * @return
     *
     */
    @Override
    public Servicio getServicioByNombre(String nombre) {
        return servicioRepository.getServicioByNombre(nombre);
    }

    /**
     * Funcion que accede al repositorio servicios y añade un servicio a la base
     * de datos
     * @author Jose Luis Sanchez Escoda
     * @param nombre
     * @param fecha_inicio
     * @param fecha_fin
     * @param hora_inicio
     * @param hora_fin
     * @param precio
     * @param imagenDestacada
     * @param descripcion
     * @param imagen1
     * @param imagen2
     * @param imagen3
     *
     */
    @Override
    public void addServicio(String nombre, String fecha_inicio, String fecha_fin, String hora_inicio, String hora_fin, String descripcion,
            String precio, MultipartFile imagenDestacada, MultipartFile imagen1, MultipartFile imagen2, MultipartFile imagen3) {
        Servicio servicio = new Servicio();
        //servicio.setUsuario(usuario); usuario que crea el servicio
        servicio.setNombre(nombre);//nombre del servicio
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");//formato fechas
        if (!fecha_inicio.equals("")) {
            servicio.setFecha_inicio(LocalDate.parse(fecha_inicio, formatter));//fecha_inicio del servicio
        }
        if (!fecha_fin.equals("")) {
            servicio.setFecha_fin(LocalDate.parse(fecha_fin, formatter));//fecha_fin del servicio
        }
        if (!hora_inicio.equals("")) {
            servicio.setHora_inicio(LocalTime.parse(hora_inicio));//hora_inicio del servicio
        }
        if (!hora_fin.equals("")) {
            servicio.setHora_fin(LocalTime.parse(hora_fin));//hora_fin del servicio
        }
        servicio.setDescripcion(descripcion);//descripcion del servicio
        //precio del servicio
        if (!precio.equals("")) {
            servicio.setPrecio(Integer.parseInt(precio));
        } else {
            servicio.setPrecio(0);
        }
        /*fotos del servicio --> POR AHORA, ESTA COMENTADA LA PARTE DE IMAGENES PORQUE ME DABA UN ERROR*/
        List<Foto> fotos = new ArrayList();
        //Foto destacada del servicio
        Foto fotodestacada = Imagenes.subirFotosServer(imagenDestacada, true, servicio.getNombre(), 0, null);
        fotos.add(fotodestacada);
        fotoService.addFoto(fotodestacada);
        //foto 1 del servicio
        Foto foto1 = Imagenes.subirFotosServer(imagen1, false, servicio.getNombre(), 1, null);
        fotos.add(foto1);
        fotoService.addFoto(foto1);
        //foto 2 del servicio
        Foto foto2 = Imagenes.subirFotosServer(imagen2, false, servicio.getNombre(), 2, null);
        fotos.add(foto2);
        fotoService.addFoto(foto2);
        //foto 3 del servicio
        Foto foto3 = Imagenes.subirFotosServer(imagen3, false, servicio.getNombre(), 3, null);
        fotos.add(foto3);
        fotoService.addFoto(foto3);
        servicio.setFotos(fotos);//fotos del servicio

        servicioRepository.addServicio(servicio);

    }

    /**
     * Funcion que elimina un servicio
     * @author Jose Luis Sanchez Escoda
     * @param servicio
     *
     */
    @Override
    public void deleteServicio(Servicio servicio) {
        //eliminamos archivos del servidor
        for (Foto foto : servicio.getFotos()) {
            System.out.println("Foto: " + foto.getFoto());
            Imagenes.eliminarFotosServer(foto);
        }
        servicioRepository.deleteServicio(servicio);
    }

    /**
     * Funcion que actualiza un servicio
     * @author Jose Luis Sanchez Escoda
     * @param id
     * @param nombre
     * @param fecha_inicio
     * @param fecha_fin
     * @param hora_inicio
     * @param hora_fin
     * @param disponible
     * @param descripcion
     * @param precio
     * @param imagenDestacada
     * @param imagen1
     * @param imagen2
     * @param imagen3
     *
     */
    @Override
    public void updateServicio(Integer id, String nombre, String fecha_inicio, String fecha_fin, String hora_inicio, String hora_fin, String disponible, String descripcion,
            String precio, MultipartFile imagenDestacada, MultipartFile imagen1, MultipartFile imagen2, MultipartFile imagen3) {
        Servicio servicio = servicioRepository.getServicioById(id);
        servicio.setNombre(nombre);//nombre del servicio
        servicio.setNombre(nombre);//nombre de la experiencia
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");//formato fechas
        if (!fecha_inicio.equals("")) {
            servicio.setFecha_inicio(LocalDate.parse(fecha_inicio, formatter));//fecha_inicio del servicio
        }
        if (!fecha_fin.equals("")) {
            servicio.setFecha_fin(LocalDate.parse(fecha_fin, formatter));//fecha_fin del servicio
        }
        if (!hora_inicio.equals("")) {
            servicio.setHora_inicio(LocalTime.parse(hora_inicio));//hora_inicio del servicio
        }
        if (!hora_fin.equals("")) {
            servicio.setHora_fin(LocalTime.parse(hora_fin));//hora_fin del servicio
        }
        boolean disponibilidad = true;
        if (disponible.equals("true")) {
            disponibilidad = true;
        }
        if (disponible.equals("false")) {
            disponibilidad = false;
        }

        servicio.setDisponible(disponibilidad);//disponible o no disponible
        servicio.setDescripcion(descripcion);//descripcion del servicio
        if (!precio.equals("")) {
            servicio.setPrecio(Integer.parseInt(precio));//precio del servicio
        }

        List<Foto> fotos = new ArrayList();
        //Foto destacada del servicio
        if (!imagenDestacada.isEmpty()) {
            Imagenes.eliminarFotosServer(servicio.getFotos().get(0));
            Foto fotodestacada = Imagenes.subirFotosServer(imagenDestacada, true, servicio.getNombre(), 0, servicio.getFotos().get(0).getId());
            fotos.add(fotodestacada);
            fotoService.addFoto(fotodestacada);
        } else {
            fotos.add(servicio.getFotos().get(0));
        }
        //foto 1 del servicio
        if (!imagen1.isEmpty()) {
            Imagenes.eliminarFotosServer(servicio.getFotos().get(1));
            Foto foto1 = Imagenes.subirFotosServer(imagen1, false, servicio.getNombre(), 1, servicio.getFotos().get(1).getId());
            fotos.add(foto1);
            fotoService.addFoto(foto1);
        } else {
            fotos.add(servicio.getFotos().get(1));
        }
        //foto 2 de la experiencia
        if (!imagen2.isEmpty()) {
            Imagenes.eliminarFotosServer(servicio.getFotos().get(2));
            Foto foto2 = Imagenes.subirFotosServer(imagen2, false, servicio.getNombre(), 2, servicio.getFotos().get(2).getId());
            fotos.add(foto2);
            fotoService.addFoto(foto2);
        } else {
            fotos.add(servicio.getFotos().get(2));
        }
        //foto 3 de la experiencia
        if (!imagen3.isEmpty()) {
            Imagenes.eliminarFotosServer(servicio.getFotos().get(3));
            Foto foto3 = Imagenes.subirFotosServer(imagen3, false, servicio.getNombre(), 3, servicio.getFotos().get(3).getId());
            fotos.add(foto3);
            fotoService.addFoto(foto3);
        } else {
            fotos.add(servicio.getFotos().get(3));
        }
        servicio.setFotos(fotos);//fotos del servicio

        servicioRepository.addServicio(servicio);
    }

}
