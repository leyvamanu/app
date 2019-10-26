package com.discoverme.app.service.impl;


import com.discoverme.app.domain.Foto;
import com.discoverme.app.repository.FotoRepository;
import com.discoverme.app.service.FotoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase que nos da servicio para acceder al repositorio de fotografias
 * @author leyva
 */
@Service
public class FotoServiceImpl implements FotoService {

    @Autowired
    FotoRepository fotoRepository;

    /**
     * Funcion que devuelve una fotografia dado un id
     * @param id
     * @return fotografia
     */
    @Override
    public Foto getFotoById(int id) {
        return fotoRepository.getFotoById(id);
    }

    /**
     * Funcion que añade un objeto fotografia
     * @param foto
     */
    @Override
    public void addFoto(Foto foto) {
        fotoRepository.addFoto(foto);
    }

    /**
     * Funcion que borra un objeto fotografia
     * @param foto
     */
    @Override
    public void deleteFoto(Foto foto) {
        fotoRepository.deleteFoto(foto);
    }

    /**
     * Funcion que devuelve todos los avatares de la lista
     * @return
     */
    @Override
    public List<Foto> getAllAvatares() {
        return fotoRepository.getAllAvatares();
    }

}
