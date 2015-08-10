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
import com.google.common.base.Optional;
import java.io.Serializable;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import models.EstadoIncidencia;
import models.Prioridad;
import models.Reproducibilidad;
import models.Resolucion;

/**
 *
 * @author Herman Barrantes
 * @since 07/08/2015
 */
@Data
@EqualsAndHashCode(of = {"proyectoId", "moduloId", "categoriaId"})
@JsonRootName(value = "filtro")
public class FiltroIncidenciaDTO implements Serializable {

    private Optional<String> usuario;
    private Optional<Long> proyectoId;
    private Optional<Long> moduloId;
    private Optional<Long> categoriaId;
    private Set<EstadoIncidencia> estado;
    private Set<Prioridad> prioridad;
    private Set<Reproducibilidad> reproducibilidad;
    private Set<Resolucion> resolucion;
    private Optional<String> resumen;

}
