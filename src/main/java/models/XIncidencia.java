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
import models.enums.EstadoIncidencia;
import models.enums.Prioridad;
import models.enums.Reproducibilidad;
import models.enums.Resolucion;

/**
 * Representa una incidencia.
 *
 * @author Herman
 * @since 22/07/2015
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "incidencia")
public class XIncidencia extends ModeloBase implements Serializable {

    /**
     * Proyecto a la que esta relacionada la incidencia.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proyecto_id")
    private Proyecto proyecto;
    /**
     * Módulo a la que esta relacionada la incidencia, este campo es opcional.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modulo_id")
    private Modulo modulo;
    /**
     * Categoría a la que esta relacionada la incidencia.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    /**
     * Estado en el que se encuentra la incidencia.
     */
    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private EstadoIncidencia estado;
    /**
     * Prioridad en la que debe ser atendida la incidencia.
     */
    @Column(name = "prioridad")
    @Enumerated(EnumType.STRING)
    private Prioridad prioridad;
    /**
     * Frecuencia en la que se reproduce la incidencia.
     */
    @Column(name = "reproducibilidad")
    @Enumerated(EnumType.STRING)
    private Reproducibilidad reproducibilidad;
    /**
     * Resolución en la que se encuentra la incidencia.
     */
    @Column(name = "resolucion")
    @Enumerated(EnumType.STRING)
    private Resolucion resolucion;
    /**
     * Descripción breve de lo que se trata la incidencia.
     */
    @Column(name = "resumen")
    private String resumen;
    /**
     * Descripción detallada de lo que se trata la incidencia.
     */
    @Column(name = "descripcion")
    private String descripcion;
    /**
     * Pasos a seguir para reproducir la incidencia.
     */
    @Column(name = "pasos")
    private String pasos;
    /**
     * Información extra que puede ayudar a la resolución de la incidencia.
     */
    @Column(name = "informacion_adicional")
    private String informacionAdicional;
    ////////////////////////////////////////////////////////////////////////////
    // Fechas de auditoría
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Fecha en la que se creó la incidencia.
     */
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    /**
     * Fecha en la que se actualizó por última vez la incidencia.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_actualizacion")
    private Date fechaActualizacion;
    /**
     * Fecha en la que se asignó la incidencia a un desarrollador.
     */
    @Column(name = "fecha_asignacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAsignacion;
    /**
     * Fecha en la que se empezó a atender la incidencia.
     */
    @Column(name = "fecha_atencion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAtencion;
    /**
     * Fecha en la que se resolvió la incidencia.
     */
    @Column(name = "fecha_resolucion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaResolucion;
    /**
     * Fecha en la que se empezó a revisar la incidencia.
     */
    @Column(name = "fecha_revision")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRevision;
    /**
     * Fecha en la que se cerró la incidencia.
     */
    @Column(name = "fecha_cierre")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCierre;
    ////////////////////////////////////////////////////////////////////////////
    // Usuarios de auditoría
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Usuario que creó la incidencia.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_creacion_id")
    private Usuario usuarioCreacion;
    /**
     * Usuario que actualizó por última vez la incidencia.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_actualizacion_id")
    private Usuario usuarioActualizacion;
    /**
     * Usuario que se le asignó la incidencia para atenderla.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_asignacion_id")
    private Usuario usuarioAsignacion;
    /**
     * Usuario que se encuentra atendiendo la incidencia.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_atencion_id")
    private Usuario usuarioAtencion;
    /**
     * Usuario que resolvió la incidencia.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_resolucion_id")
    private Usuario usuarioResolucion;
    /**
     * Usuario que revisó la incidencia.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_revision_id")
    private Usuario usuarioRevision;
    /**
     * Usuario que cerró la incidencia.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_cierre_id")
    private Usuario usuarioCierre;

}
