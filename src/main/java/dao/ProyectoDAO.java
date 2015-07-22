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
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import models.Proyecto;
import models.QProyecto;
import ninja.jpa.UnitOfWork;

/**
 *
 * @author Herman
 * @since 22/07/2015
 */
public class ProyectoDAO {

    @Inject
    private Provider<JPAQueryFactory> jpaQueryFactoryProvider;
    @Inject
    private Provider<EntityManager> entitiyManagerProvider;

    @UnitOfWork
    public List<Proyecto> listar() {
        QProyecto qp = QProyecto.proyecto;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        return query
                .selectFrom(qp)
                .where(
                        qp.activo.eq(true))
                .fetch();
    }

    @UnitOfWork
    public Optional<Proyecto> buscar(long id) {
        QProyecto qp = QProyecto.proyecto;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        return Optional.fromNullable(query
                .selectFrom(qp)
                .where(
                        qp.id.eq(id),
                        qp.activo.eq(true))
                .fetchOne());
    }

    @Transactional
    public Optional<Proyecto> crear(Proyecto proyecto) {
        EntityManager em = entitiyManagerProvider.get();
        proyecto.setActivo(true);
        em.persist(proyecto);
        return Optional.of(proyecto);
    }

    @Transactional
    public boolean editar(long id, Proyecto proyecto) {
        QProyecto qp = QProyecto.proyecto;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        return query
                .update(qp)
                .set(qp.nombre, proyecto.getNombre())
                .set(qp.descripcion, proyecto.getDescripcion())
                .set(qp.estado, proyecto.getEstado())
                .where(
                        qp.id.eq(id))
                .execute() > 0L;
    }

    @Transactional
    public boolean eliminar(long id) {
        QProyecto qp = QProyecto.proyecto;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        return query
                .update(qp)
                .set(qp.activo, false)
                .where(
                        qp.id.eq(id))
                .execute() > 0L;
    }
}
