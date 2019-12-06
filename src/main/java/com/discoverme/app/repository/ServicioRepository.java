package com.discoverme.app.repository;

import com.discoverme.app.domain.Servicio;
import java.util.List;

/**
 * Interficie que representa un repositorio de servicios
 * 
 * @author Jose Luis Sanchez Escoda
 */
public interface ServicioRepository {
    List<Servicio>getAllServicios();
    Servicio getServicioById (Integer id);
    Servicio getServicioByNombre(String nombre);
    void addServicio (Servicio servicio);
    void updateServicio(Servicio servicio);
    void deleteServicio(Servicio servicio);
}
