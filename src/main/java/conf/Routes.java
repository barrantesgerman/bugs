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
package conf;

import ninja.AssetsController;
import ninja.Router;
import ninja.application.ApplicationRoutes;
import ninja.utils.NinjaProperties;
import com.google.inject.Inject;
import controllers.ApplicationController;
import controllers.CategoriaApiController;
import controllers.LoginLogoutController;
import controllers.ProyectoApiController;

public class Routes implements ApplicationRoutes {

    @Inject
    private NinjaProperties ninjaProperties;

    /**
     * Using a (almost) nice DSL we can configure the router.
     *
     * The second argument NinjaModuleDemoRouter contains all routes of a
     * submodule. By simply injecting it we activate the routes.
     *
     * @param router The default router of this application
     */
    @Override
    public void init(Router router) {

        // puts test data into db:
//        if (!ninjaProperties.isProd()) {
//            router.GET().route("/setup").with(ApplicationController.class, "setup");
//        }

        ///////////////////////////////////////////////////////////////////////
        // Proyecto API Controller
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/api/proyectos").with(ProyectoApiController.class, "listar");
        router.GET().route("/api/proyectos/{id: [0-9]+}").with(ProyectoApiController.class, "buscar");
        router.POST().route("/api/proyectos").with(ProyectoApiController.class, "crear");
        router.PUT().route("/api/proyectos/{id: [0-9]+}").with(ProyectoApiController.class, "editar");
        router.DELETE().route("/api/proyectos/{id: [0-9]+}").with(ProyectoApiController.class, "eliminar");
        
        ///////////////////////////////////////////////////////////////////////
        // Categoría API Controller
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/api/proyectos/{proyectoId: [0-9]+}/categorias").with(CategoriaApiController.class, "listar");
        router.GET().route("/api/proyectos/{proyectoId: [0-9]+}/categorias/{categoriaId: [0-9]+}").with(CategoriaApiController.class, "buscar");
        
        ///////////////////////////////////////////////////////////////////////
        // Login / Logout
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/login").with(LoginLogoutController.class, "login");
        router.POST().route("/login").with(LoginLogoutController.class, "loginPost");
        router.GET().route("/logout").with(LoginLogoutController.class, "logout");
        
        ///////////////////////////////////////////////////////////////////////
        // Assets (fotos/javascript/css)
        ///////////////////////////////////////////////////////////////////////    
        router.GET().route("/assets/webjars/{fileName: .*}").with(AssetsController.class, "serveWebJars");
        router.GET().route("/assets/{fileName: .*}").with(AssetsController.class, "serveStatic");

        ///////////////////////////////////////////////////////////////////////
        // Index
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/").with(ApplicationController.class, "index");
    }

}