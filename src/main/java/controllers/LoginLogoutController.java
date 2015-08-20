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
package controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dao.UsuarioDAO;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.params.Param;
import ninja.validation.Length;
import ninja.validation.Required;
import ninja.validation.Validation;

@Singleton
public class LoginLogoutController {

    @Inject
    private UsuarioDAO userDao;

    public Result login(Context context) {
        return Results.html();
    }

    public Result loginPost(
            @Param("usuario") @Required @Length(min = 5) String usuario,
            @Param("clave") @Required @Length(min = 5) String clave,
            Context context,
            Validation validation) {
        if (validation.hasViolations()) {
        }
        boolean isUserNameAndPasswordValid = this.userDao.isUserAndPasswordValid(usuario, clave);
        if (isUserNameAndPasswordValid) {
            context.getSession().put("username", usuario);
            context.getFlashScope().success("login.loginSuccessful");

            return Results.redirect("/");
        }
        context.getFlashScope().put("username", usuario);
        context.getFlashScope().error("login.errorLogin");

        return Results.redirect("/login");
    }

    public Result logout(Context context) {
        context.getSession().clear();
        context.getFlashScope().success("login.logoutSuccessful");
        return Results.redirect("/login");
    }
}
