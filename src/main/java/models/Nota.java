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
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Representa una nota relacionada a una incidencia.
 *
 * @author Herman
 * @since 22/07/2015
 */
@Data
@EqualsAndHashCode(callSuper = true, of = {"incidenciaId"})
@ToString(callSuper = true, exclude = {"nota"})
@Entity
@Table(name = "nota")
public class Nota extends ModeloBase implements Serializable {

    /**
     * ID de la incidencia a la que esta relacionada la nota.
     */
    @JsonIgnore
    @Column(name = "incidencia_id")
    private long incidenciaId;
    /**
     * Usuario que creó la nota.
     */
    @Column(name = "usuario")
    private String usuario;
    /**
     * Fecha en que fue creada la nota.
     */
    @NotNull
    @Past
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    /**
     * Contenido de la nota.
     */
    @NotNull
    @Size(min = 1, max = 5000)
    @Column(name = "nota")
    private String nota;

}
