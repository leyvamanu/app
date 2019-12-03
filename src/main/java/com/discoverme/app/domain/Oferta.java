package com.discoverme.app.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Clase que representa una oferta (dotada de anotaciones de Hibernate para poder mapearla con la BD)
 *
 * @author Manuel Leyva
 */
@Entity
@Table(name = "ofertas")
public class Oferta implements Serializable{
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @NotNull
    @Column(name = "codigo", length = 10)
    private String codigo;
    
    @NotNull
    @Column(name = "nombre", length = 75)
    private String nombre;
    
    @NotNull
    @Column(name = "descripcion", length = 300)
    private String descripcion;

    @Column(name = "fecha_inicio")
    private LocalDate fecha_inicio;
    
    @Column(name = "fecha_fin")
    private LocalDate fecha_fin;
    
    @Column(name = "hora_inicio")
    private LocalTime hora_inicio;
    
    @Column(name = "hora_fin")
    private LocalTime hora_fin;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "experiencia_id")
    private Experiencia experiencia;

    public Oferta() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public LocalTime getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(LocalTime hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public LocalTime getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(LocalTime hora_fin) {
        this.hora_fin = hora_fin;
    }

    public Experiencia getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Experiencia experiencia) {
        this.experiencia = experiencia;
    }
    
    
}
