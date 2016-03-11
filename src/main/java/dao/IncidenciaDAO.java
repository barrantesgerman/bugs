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
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Date;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import models.Incidencia;
import models.QIncidencia;
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

    @UnitOfWork
    public Optional<Incidencia> buscar(long id) {
        QIncidencia qi = QIncidencia.incidencia;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        return Optional.fromNullable(query
                .selectFrom(qi)
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
