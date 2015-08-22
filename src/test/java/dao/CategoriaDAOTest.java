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
import models.Categoria;
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
public class CategoriaDAOTest {

    @Inject
    private CategoriaDAO categoriaDAO;

    @Inject
    private ProyectoDAO proyectoDAO;

    private static long proyectoId;
    private static long categoriaId;

    @Test
    public void test0setup() {
        System.out.println("*** test0setup ***");

        Proyecto proyecto = new Proyecto();
        proyecto.setNombre("Proyecto Categoría");
        proyecto.setDescripcion("Descripción Proyecto Categoría");
        proyecto.setEstado(EstadoProyecto.EN_DESARROLLO);

        proyectoId = proyectoDAO.crear(proyecto).get().getId();
    }

    @Test
    public void test1Crear() {
        System.out.println("*** test1Crear ***");
        Categoria categoria = new Categoria();
        categoria.setDescripcion("Categoría Prueba 1");

        Optional<Categoria> creado = categoriaDAO.crear(proyectoId, categoria);

        assertTrue(creado.isPresent());
        
        assertNotEquals(creado.get().getId(), 0L);
        categoriaId = creado.get().getId();
        
        assertEquals(creado.get().getDescripcion(), "Categoría Prueba 1");
    }

    @Test
    public void test2Buscar() {
        System.out.println("*** test2Buscar ***");
        Optional<Categoria> buscado = categoriaDAO.buscar(proyectoId, categoriaId);

        assertTrue(buscado.isPresent());
        assertEquals(buscado.get().getId(), categoriaId);
        assertEquals(buscado.get().getDescripcion(), "Categoría Prueba 1");
    }

    @Test
    public void test3Editar() {
        System.out.println("*** test3Editar ***");
        Categoria buscado = categoriaDAO.buscar(proyectoId, categoriaId).get();
        buscado.setDescripcion("Categoría 1");

        boolean editado = categoriaDAO.editar(proyectoId, buscado.getId(), buscado);
        assertTrue(editado);

        buscado = categoriaDAO.buscar(proyectoId, categoriaId).get();
        assertEquals(buscado.getDescripcion(), "Categoría 1");
    }

    @Test
    public void test4Listar() {
        System.out.println("*** test4Listar ***");
        List<Categoria> buscados = categoriaDAO.listar(proyectoId);
        assertFalse(buscados.isEmpty());
        assertEquals(buscados.size(), 1);
    }

    @Test
    public void test5Eliminar() {
        System.out.println("*** test5Eliminar ***");
        boolean eliminado = categoriaDAO.eliminar(proyectoId, categoriaId);
        assertTrue(eliminado);
        Optional<Categoria> buscado = categoriaDAO.buscar(proyectoId, categoriaId);
        assertFalse(buscado.isPresent());
        List<Categoria> buscados = categoriaDAO.listar(proyectoId);
        assertTrue(buscados.isEmpty());
    }
}
