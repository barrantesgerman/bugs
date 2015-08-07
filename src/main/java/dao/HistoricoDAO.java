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
import models.Historico;
import models.QHistorico;
import ninja.jpa.UnitOfWork;

/**
 *
 * @author Herman
 * @since 22/07/2015
 */
public class HistoricoDAO {

    @Inject
    private Provider<JPAQueryFactory> jpaQueryFactoryProvider;
    @Inject
    private Provider<EntityManager> entitiyManagerProvider;

    @UnitOfWork
    public List<Historico> listar(long incidenciaId) {
        QHistorico qh = QHistorico.historico;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        return query
                .selectFrom(qh)
                .where(
                        qh.incidenciaId.eq(incidenciaId),
                        qh.activo.isTrue())
                .orderBy(qh.fecha.asc())
                .fetch();
    }

    @UnitOfWork
    public Optional<Historico> buscar(long incidenciaId, long historicoId) {
        QHistorico qh = QHistorico.historico;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        return Optional.fromNullable(query
                .selectFrom(qh)
                .where(
                        qh.id.eq(historicoId),
                        qh.incidenciaId.eq(incidenciaId),
                        qh.activo.isTrue())
                .fetchOne());
    }

    @Transactional
    public Optional<Historico> crear(long incidenciaId, Historico historico) {
        EntityManager em = entitiyManagerProvider.get();
        historico.setIncidenciaId(incidenciaId);
        historico.setFecha(new Date());
        historico.setActivo(true);
        em.persist(historico);
        return Optional.of(historico);
    }

}
