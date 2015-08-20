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
package etc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import ninja.params.WithArgumentExtractor;

/**
 *
 * @author Herman Barrantes
 * @since 10/08/2015
 */
@WithArgumentExtractor(PaginaExtractor.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface Pagina {

    /**
     * Indica el número de la página. Por defecto es 1.
     *
     * @return número de la página.
     */
    int numero() default 1;

    /**
     * Indica el tamaño de la página. Por defecto es 10.
     *
     * @return tamaño de la página.
     */
    int tamano() default 10;
}
