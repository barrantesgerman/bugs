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
import java.util.List;
import javax.inject.Inject;
import models.Categoria;
import ninja.Result;
import ninja.Results;
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
            return Results.json().render(categorias);
        }
        return Results.notFound().json();
    }

    public Result buscar(
            @PathParam("proyectoId") Long proyectoId,
            @PathParam("categoriaId") Long categoriaId) {
        Optional<Categoria> categoria = categoriaDAO.buscar(proyectoId, categoriaId);
        if (categoria.isPresent()) {
            return Results.json().render(categoria.get());
        }
        return Results.notFound().json();
    }
//
//    @FilterWith({XmlAndJsonResult.class})
//    public Result crear(Proyecto proyecto) {
//        Optional<Proyecto> resultado = proyectoDAO.crear(proyecto);
//        if (resultado.isPresent()) {
//            return Results.json().render(resultado.get());
//        }
//        return Results.notFound();
//    }
//
//    @FilterWith({XmlAndJsonResult.class})
//    public Result editar(@PathParam("id") Long id, Proyecto proyecto) {
//        boolean resultado = proyectoDAO.editar(id, proyecto);
//        if (resultado) {
//            return Results.json();
//        }
//        return Results.notFound();
//    }
//
//    @FilterWith({XmlAndJsonResult.class})
//    public Result eliminar(@PathParam("id") Long id) {
//        boolean resultado = proyectoDAO.eliminar(id);
//        if (resultado) {
//            return Results.json();
//        }
//        return Results.notFound();
//    }
}
