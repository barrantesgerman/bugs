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
import dao.CategoriaDAO;
import dtos.Resultados;
import java.util.List;
import javax.inject.Inject;
import models.Categoria;
import ninja.Result;
import ninja.params.PathParam;

/**
 *
 * @author Herman
 * @since 22/07/2015
 */
public class CategoriaApiController {

    @Inject
    private CategoriaDAO categoriaDAO;

    public Result listar(@PathParam("proyectoId") Long proyectoId) {

        List<Categoria> categorias = categoriaDAO.listar(proyectoId);
        if (!categorias.isEmpty()) {
            return Resultados.ok(categorias);
        }
        return Resultados.notFound();
    }

    public Result buscar(
            @PathParam("proyectoId") Long proyectoId,
            @PathParam("categoriaId") Long categoriaId) {

        Optional<Categoria> categoria = categoriaDAO.buscar(proyectoId, categoriaId);
        if (categoria.isPresent()) {
            return Resultados.ok(categoria.get());
        }
        return Resultados.notFound();
    }

    public Result crear(
            @PathParam("proyectoId") Long proyectoId,
            Categoria categoria) {

        Optional<Categoria> resultado = categoriaDAO.crear(proyectoId, categoria);
        if (resultado.isPresent()) {
            return Resultados.created(resultado.get());
        }
        return Resultados.notFound();
    }

    public Result editar(
            @PathParam("proyectoId") Long proyectoId,
            @PathParam("categoriaId") Long categoriaId,
            Categoria categoria) {

        boolean resultado = categoriaDAO.editar(proyectoId, categoriaId, categoria);
        if (resultado) {
            return Resultados.ok();
        }
        return Resultados.notFound();
    }

    public Result eliminar(
            @PathParam("proyectoId") Long proyectoId,
            @PathParam("categoriaId") Long categoriaId) {

        boolean resultado = categoriaDAO.eliminar(proyectoId, categoriaId);
        if (resultado) {
            return Resultados.ok();
        }
        return Resultados.notFound();
    }

}
