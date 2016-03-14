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
package dtos;

import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Value;
import models.enums.EstadoIncidencia;
import models.enums.Prioridad;
import models.enums.Reproducibilidad;
import models.enums.Resolucion;

/**
 *
 * @author Herman Barrantes
 * @since 19/08/2015
 */
@Value
@EqualsAndHashCode(of = {"id"})
@JsonRootName(value = "incidencia")
public class IncidenciaDTO {

    /**
     * ID de la Incidencia.
     */
    private final long id;
    /**
     * ID del proyecto a la que esta relacionada la incidencia.
     */
    private final long proyectoId;
    /**
     * Nombre del proyecto a la que esta relacionada la incidencia.
     */
    private final String proyectoNombre;
    /**
     * ID del módulo a la que esta relacionada la incidencia, este campo es
     * opcional.
     */
    private final long moduloId;
    /**
     * Nombre del módulo a la que esta relacionada la incidencia.
     */
    private final String moduloNombre;
    /**
     * ID de la categoría a la que esta relacionada la incidencia.
     */
    private final long categoriaId;
    /**
     * Descripción de la categoría a la que esta relacionada la incidencia.
     */
    private final String categoriaDescripcion;
    /**
     * Estado en el que se encuentra la incidencia.
     */
    private final EstadoIncidencia estado;
    /**
     * Prioridad en la que debe ser atendida la incidencia.
     */
    private final Prioridad prioridad;
    /**
     * Frecuencia en la que se reproduce la incidencia.
     */
    private final Reproducibilidad reproducibilidad;
    /**
     * Resolución en la que se encuentra la incidencia.
     */
    private final Resolucion resolucion;
    /**
     * Descripción breve de lo que se trata la incidencia.
     */
    private final String resumen;
    /**
     * Fecha en la que se actualizó por última vez la incidencia.
     */
    private final Date fechaActualizacion;
    /**
     * Usuario que actualizó por última vez la incidencia.
     */
    private final String usuarioActualizacion;

    public IncidenciaDTO(long id, long proyectoId, String proyectoNombre, long moduloId, String moduloNombre, long categoriaId, String categoriaDescripcion, EstadoIncidencia estado, Prioridad prioridad, Reproducibilidad reproducibilidad, Resolucion resolucion, String resumen, Date fechaActualizacion, String usuarioActualizacion) {
        this.id = id;
        this.proyectoId = proyectoId;
        this.proyectoNombre = proyectoNombre;
        this.moduloId = moduloId;
        this.moduloNombre = moduloNombre;
        this.categoriaId = categoriaId;
        this.categoriaDescripcion = categoriaDescripcion;
        this.estado = estado;
        this.prioridad = prioridad;
        this.reproducibilidad = reproducibilidad;
        this.resolucion = resolucion;
        this.resumen = resumen;
        this.fechaActualizacion = fechaActualizacion;
        this.usuarioActualizacion = usuarioActualizacion;
        this.descripcion = null;
        this.pasos = null;
        this.informacionAdicional = null;
        this.fechaCreacion = null;
        this.usuarioCreacion = null;
        this.fechaAsignacion = null;
        this.usuarioAsignacion = null;
        this.fechaAtencion = null;
        this.usuarioAtencion = null;
        this.fechaResolucion = null;
        this.usuarioResolucion = null;
        this.fechaRevision = null;
        this.usuarioRevision = null;
        this.fechaCierre = null;
        this.usuarioCierre = null;
    }

    /**
     * Descripción detallada de lo que se trata la incidencia.
     */
    private final String descripcion;
    /**
     * Pasos a seguir para reproducir la incidencia.
     */
    private final String pasos;
    /**
     * Información extra que puede ayudar a la resolución de la incidencia.
     */
    private final String informacionAdicional;
    /**
     * Fecha en la que se creó la incidencia.
     */
    private final Date fechaCreacion;
    /**
     * Usuario que creó la incidencia.
     */
    private final String usuarioCreacion;
    /**
     * Fecha en la que se asignó la incidencia a un desarrollador.
     */
    private final Date fechaAsignacion;
    /**
     * Usuario que se le asignó la incidencia para atenderla.
     */
    private final String usuarioAsignacion;
    /**
     * Fecha en la que se empezó a atender la incidencia.
     */
    private final Date fechaAtencion;
    /**
     * Usuario que se encuentra atendiendo la incidencia.
     */
    private final String usuarioAtencion;
    /**
     * Fecha en la que se resolvió la incidencia.
     */
    private final Date fechaResolucion;
    /**
     * Usuario que resolvió la incidencia.
     */
    private final String usuarioResolucion;
    /**
     * Fecha en la que se empezó a revisar la incidencia.
     */
    private final Date fechaRevision;
    /**
     * Usuario que revisó la incidencia.
     */
    private final String usuarioRevision;
    /**
     * Fecha en la que se cerró la incidencia.
     */
    private final Date fechaCierre;
    /**
     * Usuario que cerró la incidencia.
     */
    private final String usuarioCierre;
}
