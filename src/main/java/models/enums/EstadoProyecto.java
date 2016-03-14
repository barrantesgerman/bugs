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
package models.enums;

/**
 * Representa cada uno de los estados en los que se puede encontrar un proyecto.
 *
 * @author Herman
 * @since 22/07/2015
 */
public enum EstadoProyecto {

    /**
     * El proyecto se encuentra en estado de desarrollo.
     */
    EN_DESARROLLO,
    /**
     * El proyecto se a entregado al usuario final y solo se le esta dando
     * mantenimiento al mismo.
     */
    ENTREGADO,
    /**
     * El proyecto ya terminó su ciclo de vida, y esta guardado para efectos
     * históricos.
     */
    OBSOLETO;
}
