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
 * Representa cada uno de los estados en los que se puede encontrar una
 * incidencia.
 *
 * @author Herman
 * @since 22/07/2015
 */
public enum EstadoIncidencia {

    /**
     * Este es el punto de partida de un nuevo error hasta que le es asignado
     * otro estado. Desde nueva, la incidencia pasará a ser "asignada",
     * "aceptada", "confirmada" o "resuelta" o bien requerirá de más datos para
     * poder iniciar el proceso de resolución y cierre.
     */
    NUEVA,
    /**
     * Como indica su propia descripción, en este estado se señala la
     * imposibilidad de proceder a su resolución si no se aportan más datos
     * sobre la nueva incidencia. Cuando se le asigna este estado, la aplicación
     * abre una ficha para "Solicitar más información sobre la incidencia" en la
     * que debe elegir un usuario del desplegable al que asignarle la tarea de
     * completar esta información.
     */
    SE_NECESITAN_MAS_DATOS,
    /**
     * Este estado es utilizado por el debido responsable o, en su caso, por el
     * equipo de desarrollo para reflejar su acuerdo con la propuesta de
     * solicitud. El siguiente estado correspondería a "asignada" o
     * "confirmada".
     */
    ACEPTADA,
    /**
     * Este estado es generalmente utilizado tras confirmar los detalles que se
     * sugieren por parte del informador sobre la incidencia. El siguiente
     * estado es "asignada".
     */
    CONFIRMADA,
    /**
     * Este estado se utiliza para reflejar que la incidencia ha sido asignada a
     * uno de los miembros del equipo y que tal miembro del equipo está
     * trabajando activamente en la resolución del fallo. El siguiente estado
     * corresponde a "resuelta".
     */
    ASIGNADA,
    /**
     * Este estado se utiliza para reflejar que la incidencia es siendo atendida
     * por uno de los miembros del equipo.
     */
    ATENDIENDO,
    /**
     * Refleja que el problema se ha resuelto. Un problema puede ser resuelto
     * con una de las muchas resoluciones posibles que aparecen en una lista
     * desplegable (esto es personalizable). Los tipos de resoluciones posibles
     * por defecto cubren múltiples opciones: abierta, corregida, reabierta, no
     * reproducible, no es corregible, duplicada, no es una incidencia,
     * suspendida o no se arreglará. El siguiente estado que correspondería,
     * tras resolver una incidencia, llevaría a "cerrada" o en caso de que la
     * cuestión se vuelva a abrir, entonces sería "comentarios".
     */
    RESUELTA,
    /**
     * Este estado se utiliza para reflejar que la incidencia es siendo revisada
     * por uno de los miembros del equipo.
     */
    REVISANDO,
    /**
     * Esta situación refleja que la incidencia está completamente cerrada y no
     * se requieren nuevas medidas al respecto.
     */
    CERRADA;
}
