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
 * Representa la frecuencia en la que ocurre la incidencia.
 *
 * @author Herman
 * @since 22/07/2015
 */
public enum Reproducibilidad {

    /**
     * Ocurre todo el tiempo, siguiendo los pasos indicados.
     */
    SIEMPRE,
    /**
     * Se tiene la certeza de que ocurre, pero los pasos para reproducirlo no
     * son claros.
     */
    ALEATORIO,
    /**
     * Ocurre bajo circunstancias desconocidas y no se tiene idea de como
     * reproducirlo.
     */
    DESCONOCIDO;
}
