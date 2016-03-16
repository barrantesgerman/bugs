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

import com.google.common.base.Optional;
import com.google.inject.persist.Transactional;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dtos.FiltroIncidenciaDTO;
import dtos.IncidenciaDTO;
import dtos.PaginaDTO;
import dtos.QIncidenciaDTO;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import models.Incidencia;
import models.QIncidencia;
import models.QXIncidencia;
import models.enums.EstadoIncidencia;
import models.enums.Prioridad;
import models.enums.Reproducibilidad;
import models.enums.Resolucion;
import ninja.jpa.UnitOfWork;

/**
 *
 * @author Herman
 * @since 22/07/2015
 */
public class IncidenciaDAO {

    @Inject
    private Provider<JPAQueryFactory> jpaQueryFactoryProvider;
    @Inject
    private Provider<EntityManager> entitiyManagerProvider;

    private JPAQuery<IncidenciaDTO> filtrar(FiltroIncidenciaDTO filtro) {
        QXIncidencia qi = QXIncidencia.xIncidencia;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        JPAQuery<IncidenciaDTO> consulta = query
                .select(
                        new QIncidenciaDTO(
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

    @UnitOfWork
    public Optional<IncidenciaDTO> buscar(long id) {
        QXIncidencia qi = QXIncidencia.xIncidencia;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        return Optional.fromNullable(query
                .select(
                        new QIncidenciaDTO(
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
                                qi.usuarioActualizacion.usuario,
                                qi.descripcion,
                                qi.pasos,
                                qi.informacionAdicional,
                                qi.fechaCreacion,
                                qi.usuarioCreacion.usuario,
                                qi.fechaAsignacion,
                                qi.usuarioAsignacion.usuario,
                                qi.fechaAtencion,
                                qi.usuarioAtencion.usuario,
                                qi.fechaResolucion,
                                qi.usuarioResolucion.usuario,
                                qi.fechaRevision,
                                qi.usuarioRevision.usuario,
                                qi.fechaCierre,
                                qi.usuarioCierre.usuario))
                .from(qi)
                .innerJoin(qi.proyecto)
                .leftJoin(qi.modulo)
                .innerJoin(qi.categoria)
                .innerJoin(qi.usuarioActualizacion)
                .leftJoin(qi.usuarioCreacion)
                .leftJoin(qi.usuarioAsignacion)
                .leftJoin(qi.usuarioAtencion)
                .leftJoin(qi.usuarioResolucion)
                .leftJoin(qi.usuarioRevision)
                .leftJoin(qi.usuarioCierre)
                .where(
                        qi.id.eq(id),
                        qi.activo.isTrue())
                .fetchOne());
    }

    @Transactional
    public Optional<Incidencia> crear(Long usuarioId, Incidencia incidencia) {
        EntityManager em = entitiyManagerProvider.get();
        incidencia.setActivo(true);
        incidencia.setFechaCreacion(new Date());
        incidencia.setFechaActualizacion(new Date());
        incidencia.setUsuarioCreacionId(usuarioId);
        incidencia.setUsuarioActualizacionId(usuarioId);
        em.persist(incidencia);
        return Optional.of(incidencia);
    }

    @Transactional
    public boolean editar(long id, Long usuarioId, Incidencia incidencia) {
        QIncidencia qi = QIncidencia.incidencia;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        return query
                .update(qi)
                .set(qi.proyectoId, incidencia.getProyectoId())
                .set(qi.moduloId, incidencia.getModuloId())
                .set(qi.categoriaId, incidencia.getCategoriaId())
                .set(qi.estado, incidencia.getEstado())
                .set(qi.prioridad, incidencia.getPrioridad())
                .set(qi.reproducibilidad, incidencia.getReproducibilidad())
                .set(qi.resolucion, incidencia.getResolucion())
                .set(qi.resumen, incidencia.getResumen())
                .set(qi.descripcion, incidencia.getDescripcion())
                .set(qi.pasos, incidencia.getPasos())
                .set(qi.informacionAdicional, incidencia.getInformacionAdicional())
                .set(qi.fechaActualizacion, new Date())
                .set(qi.usuarioActualizacionId, usuarioId)
                .where(
                        qi.id.eq(id),
                        qi.activo.isTrue())
                .execute() > 0L;
    }

    @Transactional
    public boolean eliminar(long id) {
        QIncidencia qi = QIncidencia.incidencia;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        return query
                .update(qi)
                .set(qi.activo, false)
                .where(
                        qi.id.eq(id),
                        qi.activo.isTrue())
                .execute() > 0L;
    }
}
