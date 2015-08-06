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
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dtos.ArchivoLiteDTO;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import models.Archivo;
import models.QArchivo;
import ninja.jpa.UnitOfWork;

/**
 *
 * @author Herman
 * @since 22/07/2015
 */
public class ArchivoDAO {

    @Inject
    private Provider<JPAQueryFactory> jpaQueryFactoryProvider;
    @Inject
    private Provider<EntityManager> entitiyManagerProvider;

    @UnitOfWork
    public List<ArchivoLiteDTO> listar(long incidenciaId) {
        QArchivo qa = QArchivo.archivo;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        return query
                .select(
                        Projections.constructor(
                                ArchivoLiteDTO.class,
                                qa.id,
                                qa.usuario,
                                qa.nombre,
                                qa.mimeType))
                .from(qa)
                .where(
                        qa.incidenciaId.eq(incidenciaId),
                        qa.activo.isTrue())
                .fetch();
    }

    @UnitOfWork
    public Optional<Archivo> buscar(long incidenciaId, long archivoId) {
        QArchivo qa = QArchivo.archivo;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        return Optional.fromNullable(query
                .selectFrom(qa)
                .where(
                        qa.id.eq(archivoId),
                        qa.incidenciaId.eq(incidenciaId),
                        qa.activo.isTrue())
                .fetchOne());
    }

    @Transactional
    public Optional<Archivo> crear(long incidenciaId, Archivo nota) {
        EntityManager em = entitiyManagerProvider.get();
        nota.setIncidenciaId(incidenciaId);
        nota.setActivo(true);
        em.persist(nota);
        return Optional.of(nota);
    }

    @Transactional
    public boolean eliminar(long incidenciaId, long archivoId) {
        QArchivo qa = QArchivo.archivo;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        return query
                .update(qa)
                .set(qa.activo, false)
                .where(
                        qa.id.eq(archivoId),
                        qa.incidenciaId.eq(incidenciaId),
                        qa.activo.isTrue())
                .execute() > 0L;
    }
}
