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
import javax.inject.Inject;
import models.Categoria;
import models.EstadoIncidencia;
import models.EstadoProyecto;
import models.Incidencia;
import models.Modulo;
import models.Prioridad;
import models.Proyecto;
import models.Reproducibilidad;
import models.Resolucion;
import ninja.NinjaRunner;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Herman Barrantes
 * @since 20/08/2015
 */
@RunWith(NinjaRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IncidenciaDAOTest {

    @Inject
    private IncidenciaDAO incidenciaDAO;

    @Inject
    private ProyectoDAO proyectoDAO;

    @Inject
    private ModuloDAO moduloDAO;

    @Inject
    private CategoriaDAO categoriaDAO;

    private static long proyectoId;
    private static long moduloId;
    private static long categoriaId;

    @Test
    public void test0setup() {
        System.out.println("*** test0setup ***");

        Proyecto proyecto = new Proyecto();
        proyecto.setNombre("Proyecto Incidencia");
        proyecto.setDescripcion("Descripción Proyecto Incidencia");
        proyecto.setEstado(EstadoProyecto.EN_DESARROLLO);

        proyectoId = proyectoDAO.crear(proyecto).get().getId();

        Modulo modulo = new Modulo();
        modulo.setNombre("Módulo Incidencia");

        moduloId = moduloDAO.crear(proyectoId, modulo).get().getId();

        Categoria categoria = new Categoria();
        categoria.setDescripcion("Categoría Incidencia");

        categoriaId = categoriaDAO.crear(proyectoId, categoria).get().getId();
    }

    @Test
    public void test1Crear() {
        System.out.println("*** test1Crear ***");
        Incidencia incidencia = new Incidencia();
        incidencia.setProyectoId(proyectoId);
        incidencia.setModuloId(moduloId);
        incidencia.setCategoriaId(categoriaId);
        incidencia.setEstado(EstadoIncidencia.NUEVA);
        incidencia.setPrioridad(Prioridad.NORMAL);
        incidencia.setReproducibilidad(Reproducibilidad.SIEMPRE);
        incidencia.setResolucion(Resolucion.ABIERTA);
        incidencia.setResumen("Incidencia Prueba 1");
        incidencia.setDescripcion("Descripción Incidencia Prueba 1");

        Optional<Incidencia> creado = incidenciaDAO.crear("usuario.prueba1", incidencia);

        assertTrue(creado.isPresent());
        assertEquals(creado.get().getId(), 1L);
        assertEquals(creado.get().getProyectoId(), proyectoId);
        assertEquals(creado.get().getModuloId(), moduloId);
        assertEquals(creado.get().getCategoriaId(), categoriaId);
        assertEquals(creado.get().getEstado(), EstadoIncidencia.NUEVA);
        assertEquals(creado.get().getPrioridad(), Prioridad.NORMAL);
        assertEquals(creado.get().getReproducibilidad(), Reproducibilidad.SIEMPRE);
        assertEquals(creado.get().getResolucion(), Resolucion.ABIERTA);
        assertEquals(creado.get().getResumen(), "Incidencia Prueba 1");
        assertEquals(creado.get().getDescripcion(), "Descripción Incidencia Prueba 1");
        assertEquals(creado.get().getUsuarioCreacion(), "usuario.prueba1");
        assertEquals(creado.get().getUsuarioActualizacion(), "usuario.prueba1");
    }

    @Test
    public void test2Buscar() {
        System.out.println("*** test2Buscar ***");
        Optional<Incidencia> buscado = incidenciaDAO.buscar(1L);

        assertTrue(buscado.isPresent());
        assertEquals(buscado.get().getId(), 1L);
        assertEquals(buscado.get().getProyectoId(), proyectoId);
        assertEquals(buscado.get().getModuloId(), moduloId);
        assertEquals(buscado.get().getCategoriaId(), categoriaId);
        assertEquals(buscado.get().getEstado(), EstadoIncidencia.NUEVA);
        assertEquals(buscado.get().getPrioridad(), Prioridad.NORMAL);
        assertEquals(buscado.get().getReproducibilidad(), Reproducibilidad.SIEMPRE);
        assertEquals(buscado.get().getResolucion(), Resolucion.ABIERTA);
        assertEquals(buscado.get().getResumen(), "Incidencia Prueba 1");
        assertEquals(buscado.get().getDescripcion(), "Descripción Incidencia Prueba 1");
        assertEquals(buscado.get().getUsuarioCreacion(), "usuario.prueba1");
        assertEquals(buscado.get().getUsuarioActualizacion(), "usuario.prueba1");
    }

    @Test
    public void test3Editar() {
        System.out.println("*** test3Editar ***");
        Incidencia buscado = incidenciaDAO.buscar(1L).get();
        buscado.setEstado(EstadoIncidencia.ACEPTADA);
        buscado.setPrioridad(Prioridad.ALTA);
        buscado.setReproducibilidad(Reproducibilidad.ALEATORIO);
        buscado.setResolucion(Resolucion.NO_REPRODUCIBLE);
        buscado.setResumen("Incidencia 1");
        buscado.setDescripcion("Descripción Incidencia 1");

        boolean editado = incidenciaDAO.editar(buscado.getId(), "usuario.prueba2", buscado);
        assertTrue(editado);

        buscado = incidenciaDAO.buscar(1L).get();
        assertEquals(buscado.getEstado(), EstadoIncidencia.ACEPTADA);
        assertEquals(buscado.getPrioridad(), Prioridad.ALTA);
        assertEquals(buscado.getReproducibilidad(), Reproducibilidad.ALEATORIO);
        assertEquals(buscado.getResolucion(), Resolucion.NO_REPRODUCIBLE);
        assertEquals(buscado.getResumen(), "Incidencia 1");
        assertEquals(buscado.getDescripcion(), "Descripción Incidencia 1");
        assertEquals(buscado.getUsuarioActualizacion(), "usuario.prueba2");
    }

    @Test
    public void test4Eliminar() {
        System.out.println("*** test4Eliminar ***");
        boolean eliminado = incidenciaDAO.eliminar(1L);
        assertTrue(eliminado);
        Optional<Incidencia> buscado = incidenciaDAO.buscar(1L);
        assertFalse(buscado.isPresent());
    }
}
