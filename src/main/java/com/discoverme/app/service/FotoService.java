package com.discoverme.app.service;

import com.discoverme.app.domain.Foto;
import java.util.List;

/**
 * Interficie que nos da servicio para acceder al repositorio fotos
 * @author leyva
 */
public interface FotoService {
    Foto getFotoById(int id);
    void addFoto(Foto foto);
    void deleteFoto(Foto foto);
    List<Foto> getAllAvatares();
}