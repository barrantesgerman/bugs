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
 * Representa la resolución de la incidencia.
 *
 * @author Herman
 * @since 22/07/2015
 */
public enum Resolucion {

    /**
     * No ha sido antendida o el desarrollador no fue capas de Resolver la
     * incidencia, la deja abierta para que sea reasignada.
     */
    ABIERTA,
    /**
     * Se resolvió satisfactoriamente la incidencia.
     */
    CORREGIDA,
    /**
     * Se resolvió parcialmente la incidencia, no se logró corregir de la forma
     * deseada o no se resolvió en su totalidad, pero no se dedicaran mas
     * esfuerzos a la solución de la misma.
     */
    CORREGIDA_PARCIALMENTE,
    /**
     * No se resolvió satisfactoriamente la incidencia.
     */
    REABIERTA,
    /**
     * Luego de intentarlo varias veces no se logró reproducir el error, por lo
     * que no se va a atender.
     */
    NO_REPRODUCIBLE,
    /**
     * Por cuestiones técnicas la incidencia no se puede corregir total o
     * parcialmente (workaround).
     */
    NO_ES_CORREGIBLE,
    /**
     * La incidencia ya fue reportada con anterioridad y se encuentra repetida.
     */
    DUPLICADA,
    /**
     * La funcionalidad cumple con lo establecido en los Casos de Uso, no se
     * trata de un error, puede que se trate de una mejora.
     */
    NO_ES_UNA_INCIDENCIA,
    /**
     * La administración indicó que por el momento no se va a atender hasta que
     * se resuelva algo Administrativo primero.
     */
    SUSPENDIDA,
    /**
     * La administración indicó que ese caso no se va a atender por decisión
     * Administrativa.
     */
    NO_SE_ARREGLARA;

}
