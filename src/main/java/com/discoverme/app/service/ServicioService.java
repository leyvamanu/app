package com.discoverme.app.service;

import com.discoverme.app.domain.Servicio;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 * Interficie que nos da servicio para acceder al repositorio de servicios
 * @author Jose Luis Sanchez Escoda
 */
public interface ServicioService {
    List<Servicio>getAllServicios();
    Servicio getServicioById(Integer id);
    Servicio getServicioByNombre(String nombre);
    void addServicio(String nombre,String fecha_inicio,String fecha_fin,String hora_inicio,String hora_fin,String descripcion,
                  String precio,MultipartFile imagenDestacada,MultipartFile imagen1,MultipartFile imagen2,MultipartFile imagen3);
    void updateServicio(Integer id,String nombre,String fecha_inicio,String fecha_fin,String hora_inicio,String hora_fin,String disponible,String descripcion,
                           String precio,MultipartFile imagenDestacada,MultipartFile imagen1,MultipartFile imagen2,MultipartFile imagen3);
    void deleteServicio(Servicio servicio);
}
