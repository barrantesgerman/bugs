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
    private long proyectoId;
    /**
     * Nombre del proyecto a la que esta relacionada la incidencia.
     */
    private String proyectoNombre;
    /**
     * ID del módulo a la que esta relacionada la incidencia, este campo es
     * opcional.
     */
    private long moduloId;
    /**
     * Nombre del módulo a la que esta relacionada la incidencia.
     */
    private String moduloNombre;
    /**
     * ID de la categoría a la que esta relacionada la incidencia.
     */
    private long categoriaId;
    /**
     * Descripción de la categoría a la que esta relacionada la incidencia.
     */
    private String categoriaDescripcion;
    /**
     * Estado en el que se encuentra la incidencia.
     */
    private EstadoIncidencia estado;
    /**
     * Prioridad en la que debe ser atendida la incidencia.
     */
    private Prioridad prioridad;
    /**
     * Frecuencia en la que se reproduce la incidencia.
     */
    private Reproducibilidad reproducibilidad;
    /**
     * Resolución en la que se encuentra la incidencia.
     */
    private Resolucion resolucion;
    /**
     * Descripción breve de lo que se trata la incidencia.
     */
    private String resumen;
    /**
     * Fecha en la que se actualizó por última vez la incidencia.
     */
    private Date fechaActualizacion;
    /**
     * Usuario que actualizó por última vez la incidencia.
     */
    private String usuarioActualizacion;
}
