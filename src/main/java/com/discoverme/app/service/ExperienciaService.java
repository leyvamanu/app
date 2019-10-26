package com.discoverme.app.service;

import com.discoverme.app.domain.Experiencia;
import com.discoverme.app.domain.Usuario;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ExperienciaService {
    List<Experiencia>getAllExperiencias();
    List<Experiencia> getAllExperiencias(String orden);
    Experiencia getExperienciaById(Integer id);
    Experiencia getExperienciaByNombre(String nombre);
    List<Experiencia> getExperienciaByUsuario(Usuario usuario);
    List<Experiencia> getExperienciasByPreferencias(String[] preferencias,String orden);
    void addExperiencia(Usuario usuario,String nombre,String fecha_inicio,String fecha_fin,String hora_inicio,String hora_fin,String descripcion,
                  String precio,String distancia,String mapa,MultipartFile imagenDestacada,MultipartFile imagen1,MultipartFile imagen2,MultipartFile imagen3,String tipo);
    void updateExperiencia(Integer id,Usuario usuario,String nombre,String fecha_inicio,String fecha_fin,String hora_inicio,String hora_fin,String descripcion,
                           String precio,String distancia,String mapa,MultipartFile imagenDestacada,MultipartFile imagen1,MultipartFile imagen2,MultipartFile imagen3,String tipo);
    void deleteExperiencia(Experiencia experiencia);
}