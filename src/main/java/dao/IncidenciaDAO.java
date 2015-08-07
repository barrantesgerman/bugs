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
import java.util.List;
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
    public List<Incidencia> listar() {
        QIncidencia qi = QIncidencia.incidencia;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        return query
                .selectFrom(qi)
                .where(
                        qi.activo.isTrue())
                .fetch();
    }

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
    public Optional<Incidencia> crear(Incidencia incidencia) {
        EntityManager em = entitiyManagerProvider.get();
        incidencia.setActivo(true);
        incidencia.setFechaCreacion(new Date());
        em.persist(incidencia);
        return Optional.of(incidencia);
    }

    @Transactional
    public boolean editar(long id, Incidencia incidencia) {
        QIncidencia qi = QIncidencia.incidencia;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        return query
                .update(qi)
                .set(qi.descripcion, incidencia.getDescripcion())
                .set(qi.estado, incidencia.getEstado())
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
