package com.discoverme.app.repository;

import com.discoverme.app.domain.Foto;
import java.util.List;

/**
 * Interfaz que representa un repositorio de fotos
 * 
 * @author leyva
 */
public interface FotoRepository {
    Foto getFotoById(int id);
    Foto getFotoByNombre(String nombre);
    void addFoto(Foto foto);
    void deleteFoto(Foto foto);
    List<Foto> getAllAvatares();
}