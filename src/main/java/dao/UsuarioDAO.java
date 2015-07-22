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
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.inject.Inject;
import javax.inject.Provider;
import models.QUsuario;
import models.Usuario;
import ninja.jpa.UnitOfWork;

/**
 *
 * @author Herman
 * @since 22/07/2015
 */
public class UsuarioDAO {

    @Inject
    private Provider<JPAQueryFactory> jpaQueryFactoryProvider;

    @UnitOfWork
    public Optional<Usuario> obtenerUsuario(String nombreUsuario, String clave) {
        if (nombreUsuario != null && clave != null) {
            QUsuario qu = new QUsuario("u");
            JPAQueryFactory query = jpaQueryFactoryProvider.get();
            return Optional.fromNullable(query
                    .select(qu)
                    .from(qu)
                    .where(
                            qu.usuario.eq(nombreUsuario),
                            qu.clave.eq(clave),
                            qu.activo.isTrue())
                    .fetchOne());
        }
        return Optional.absent();
    }

    @UnitOfWork
    public boolean isUserAndPasswordValid(String usuario, String clave) {
        if (usuario != null && clave != null) {
            QUsuario qu = new QUsuario("u");
            JPAQueryFactory query = jpaQueryFactoryProvider.get();
            return query
                    .select(qu.count())
                    .from(qu)
                    .where(
                            qu.usuario.eq(usuario),
                            qu.clave.eq(clave),
                            qu.activo.isTrue())
                    .fetchOne() > 0L;
        }
        return false;
    }
}
