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

import com.google.inject.Inject;
import controllers.ArchivoApiController;
import controllers.CatalogoApiController;
import controllers.CategoriaApiController;
import controllers.IncidenciaApiController;
import controllers.InicioController;
import controllers.LoginLogoutController;
import controllers.ModuloApiController;
import controllers.NotaApiController;
import controllers.ProyectoApiController;
import ninja.AssetsController;
import ninja.Router;
import ninja.application.ApplicationRoutes;
import ninja.utils.NinjaProperties;

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
        router.POST().route("/api/proyectos/{proyectoId: [0-9]+}/categorias").with(CategoriaApiController.class, "crear");
        router.PUT().route("/api/proyectos/{proyectoId: [0-9]+}/categorias/{categoriaId: [0-9]+}").with(CategoriaApiController.class, "editar");
        router.DELETE().route("/api/proyectos/{proyectoId: [0-9]+}/categorias/{categoriaId: [0-9]+}").with(CategoriaApiController.class, "eliminar");

        ///////////////////////////////////////////////////////////////////////
        // Modulo API Controller
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/api/proyectos/{proyectoId: [0-9]+}/modulos").with(ModuloApiController.class, "listar");
        router.GET().route("/api/proyectos/{proyectoId: [0-9]+}/modulos/{moduloId: [0-9]+}").with(ModuloApiController.class, "buscar");
        router.POST().route("/api/proyectos/{proyectoId: [0-9]+}/modulos").with(ModuloApiController.class, "crear");
        router.PUT().route("/api/proyectos/{proyectoId: [0-9]+}/modulos/{moduloId: [0-9]+}").with(ModuloApiController.class, "editar");
        router.DELETE().route("/api/proyectos/{proyectoId: [0-9]+}/modulos/{moduloId: [0-9]+}").with(ModuloApiController.class, "eliminar");
        
        ///////////////////////////////////////////////////////////////////////
        // Incidencia API Controller
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/api/incidencias").with(IncidenciaApiController.class, "listar");
        
        ///////////////////////////////////////////////////////////////////////
        // Catálogo API Controller
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/api/catalogos/estadoIncidencia").with(CatalogoApiController.class, "estadoIncidencia");
        router.GET().route("/api/catalogos/estadoProyecto").with(CatalogoApiController.class, "estadoProyecto");
        router.GET().route("/api/catalogos/prioridad").with(CatalogoApiController.class, "prioridad");
        router.GET().route("/api/catalogos/reproducibilidad").with(CatalogoApiController.class, "reproducibilidad");
        router.GET().route("/api/catalogos/resolucion").with(CatalogoApiController.class, "resolucion");
        
        ///////////////////////////////////////////////////////////////////////
        // Nota API Controller
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/api/incidencias/{incidenciaId: [0-9]+}/notas").with(NotaApiController.class, "listar");
        router.GET().route("/api/incidencias/{incidenciaId: [0-9]+}/notas/{notaId: [0-9]+}").with(NotaApiController.class, "buscar");
        router.POST().route("/api/incidencias/{incidenciaId: [0-9]+}/notas").with(NotaApiController.class, "crear");
        router.PUT().route("/api/incidencias/{incidenciaId: [0-9]+}/notas/{notaId: [0-9]+}").with(NotaApiController.class, "editar");
        router.DELETE().route("/api/incidencias/{incidenciaId: [0-9]+}/notas/{notaId: [0-9]+}").with(NotaApiController.class, "eliminar");
        
        ///////////////////////////////////////////////////////////////////////
        // Archivo API Controller
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/api/incidencias/{incidenciaId: [0-9]+}/archivos").with(ArchivoApiController.class, "listar");
        router.GET().route("/api/incidencias/{incidenciaId: [0-9]+}/archivos/{modo: inline|attachment}/{archivoId: [0-9]+}").with(ArchivoApiController.class, "buscar");
        router.POST().route("/api/incidencias/{incidenciaId: [0-9]+}/archivos").with(ArchivoApiController.class, "crear");
        router.DELETE().route("/api/incidencias/{incidenciaId: [0-9]+}/archivos/{archivoId: [0-9]+}").with(ArchivoApiController.class, "eliminar");
        
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
        router.GET().route("/").with(InicioController.class, "index");
    }

}
