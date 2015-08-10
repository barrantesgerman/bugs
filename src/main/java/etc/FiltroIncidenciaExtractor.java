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
package etc;

import com.google.common.base.Optional;
import dtos.FiltroIncidenciaDTO;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import models.EstadoIncidencia;
import models.Prioridad;
import models.Reproducibilidad;
import models.Resolucion;
import ninja.Context;
import ninja.params.ArgumentExtractor;

/**
 *
 * @author Herman Barrantes
 * @since 07/08/2015
 */
public class FiltroIncidenciaExtractor implements ArgumentExtractor<FiltroIncidenciaDTO> {

    @Override
    public FiltroIncidenciaDTO extract(Context context) {
        FiltroIncidenciaDTO fdto = new FiltroIncidenciaDTO();
        fdto.setProyectoId(
                Optional.fromNullable(
                        context.getParameterAs("proyectoId", Long.class)));
        fdto.setModuloId(
                Optional.fromNullable(
                        context.getParameterAs("moduloId", Long.class)));
        fdto.setCategoriaId(
                Optional.fromNullable(
                        context.getParameterAs("categoriaId", Long.class)));
        fdto.setEstado(
                stringToEnum(
                        context.getParameterValues("estado"),
                        EstadoIncidencia.class));
        fdto.setPrioridad(
                stringToEnum(
                        context.getParameterValues("prioridad"),
                        Prioridad.class));
        fdto.setReproducibilidad(
                stringToEnum(
                        context.getParameterValues("reproducibilidad"),
                        Reproducibilidad.class));
        fdto.setResolucion(
                stringToEnum(
                        context.getParameterValues("resolucion"),
                        Resolucion.class));
        fdto.setResumen(
                Optional.fromNullable(
                        context.getParameter("resumen")));
        return fdto;
    }

    private <E extends Enum<E>> Set<E> stringToEnum(List<String> parametros, Class<E> enumerado) {
        Set<E> conjunto = new HashSet<>();
        for (String parametro : parametros) {
            try {
                E elemento = Enum.valueOf(enumerado, parametro);
                conjunto.add(elemento);
            } catch(NullPointerException | IllegalArgumentException ex) {
            }
        }
        return conjunto;
    }

    @Override
    public Class<FiltroIncidenciaDTO> getExtractedType() {
        return FiltroIncidenciaDTO.class;
    }

    @Override
    public String getFieldName() {
        return null;
    }

}
