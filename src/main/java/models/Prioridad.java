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

/**
 * Representa la prioridad en la que debe ser atendida una incidencia.
 *
 * @author Herman
 * @since 22/07/2015
 */
public enum Prioridad {

    /**
     * Se debe atender en algún momento, pero no representa ningún riesgo o
     * problema para el usuario.
     */
    NINGUNA,
    /**
     * Se debe atender en algún momento, pero el riesgo o problema para el
     * usuario es mínimo.
     */
    BAJA,
    /**
     * Se debe atender en algún momento, pero el riesgo o problema para el
     * usuario es moderado.
     */
    NORMAL,
    /**
     * Se debe atender pronto, ya que el riesgo o problema para el usuario es
     * alto.
     */
    ALTA,
    /**
     * Se debe atender urgentemente, ya que consiste en un error grave que
     * bloque el flujo de trabajo para el usuario.
     */
    URGENTE,
    /**
     * Se debe atender inmediatamente, ya que consiste en un error fatal que
     * hace caer el sistema o procesos del mismo.
     */
    INMEDIATA;
}
