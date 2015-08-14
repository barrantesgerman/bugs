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
import lombok.Value;

/**
 * Representa un error de validación según la especificación JSR 303.
 *
 * @author Herman Barrantes
 * @since 14/08/2015
 */
@Value
@JsonRootName(value = "error")
public class ErrorValidationDTO {

    /**
     * Campo con errores.
     */
    private final String campo;
    /**
     * Mensaje de error.
     */
    private final String mensaje;
}
