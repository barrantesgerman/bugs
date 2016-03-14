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
import dao.NotaDAO;
import dtos.Resultados;
import java.util.List;
import javax.inject.Inject;
import models.Nota;
import ninja.Result;
import ninja.params.PathParam;
import ninja.validation.JSR303Validation;
import ninja.validation.Validation;

/**
 *
 * @author Herman
 * @since 22/07/2015
 */
public class NotaApiController {

    @Inject
    private NotaDAO notaDAO;

    public Result listar(@PathParam("incidenciaId") Long incidenciaId) {

        List<Nota> notas = notaDAO.listar(incidenciaId);
        if (!notas.isEmpty()) {
            return Resultados.ok(notas);
        }
        return Resultados.notFound();
    }

    public Result buscar(
            @PathParam("incidenciaId") Long incidenciaId,
            @PathParam("notaId") Long notaId) {

        Optional<Nota> nota = notaDAO.buscar(incidenciaId, notaId);
        if (nota.isPresent()) {
            return Resultados.ok(nota.get());
        }
        return Resultados.notFound();
    }

    public Result crear(
            @PathParam("incidenciaId") Long incidenciaId,
            @JSR303Validation Nota nota,
            Validation validation) {
        
        if (validation.hasViolations()) {
            return Resultados.validation(validation);
        }

        Optional<Nota> resultado = notaDAO.crear(incidenciaId, nota);
        if (resultado.isPresent()) {
            return Resultados.created(resultado.get());
        }
        return Resultados.notFound();
    }

    public Result editar(
            @PathParam("incidenciaId") Long incidenciaId,
            @PathParam("notaId") Long notaId,
            @JSR303Validation Nota nota,
            Validation validation) {

        if (validation.hasViolations()) {
            return Resultados.validation(validation);
        }

        boolean resultado = notaDAO.editar(incidenciaId, notaId, nota);
        if (resultado) {
            return Resultados.ok();
        }
        return Resultados.notFound();
    }

    public Result eliminar(
            @PathParam("incidenciaId") Long incidenciaId,
            @PathParam("notaId") Long notaId) {

        boolean resultado = notaDAO.eliminar(incidenciaId, notaId);
        if (resultado) {
            return Resultados.ok();
        }
        return Resultados.notFound();
    }

}
