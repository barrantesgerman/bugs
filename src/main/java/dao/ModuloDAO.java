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
import models.Modulo;
import models.QModulo;
import ninja.jpa.UnitOfWork;

/**
 *
 * @author Herman
 * @since 24/07/2015
 */
public class ModuloDAO {

    @Inject
    private Provider<JPAQueryFactory> jpaQueryFactoryProvider;
    @Inject
    private Provider<EntityManager> entitiyManagerProvider;

    @UnitOfWork
    public List<Modulo> listar(long proyectoId) {
        QModulo qm = QModulo.modulo;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        return query
                .selectFrom(qm)
                .where(
                        qm.proyectoId.eq(proyectoId),
                        qm.activo.isTrue())
                .orderBy(qm.nombre.asc())
                .fetch();
    }

    @UnitOfWork
    public Optional<Modulo> buscar(long proyectoId, long moduloId) {
        QModulo qm = QModulo.modulo;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        return Optional.fromNullable(query
                .selectFrom(qm)
                .where(
                        qm.id.eq(moduloId),
                        qm.proyectoId.eq(proyectoId),
                        qm.activo.isTrue())
                .fetchOne());
    }

    @Transactional
    public Optional<Modulo> crear(long proyectoId, Modulo modulo) {
        EntityManager em = entitiyManagerProvider.get();
        modulo.setProyectoId(proyectoId);
        modulo.setActivo(true);
        em.persist(modulo);
        return Optional.of(modulo);
    }

    @Transactional
    public boolean editar(long proyectoId, long moduloId, Modulo modulo) {
        QModulo qm = QModulo.modulo;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        return query
                .update(qm)
                .set(qm.nombre, modulo.getNombre())
                .where(
                        qm.id.eq(moduloId),
                        qm.proyectoId.eq(proyectoId),
                        qm.activo.isTrue())
                .execute() > 0L;
    }

    @Transactional
    public boolean eliminar(long proyectoId, long moduloId) {
        QModulo qm = QModulo.modulo;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        return query
                .update(qm)
                .set(qm.activo, false)
                .where(
                        qm.id.eq(moduloId),
                        qm.proyectoId.eq(proyectoId),
                        qm.activo.isTrue())
                .execute() > 0L;
    }
}
