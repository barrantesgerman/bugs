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

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Representa un usuario del sistema.
 *
 * @author Herman
 * @since 22/07/2015
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "usuario")
public class Usuario extends ModeloBase implements Serializable {

    /**
     * Nombre del usuario, para autenticación.
     */
    @Column(name = "usuario")
    private String usuario;
    /**
     * Clave del usuario.
     */
    @Column(name = "clave")
    private String clave;
    /**
     * Nombre real del usuario.
     */
    @Column(name = "nombre")
    private String nombre;
    /**
     * Correo del usuario.
     */
    @Column(name = "correo")
    private String correo;

}
