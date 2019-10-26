package com.discoverme.app.service.impl;


import com.discoverme.app.domain.Tipo;
import com.discoverme.app.repository.TipoRepository;
import com.discoverme.app.service.TipoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase que nos da servicio para acceder al repositorio de tipos
 * @author leyva
 */
@Service
public class TipoServiceImpl implements TipoService {

    @Autowired
    TipoRepository preferenciaRepository;

    /**
     * Funcion que devuelve el tipo dado un id
     *
     * @param id
     * @return
     * @author Manuel Leyva
     */
    @Override
    public Tipo getTipoById(Integer id) {
        return preferenciaRepository.getTipoById(id);
    }

    /**
     * Funcion que devuelve todos los tipos
     *
     * @return
     * @author Manuel Leyva
     */
    @Override
    public List<Tipo> getAllTipos() {
        return preferenciaRepository.getAllTipos();
    }
}
