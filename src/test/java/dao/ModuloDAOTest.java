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
import models.enums.EstadoProyecto;
import models.Modulo;
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
 * @since 20/08/2015
 */
@RunWith(NinjaRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ModuloDAOTest {

    @Inject
    private ModuloDAO moduloDAO;

    @Inject
    private ProyectoDAO proyectoDAO;

    private static long proyectoId;
    private static long moduloId;

    @Test
    public void test0setup() {
        System.out.println("*** test0setup ***");
        
        Proyecto proyecto = new Proyecto();
        proyecto.setNombre("Proyecto Módulo");
        proyecto.setDescripcion("Descripción Proyecto Módulo");
        proyecto.setEstado(EstadoProyecto.EN_DESARROLLO);

        proyectoId = proyectoDAO.crear(proyecto).get().getId();
    }

    @Test
    public void test1Crear() {
        System.out.println("*** test1Crear ***");
        Modulo modulo = new Modulo();
        modulo.setNombre("Módulo Prueba 1");

        Optional<Modulo> creado = moduloDAO.crear(proyectoId, modulo);

        assertTrue(creado.isPresent());
        
        assertNotEquals(creado.get().getId(), 0L);
        moduloId = creado.get().getId();
        
        assertEquals(creado.get().getNombre(), "Módulo Prueba 1");
    }

    @Test
    public void test2Buscar() {
        System.out.println("*** test2Buscar ***");
        Optional<Modulo> buscado = moduloDAO.buscar(proyectoId, moduloId);

        assertTrue(buscado.isPresent());
        assertEquals(buscado.get().getId(), moduloId);
        assertEquals(buscado.get().getNombre(), "Módulo Prueba 1");
    }

    @Test
    public void test3Editar() {
        System.out.println("*** test3Editar ***");
        Modulo buscado = moduloDAO.buscar(proyectoId, moduloId).get();
        buscado.setNombre("Módulo 1");

        boolean editado = moduloDAO.editar(proyectoId, buscado.getId(), buscado);
        assertTrue(editado);

        buscado = moduloDAO.buscar(proyectoId, moduloId).get();
        assertEquals(buscado.getNombre(), "Módulo 1");
    }

    @Test
    public void test4Listar() {
        System.out.println("*** test4Listar ***");
        List<Modulo> buscados = moduloDAO.listar(proyectoId);
        assertFalse(buscados.isEmpty());
        assertEquals(buscados.size(), 1);
    }

    @Test
    public void test5Eliminar() {
        System.out.println("*** test5Eliminar ***");
        boolean eliminado = moduloDAO.eliminar(proyectoId, moduloId);
        assertTrue(eliminado);
        Optional<Modulo> buscado = moduloDAO.buscar(proyectoId, moduloId);
        assertFalse(buscado.isPresent());
        List<Modulo> buscados = moduloDAO.listar(proyectoId);
        assertTrue(buscados.isEmpty());
    }
}
