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
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import models.EstadoIncidencia;
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

    public Result estadoIncidencia(Context context) {
        
        Optional<String> lang =
                Optional.fromNullable(context.getAcceptLanguage());
        
        Map<EstadoIncidencia, String> resultado = new HashMap<>();
        
        for (EstadoIncidencia estado : EstadoIncidencia.values()) {
            resultado.put(
                    estado,
                    msg.getWithDefault(
                            "estadoIncidencia." + estado.name(),
                            "",
                            lang,
                            Optional.<Result>absent()));
        }
        
        return Resultados.ok(resultado);
    }

}
