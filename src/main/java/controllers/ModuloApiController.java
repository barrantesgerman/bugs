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
import dao.ModuloDAO;
import java.util.List;
import javax.inject.Inject;
import models.Modulo;
import dtos.Resultados;
import ninja.Result;
import ninja.params.PathParam;

/**
 *
 * @author Herman
 * @since 22/07/2015
 */
public class ModuloApiController {

    @Inject
    private ModuloDAO moduloDAO;

    public Result listar(@PathParam("proyectoId") Long proyectoId) {

        List<Modulo> modulos = moduloDAO.listar(proyectoId);
        if (!modulos.isEmpty()) {
            return Resultados.ok(modulos);
        }
        return Resultados.notFound();
    }

    public Result buscar(
            @PathParam("proyectoId") Long proyectoId,
            @PathParam("moduloId") Long moduloId) {

        Optional<Modulo> modulo = moduloDAO.buscar(proyectoId, moduloId);
        if (modulo.isPresent()) {
            return Resultados.ok(modulo.get());
        }
        return Resultados.notFound();
    }

    public Result crear(
            @PathParam("proyectoId") Long proyectoId,
            Modulo modulo) {

        Optional<Modulo> resultado = moduloDAO.crear(proyectoId, modulo);
        if (resultado.isPresent()) {
            return Resultados.created(resultado.get());
        }
        return Resultados.notFound();
    }

    public Result editar(
            @PathParam("proyectoId") Long proyectoId,
            @PathParam("moduloId") Long moduloId,
            Modulo modulo) {

        boolean resultado = moduloDAO.editar(proyectoId, moduloId, modulo);
        if (resultado) {
            return Resultados.ok();
        }
        return Resultados.notFound();
    }

    public Result eliminar(
            @PathParam("proyectoId") Long proyectoId,
            @PathParam("moduloId") Long moduloId) {

        boolean resultado = moduloDAO.eliminar(proyectoId, moduloId);
        if (resultado) {
            return Resultados.ok();
        }
        return Resultados.notFound();
    }

}
