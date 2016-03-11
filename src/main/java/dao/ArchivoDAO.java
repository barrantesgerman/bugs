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
import dtos.ArchivoDTO;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import models.Archivo;
import models.QArchivo;
import models.QXArchivoUsuario;
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
    public List<ArchivoDTO> listar(long incidenciaId) {
        QXArchivoUsuario qa = QXArchivoUsuario.xArchivoUsuario;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        return query
                .select(
                        Projections.constructor(
                                ArchivoDTO.class,
                                qa.id,
                                qa.usuario.usuario,
                                qa.nombre,
                                qa.mimeType,
                                qa.fecha))
                .from(qa)
                .where(
                        qa.incidenciaId.eq(incidenciaId),
                        qa.activo.isTrue())
                .orderBy(qa.fecha.asc())
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
    public boolean crear(long incidenciaId, Archivo archivo) {
        EntityManager em = entitiyManagerProvider.get();
        archivo.setIncidenciaId(incidenciaId);
        archivo.setActivo(true);
        archivo.setFecha(new Date());
        em.persist(archivo);
        return true;
    }

    @Transactional
    public boolean eliminar(long incidenciaId, long archivoId) {
        QArchivo qa = QArchivo.archivo;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        return query
                .delete(qa)
                .where(
                        qa.id.eq(archivoId),
                        qa.incidenciaId.eq(incidenciaId))
                .execute() > 0L;
    }
}
