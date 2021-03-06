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
import dao.ProyectoDAO;
import dtos.Resultados;
import java.util.List;
import javax.inject.Inject;
import models.Proyecto;
import ninja.Result;
import ninja.params.PathParam;

/**
 *
 * @author Herman
 * @since 22/07/2015
 */
public class ProyectoApiController {

    @Inject
    private ProyectoDAO proyectoDAO;

    public Result listar() {

        List<Proyecto> proyectos = proyectoDAO.listar();
        if (!proyectos.isEmpty()) {
            return Resultados.ok(proyectos);
        }
        return Resultados.notFound();
    }

    public Result buscar(@PathParam("id") Long id) {

        Optional<Proyecto> proyecto = proyectoDAO.buscar(id);
        if (proyecto.isPresent()) {
            return Resultados.ok(proyecto.get());
        }
        return Resultados.notFound();
    }

    public Result crear(Proyecto proyecto) {

        Optional<Proyecto> resultado = proyectoDAO.crear(proyecto);
        if (resultado.isPresent()) {
            return Resultados.created(resultado.get());
        }
        return Resultados.notFound();
    }

    public Result editar(@PathParam("id") Long id, Proyecto proyecto) {

        boolean resultado = proyectoDAO.editar(id, proyecto);
        if (resultado) {
            return Resultados.ok();
        }
        return Resultados.notFound();
    }

    public Result eliminar(@PathParam("id") Long id) {

        boolean resultado = proyectoDAO.eliminar(id);
        if (resultado) {
            return Resultados.ok();
        }
        return Resultados.notFound();
    }
}
