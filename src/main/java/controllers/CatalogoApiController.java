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
package controllers;

import com.google.common.base.Optional;
import dtos.Resultados;
import java.util.EnumMap;
import java.util.Map;
import javax.inject.Inject;
import models.enums.EstadoIncidencia;
import models.enums.EstadoProyecto;
import models.enums.Prioridad;
import models.enums.Reproducibilidad;
import models.enums.Resolucion;
import ninja.Context;
import ninja.Result;
import ninja.i18n.Messages;

/**
 *
 * @author Herman Barrantes
 * @since 13/08/2015
 */
public class CatalogoApiController {

    @Inject
    private Messages msg;

    private <E extends Enum<E>> Map<E, String> enumToMap(
            Context context,
            Class<E> enumerado) {

        Optional<String> lang
                = Optional.fromNullable(context.getAcceptLanguage());
        Map<E, String> resultado = new EnumMap<>(enumerado);

        for (E elemento : enumerado.getEnumConstants()) {
            resultado.put(
                    elemento,
                    msg.get(
                            enumerado.getSimpleName() + "." + elemento.name(),
                            lang)
                    .or(""));
        }
        return resultado;
    }

    public Result estadoIncidencia(Context context) {

        Map<EstadoIncidencia, String> resultado
                = enumToMap(context, EstadoIncidencia.class);

        return Resultados.ok(resultado);
    }

    public Result estadoProyecto(Context context) {

        Map<EstadoProyecto, String> resultado
                = enumToMap(context, EstadoProyecto.class);

        return Resultados.ok(resultado);
    }

    public Result prioridad(Context context) {

        Map<Prioridad, String> resultado
                = enumToMap(context, Prioridad.class);

        return Resultados.ok(resultado);
    }

    public Result reproducibilidad(Context context) {

        Map<Reproducibilidad, String> resultado
                = enumToMap(context, Reproducibilidad.class);

        return Resultados.ok(resultado);
    }

    public Result resolucion(Context context) {

        Map<Resolucion, String> resultado
                = enumToMap(context, Resolucion.class);

        return Resultados.ok(resultado);
    }

}
