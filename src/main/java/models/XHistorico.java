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
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Representa el historial de cambios que sufre una incidencia en su ciclo de
 * vida.
 *
 * @author Herman
 * @since 22/07/2015
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "historico")
public class XHistorico extends ModeloBase implements Serializable {

    /**
     * Incidencia a la que esta relacionada el histórico.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incidencia_id")
    private Incidencia incidencia;
    /**
     * Usuario que realizó la acción.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    /**
     * Campo afectado.
     */
    @Column(name = "campo")
    private String campo;
    /**
     * Fecha en la que se realizó la acción.
     */
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    /**
     * Valor previo a realizar el cambio.
     */
    @Column(name = "antes")
    private String antes;
    /**
     * Valor posterior a la realización del cambio.
     */
    @Column(name = "ahora")
    private String ahora;
}
