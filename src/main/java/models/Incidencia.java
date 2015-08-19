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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Representa una incidencia.
 *
 * @author Herman
 * @since 22/07/2015
 */
@Data
@EqualsAndHashCode(callSuper = true, of = {"proyectoId", "moduloId", "categoriaId"})
@ToString(callSuper = true, of = {"proyectoId", "moduloId", "categoriaId", "resumen"})
@Entity
@Table(name = "incidencia")
public class Incidencia extends ModeloBase implements Serializable {

    /**
     * ID del proyecto a la que esta relacionada la incidencia.
     */
    @Column(name = "proyecto_id")
    private long proyectoId;
    /**
     * ID del módulo a la que esta relacionada la incidencia, este campo es
     * opcional.
     */
    @Column(name = "modulo_id")
    private long moduloId;
    /**
     * ID de la categoría a la que esta relacionada la incidencia.
     */
    @Column(name = "categoria_id")
    private long categoriaId;
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
    @Column(name = "fecha_atension")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAtension;
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
    @Column(name = "usuario_creacion")
    private String usuarioCreacion;
    /**
     * Usuario que actualizó por última vez la incidencia.
     */
    @Column(name = "usuario_actualizacion")
    private String usuarioActualizacion;
    /**
     * Usuario que se le asignó la incidencia para atenderla.
     */
    @Column(name = "usuario_asignacion")
    private String usuarioAsignacion;
    /**
     * Usuario que se encuentra atendiendo la incidencia.
     */
    @Column(name = "usuario_atension")
    private String usuarioAtension;
    /**
     * Usuario que resolvió la incidencia.
     */
    @Column(name = "usuario_resolucion")
    private String usuarioResolucion;
    /**
     * Usuario que revisó la incidencia.
     */
    @Column(name = "usuario_revision")
    private String usuarioRevision;
    /**
     * Usuario que cerró la incidencia.
     */
    @Column(name = "usuario_cierre")
    private String usuarioCierre;

}
