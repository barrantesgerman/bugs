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
import dtos.IncidenciaViewDTO;
import dtos.PaginaDTO;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import models.EstadoIncidencia;
import models.Prioridad;
import models.QIncidenciaView;
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

    private JPAQuery<IncidenciaViewDTO> filtrar(FiltroIncidenciaDTO filtro) {
        QIncidenciaView qi = QIncidenciaView.incidenciaView;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        JPAQuery<IncidenciaViewDTO> consulta = query
                .select(
                        Projections.constructor(
                                IncidenciaViewDTO.class,
                                qi.id,
                                qi.proyectoId,
                                qi.proyectoNombre,
                                qi.moduloId,
                                qi.moduloNombre,
                                qi.categoriaId,
                                qi.categoriaDescripcion,
                                qi.estado,
                                qi.prioridad,
                                qi.reproducibilidad,
                                qi.resolucion,
                                qi.resumen,
                                qi.fechaActualizacion,
                                qi.usuarioActualizacion))
                .from(qi)
                .where(qi.activo.isTrue());
        if (filtro.getProyectoId().isPresent()) {
            consulta.where(qi.proyectoId.eq(filtro.getProyectoId().get()));
        }
        if (filtro.getModuloId().isPresent()) {
            consulta.where(qi.moduloId.eq(filtro.getModuloId().get()));
        }
        if (filtro.getCategoriaId().isPresent()) {
            consulta.where(qi.categoriaId.eq(filtro.getCategoriaId().get()));
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

        JPAQuery<IncidenciaViewDTO> consulta = filtrar(filtro);
        return consulta.fetchCount();
    }

    @UnitOfWork
    public List<IncidenciaViewDTO> listar(
            FiltroIncidenciaDTO filtro,
            PaginaDTO pagina) {

        JPAQuery<IncidenciaViewDTO> consulta = filtrar(filtro);

        consulta.limit(pagina.getTamano());
        consulta.offset(pagina.getPagina());

        return consulta.fetch();
    }

}
