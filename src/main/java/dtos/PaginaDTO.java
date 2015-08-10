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
import com.google.common.base.Optional;
import lombok.Data;

/**
 *
 * @author Herman
 * @since 24/07/2015
 */
@Data
@JsonRootName(value = "pagina")
public class PaginaDTO {

    private Optional<Long> numero;
    private Optional<Long> tamano;
//    private Optional<Integer> total;
//    private boolean primera;
//    private boolean ultima;
//    private List<OrdenDTO> orden;

}
