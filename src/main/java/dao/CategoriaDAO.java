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
import models.Categoria;
import models.QCategoria;
import ninja.jpa.UnitOfWork;

/**
 *
 * @author Herman
 * @since 22/07/2015
 */
public class CategoriaDAO {

    @Inject
    private Provider<JPAQueryFactory> jpaQueryFactoryProvider;
    @Inject
    private Provider<EntityManager> entitiyManagerProvider;

    @UnitOfWork
    public List<Categoria> listar(long proyectoId) {
        QCategoria qc = QCategoria.categoria;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        return query
                .selectFrom(qc)
                .where(
                        qc.proyectoId.eq(proyectoId),
                        qc.activo.eq(true))
                .fetch();
    }

    @UnitOfWork
    public Optional<Categoria> buscar(long proyectoId, long categoriaId) {
        QCategoria qc = QCategoria.categoria;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        return Optional.fromNullable(query
                .selectFrom(qc)
                .where(
                        qc.id.eq(categoriaId),
                        qc.proyectoId.eq(proyectoId),
                        qc.activo.eq(true))
                .fetchOne());
    }

    @Transactional
    public Optional<Categoria> crear(long proyectoId, Categoria categoria) {
        EntityManager em = entitiyManagerProvider.get();
        categoria.setProyectoId(proyectoId);
        categoria.setActivo(true);
        em.persist(categoria);
        return Optional.of(categoria);
    }

    @Transactional
    public boolean editar(long proyectoId, long categoriaId, Categoria categoria) {
        QCategoria qc = QCategoria.categoria;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        return query
                .update(qc)
                .set(qc.descripcion, categoria.getDescripcion())
                .where(
                        qc.id.eq(categoriaId),
                        qc.proyectoId.eq(proyectoId))
                .execute() > 0L;
    }

    @Transactional
    public boolean eliminar(long proyectoId, long categoriaId) {
        QCategoria qc = QCategoria.categoria;
        JPAQueryFactory query = jpaQueryFactoryProvider.get();
        return query
                .update(qc)
                .set(qc.activo, false)
                .where(
                        qc.id.eq(categoriaId),
                        qc.proyectoId.eq(proyectoId))
                .execute() > 0L;
    }
}
