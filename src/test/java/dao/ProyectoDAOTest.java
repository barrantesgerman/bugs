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
import java.util.List;
import javax.inject.Inject;
import models.EstadoProyecto;
import models.Proyecto;
import ninja.NinjaRunner;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Herman Barrantes
 * @since 19/08/2015
 */
@RunWith(NinjaRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProyectoDAOTest {

    @Inject
    private ProyectoDAO proyectoDAO;

    private static long proyectoId;

    @Test
    public void test1Crear() {
        System.out.println("*** test1Crear ***");
        Proyecto proyecto = new Proyecto();
        proyecto.setNombre("Proyecto Prueba 1");
        proyecto.setDescripcion("Descripción Proyecto Prueba 1");
        proyecto.setEstado(EstadoProyecto.EN_DESARROLLO);

        Optional<Proyecto> creado = proyectoDAO.crear(proyecto);

        assertTrue(creado.isPresent());

        assertNotEquals(creado.get().getId(), 0);
        proyectoId = creado.get().getId();

        assertEquals(creado.get().getNombre(), "Proyecto Prueba 1");
        assertEquals(creado.get().getDescripcion(), "Descripción Proyecto Prueba 1");
        assertEquals(creado.get().getEstado(), EstadoProyecto.EN_DESARROLLO);
    }

    @Test
    public void test2Buscar() {
        System.out.println("*** test2Buscar ***");
        Optional<Proyecto> buscado = proyectoDAO.buscar(proyectoId);

        assertTrue(buscado.isPresent());
        assertEquals(buscado.get().getId(), 1L);
        assertEquals(buscado.get().getNombre(), "Proyecto Prueba 1");
        assertEquals(buscado.get().getDescripcion(), "Descripción Proyecto Prueba 1");
        assertEquals(buscado.get().getEstado(), EstadoProyecto.EN_DESARROLLO);
    }

    @Test
    public void test3Editar() {
        System.out.println("*** test3Editar ***");
        Proyecto buscado = proyectoDAO.buscar(proyectoId).get();
        buscado.setNombre("Proyecto 1");
        buscado.setDescripcion("Descripción Proyecto 1");
        buscado.setEstado(EstadoProyecto.OBSOLETO);

        boolean editado = proyectoDAO.editar(proyectoId, buscado);
        assertTrue(editado);

        buscado = proyectoDAO.buscar(1L).get();
        assertEquals(buscado.getNombre(), "Proyecto 1");
        assertEquals(buscado.getDescripcion(), "Descripción Proyecto 1");
        assertEquals(buscado.getEstado(), EstadoProyecto.OBSOLETO);
    }

    @Test
    public void test4Listar() {
        System.out.println("*** test4Listar ***");

        Proyecto proyecto = new Proyecto();
        proyecto.setNombre("Proyecto Prueba 2");
        proyecto.setDescripcion("Descripción Proyecto Prueba 2");
        proyecto.setEstado(EstadoProyecto.EN_DESARROLLO);

        proyectoDAO.crear(proyecto);
        List<Proyecto> buscados = proyectoDAO.listar();
        assertFalse(buscados.isEmpty());
        assertEquals(buscados.size(), 2);
    }

    @Test
    public void test5Eliminar() {
        System.out.println("*** test5Eliminar ***");
        boolean eliminado = proyectoDAO.eliminar(proyectoId);
        assertTrue(eliminado);
        Optional<Proyecto> buscado = proyectoDAO.buscar(proyectoId);
        assertFalse(buscado.isPresent());
        List<Proyecto> buscados = proyectoDAO.listar();
        assertFalse(buscados.isEmpty());
        assertEquals(buscados.size(), 1);
    }
}
