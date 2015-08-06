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
import models.Nota;
import models.QNota;
import ninja.jpa.UnitOfWork;

/**
 *
 * @author Herman
 * @since 22/07/2015
 */
public class NotaDAO {

    @Inject
    private Provider<JPAQueryFactory> jpaQueryFactoryProvider;
    @Inject
    private Provider<EntityManager> entitiyManagerProvider;

    @UnitOfWork
    public List<Nota> listar(long incidenciaId) {
        QNota qn = QNota.nota1;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        return query
                .selectFrom(qn)
                .where(
                        qn.incidenciaId.eq(incidenciaId),
                        qn.activo.isTrue())
                .fetch();
    }

    @UnitOfWork
    public Optional<Nota> buscar(long incidenciaId, long notaId) {
        QNota qn = QNota.nota1;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        return Optional.fromNullable(query
                .selectFrom(qn)
                .where(
                        qn.id.eq(notaId),
                        qn.incidenciaId.eq(incidenciaId),
                        qn.activo.isTrue())
                .fetchOne());
    }

    @Transactional
    public Optional<Nota> crear(long incidenciaId, Nota nota) {
        EntityManager em = entitiyManagerProvider.get();
        nota.setIncidenciaId(incidenciaId);
        nota.setFecha(new Date());
        nota.setActivo(true);
        em.persist(nota);
        return Optional.of(nota);
    }

    @Transactional
    public boolean editar(long incidenciaId, long notaId, Nota nota) {
        QNota qn = QNota.nota1;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        return query
                .update(qn)
                .set(qn.nota, nota.getNota())
                .set(qn.usuario, nota.getUsuario())
                .set(qn.fecha, nota.getFecha())
                .where(
                        qn.id.eq(notaId),
                        qn.incidenciaId.eq(incidenciaId),
                        qn.activo.isTrue())
                .execute() > 0L;
    }

    @Transactional
    public boolean eliminar(long incidenciaId, long notaId) {
        QNota qn = QNota.nota1;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        return query
                .update(qn)
                .set(qn.activo, false)
                .where(
                        qn.id.eq(notaId),
                        qn.incidenciaId.eq(incidenciaId),
                        qn.activo.isTrue())
                .execute() > 0L;
    }
}
