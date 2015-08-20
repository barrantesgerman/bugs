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
package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Corresponde a la categoría en la que se clasifica una incidencia. Cada
 * proyecto cuenta con un catálogo de categorías.
 *
 * @author Herman
 * @since 22/07/2015
 */
@Data
@EqualsAndHashCode(callSuper = true, of = {"proyectoId"})
@ToString(callSuper = true)
@Entity
@Table(name = "categoria")
public class Categoria extends ModeloBase implements Serializable {

    /**
     * ID del proyecto a la que esta relacionada la categoría.
     */
    @JsonIgnore
    @Column(name = "proyecto_id")
    private long proyectoId;
    /**
     * Descripción de la categoría.
     */
    @Column(name = "descripcion")
    private String descripcion;

}
