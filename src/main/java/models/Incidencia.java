/*
 * Copyright 2015 Codigo Fantasma.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * @author Herman
 * @since 22/07/2015
 */
@Data
@EqualsAndHashCode(callSuper = true, of = {"proyecto", "modulo", "categoria"})
@ToString(callSuper = true, of = {"proyecto", "modulo", "categoria", "resumen"})
@Entity
@Table(name = "incidencia")
public class Incidencia extends ModeloBase implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "proyecto_id")
    private Proyecto proyecto;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "modulo_id")
    private Modulo modulo;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private EstadoIncidencia estado;
    @Column(name = "prioridad")
    @Enumerated(EnumType.STRING)
    private Prioridad prioridad;
    @Column(name = "reproducibilidad")
    @Enumerated(EnumType.STRING)
    private Reproducibilidad reproducibilidad;
    @Column(name = "resolucion")
    @Enumerated(EnumType.STRING)
    private Resolucion resolucion;
    @Column(name = "resumen")
    private String resumen;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "pasos")
    private String pasos;
    @Column(name = "informacion_adicional")
    private String informacionAdicional;

    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_actualizacion")
    private Date fechaActualizacion;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAsignacion;
    @Column(name = "fecha_atension")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAtension;
    @Column(name = "fecha_resolucion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaResolucion;
    @Column(name = "fecha_revision")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRevision;
    @Column(name = "fecha_cierre")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCierre;

    @Column(name = "usuario_creacion")
    private String usuarioCreacion;
    @Column(name = "usuario_actualizacion")
    private String usuarioActualizacion;
    @Column(name = "usuario_asignacion")
    private String usuarioAsignacion;
    @Column(name = "usuario_atension")
    private String usuarioAtension;
    @Column(name = "usuario_resolucion")
    private String usuarioResolucion;
    @Column(name = "usuario_revision")
    private String usuarioRevision;
    @Column(name = "usuario_cierre")
    private String usuarioCierre;

}
