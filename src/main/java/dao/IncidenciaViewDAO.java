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
package dao;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dtos.FiltroIncidenciaDTO;
import dtos.IncidenciaDTO;
import dtos.PaginaDTO;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import models.EstadoIncidencia;
import models.Prioridad;
import models.QXIncidenciaCompleto;
import models.Reproducibilidad;
import models.Resolucion;
import ninja.jpa.UnitOfWork;

/**
 *
 * @author Herman Barrantes
 * @since 19/08/2015
 */
public class IncidenciaViewDAO {

    @Inject
    private Provider<JPAQueryFactory> jpaQueryFactoryProvider;

    private JPAQuery<IncidenciaDTO> filtrar(FiltroIncidenciaDTO filtro) {
        QXIncidenciaCompleto qi = QXIncidenciaCompleto.xIncidenciaCompleto;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        JPAQuery<IncidenciaDTO> consulta = query
                .select(
                        Projections.constructor(
                                IncidenciaDTO.class,
                                qi.id,
                                qi.proyecto.id,
                                qi.proyecto.nombre,
                                qi.modulo.id,
                                qi.modulo.nombre,
                                qi.categoria.id,
                                qi.categoria.descripcion,
                                qi.estado,
                                qi.prioridad,
                                qi.reproducibilidad,
                                qi.resolucion,
                                qi.resumen,
                                qi.fechaActualizacion,
                                qi.usuarioActualizacion.usuario))
                .from(qi)
                .innerJoin(qi.proyecto)
                .leftJoin(qi.modulo)
                .innerJoin(qi.categoria)
                .innerJoin(qi.usuarioActualizacion)
                .where(qi.activo.isTrue());
        if (filtro.getProyectoId().isPresent()) {
            consulta.where(qi.proyecto.id.eq(filtro.getProyectoId().get()));
        }
        if (filtro.getModuloId().isPresent()) {
            consulta.where(qi.modulo.id.eq(filtro.getModuloId().get()));
        }
        if (filtro.getCategoriaId().isPresent()) {
            consulta.where(qi.categoria.id.eq(filtro.getCategoriaId().get()));
        }
        if (!filtro.getEstado().isEmpty()) {
            BooleanBuilder builder = new BooleanBuilder();
            for (EstadoIncidencia estado : filtro.getEstado()) {
                builder.or(qi.estado.eq(estado));
            }
            consulta.where(builder);
        }
        if (!filtro.getPrioridad().isEmpty()) {
            BooleanBuilder builder = new BooleanBuilder();
            for (Prioridad prioridad : filtro.getPrioridad()) {
                builder.or(qi.prioridad.eq(prioridad));
            }
            consulta.where(builder);
        }
        if (!filtro.getReproducibilidad().isEmpty()) {
            BooleanBuilder builder = new BooleanBuilder();
            for (Reproducibilidad reproducibilidad : filtro.getReproducibilidad()) {
                builder.or(qi.reproducibilidad.eq(reproducibilidad));
            }
            consulta.where(builder);
        }
        if (!filtro.getResolucion().isEmpty()) {
            BooleanBuilder builder = new BooleanBuilder();
            for (Resolucion resolucion : filtro.getResolucion()) {
                builder.or(qi.resolucion.eq(resolucion));
            }
            consulta.where(builder);
        }
        if (filtro.getResumen().isPresent()) {
            consulta.where(qi.resumen.containsIgnoreCase(filtro.getResumen().get()));
        }
        return consulta;
    }

    @UnitOfWork
    public long cantidad(FiltroIncidenciaDTO filtro) {

        JPAQuery<IncidenciaDTO> consulta = filtrar(filtro);
        return consulta.fetchCount();
    }

    @UnitOfWork
    public List<IncidenciaDTO> listar(
            FiltroIncidenciaDTO filtro,
            PaginaDTO pagina) {

        JPAQuery<IncidenciaDTO> consulta = filtrar(filtro);

        consulta.limit(pagina.getTamano());
        consulta.offset(pagina.getPagina());

        return consulta.fetch();
    }

}
