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
package dtos;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.querydsl.core.annotations.QueryProjection;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * Representa un Archivo liviano (sin el arreglo de bytes).
 *
 * @author Herman Barrantes
 * @since 06/08/2015
 */
@Value
@EqualsAndHashCode(of = {"id"})
@JsonRootName(value = "archivo")
public class ArchivoDTO {

    /**
     * ID del archivo.
     */
    private final long id;
    /**
     * Nombre del Usuario que subió el archivo.
     */
    private final String usuario;
    /**
     * Nombre del archivo.
     */
    private final String nombre;
    /**
     * MimeType del archivo.
     */
    private final String mimeType;
    /**
     * Fecha en que fue subido el archivo.
     */
    private final Date fecha;

    /**
     * Constructor de ArchivoDTO.
     *
     * @param id ID del archivo.
     * @param usuario Nombre del Usuario que subió el archivo.
     * @param nombre Nombre del archivo.
     * @param mimeType MimeType del archivo.
     * @param fecha Fecha en que fue subido el archivo.
     */
    @QueryProjection
    public ArchivoDTO(long id, String usuario, String nombre, String mimeType, Date fecha) {
        this.id = id;
        this.usuario = usuario;
        this.nombre = nombre;
        this.mimeType = mimeType;
        this.fecha = fecha;
    }

}
