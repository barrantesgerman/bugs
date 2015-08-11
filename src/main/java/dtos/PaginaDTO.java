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
import conf.Constantes;
import lombok.Data;

/**
 *
 * @author Herman
 * @since 24/07/2015
 */
@Data
@JsonRootName(value = "pagina")
public class PaginaDTO {

    /**
     * Indica el número de la página actual.
     */
    private Long numero;
    /**
     * Indica el tamaño de la página.
     */
    private Long tamano;
    /**
     * Indica la cantidad de elementos de página actual.
     */
    private Long cantidad;
    /**
     * Indica la cantidad total de páginas.
     */
    private Long total;

    /**
     * Indica si es la primera página.
     *
     * @return true si es la primera página.
     */
    public boolean isPrimera() {
        return numero == 0;
    }

    /**
     * Indica si es la última página.
     *
     * @return true si es la última página.
     */
    public boolean isUltima() {
        return numero == total;
    }
//    private List<OrdenDTO> orden;

}
