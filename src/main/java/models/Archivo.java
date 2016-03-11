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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Representa un archivo que forma parte de la evidencia de una incidencia.
 *
 * @author Herman
 * @since 22/07/2015
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "archivo")
public class Archivo extends ModeloBase implements Serializable {

    /**
     * ID de la incidencia a la que esta relacionada el archivo.
     */
    @JsonIgnore
    @Column(name = "incidencia_id")
    private long incidenciaId;
    /**
     * Usuario que subió el archivo.
     */
    @Column(name = "usuario_id")
    private long usuarioId;
    /**
     * Nombre del archivo.
     */
    @Column(name = "nombre")
    private String nombre;
    /**
     * MimeType del archivo.
     */
    @Column(name = "mimeType")
    private String mimeType;
    /**
     * Fecha en que fue subido el archivo.
     */
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    /**
     * Contenido en bytes del archivo.
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "contenido")
    private byte[] contenido;

}
