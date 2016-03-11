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
 * Representa la vista de la incidencia, la cual facilita la obtención de la
 * información relacionada a la incidencia.
 *
 * @author Herman Barrantes
 * @since 19/08/2015
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "incidencia_view")
public class IncidenciaView extends ModeloBase implements Serializable {

    /**
     * ID del proyecto a la que esta relacionada la incidencia.
     */
    @Column(name = "proyecto_id")
    private long proyectoId;
    /**
     * Nombre del proyecto a la que esta relacionada la incidencia.
     */
    @Column(name = "proyecto_nombre")
    private String proyectoNombre;
    /**
     * ID del módulo a la que esta relacionada la incidencia, este campo es
     * opcional.
     */
    @Column(name = "modulo_id")
    private long moduloId;
    /**
     * Nombre del módulo a la que esta relacionada la incidencia.
     */
    @Column(name = "modulo_nombre")
    private String moduloNombre;
    /**
     * ID de la categoría a la que esta relacionada la incidencia.
     */
    @Column(name = "categoria_id")
    private long categoriaId;
    /**
     * Descripción de la categoría a la que esta relacionada la incidencia.
     */
    @Column(name = "categoria_descripcion")
    private String categoriaDescripcion;
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
    @Column(name = "usuario_creacion_id")
    private Long usuarioCreacionId;
    /**
     * Usuario que actualizó por última vez la incidencia.
     */
    @Column(name = "usuario_actualizacion_id")
    private Long usuarioActualizacionId;
    /**
     * Usuario que se le asignó la incidencia para atenderla.
     */
    @Column(name = "usuario_asignacion_id")
    private Long usuarioAsignacionId;
    /**
     * Usuario que se encuentra atendiendo la incidencia.
     */
    @Column(name = "usuario_atencion_id")
    private Long usuarioAtencionId;
    /**
     * Usuario que resolvió la incidencia.
     */
    @Column(name = "usuario_resolucion_id")
    private Long usuarioResolucionId;
    /**
     * Usuario que revisó la incidencia.
     */
    @Column(name = "usuario_revision_id")
    private Long usuarioRevisionId;
    /**
     * Usuario que cerró la incidencia.
     */
    @Column(name = "usuario_cierre_id")
    private Long usuarioCierreId;
}
