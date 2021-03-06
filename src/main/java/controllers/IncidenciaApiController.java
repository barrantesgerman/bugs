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
import dao.IncidenciaDAO;
import dtos.FiltroIncidenciaDTO;
import dtos.IncidenciaDTO;
import dtos.PaginaDTO;
import dtos.Resultados;
import etc.FiltroIncidencia;
import etc.Pagina;
import etc.UsuarioId;
import java.util.List;
import javax.inject.Inject;
import models.Incidencia;
import ninja.Result;
import ninja.params.PathParam;

/**
 *
 * @author Herman
 * @since 22/07/2015
 */
public class IncidenciaApiController {

    @Inject
    private IncidenciaDAO incidenciaDAO;

    public Result listar(
            @FiltroIncidencia FiltroIncidenciaDTO filtro,
            @Pagina PaginaDTO pagina) {

        List<IncidenciaDTO> incidencias
                = incidenciaDAO.listar(filtro, pagina);
        Long total = incidenciaDAO.cantidad(filtro);
        pagina.setCantidad(incidencias.size());
        pagina.setTotal(total.intValue());

        if (!incidencias.isEmpty()) {
            return Resultados.ok(incidencias, pagina);
        }
        return Resultados.notFound();
    }

    public Result buscar(@PathParam("id") Long id) {

        Optional<IncidenciaDTO> incidencia = incidenciaDAO.buscar(id);
        if (incidencia.isPresent()) {
            return Resultados.ok(incidencia.get());
        }
        return Resultados.notFound();
    }

    public Result crear(@UsuarioId Long usuarioId, Incidencia incidencia) {

        Optional<Incidencia> resultado = incidenciaDAO.crear(usuarioId, incidencia);
        if (resultado.isPresent()) {
            return Resultados.created(resultado.get());
        }
        return Resultados.notFound();
    }

    public Result editar(
            @PathParam("id") Long id,
            @UsuarioId Long usuarioId,
            Incidencia incidencia) {

        boolean resultado = incidenciaDAO.editar(id, usuarioId, incidencia);
        if (resultado) {
            return Resultados.ok();
        }
        return Resultados.notFound();
    }

    public Result eliminar(@PathParam("id") Long id) {

        boolean resultado = incidenciaDAO.eliminar(id);
        if (resultado) {
            return Resultados.ok();
        }
        return Resultados.notFound();
    }
}
