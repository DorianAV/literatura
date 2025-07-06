package org.dorian.literatura.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autor")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @JsonAlias("name")
    private String nombre;
    @JsonAlias("birth_year")
    private Integer fechaNacimiento;
    @JsonAlias("death_year")
    private Integer fechaFallecimiento;

    public Autor(Long id, String nombre, Integer fechaNacimiento, Integer fechaFallecimiento) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public Autor() {

    }

    @Override
    public String toString() {
        return "----- Autor ----- \n" +
                "Nombre:" + nombre +"\n" +
                "Año nacimiento=" + fechaNacimiento +"\n"+
                "Año fallecimiento=" + fechaFallecimiento +"\n"+
                "----------------";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Integer fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }
}
