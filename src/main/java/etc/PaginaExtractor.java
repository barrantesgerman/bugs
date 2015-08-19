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

import com.google.common.base.Optional;
import dtos.PaginaDTO;
import ninja.Context;
import ninja.params.ArgumentExtractor;

/**
 *
 * @author Herman Barrantes
 * @since 10/08/2015
 */
public class PaginaExtractor implements ArgumentExtractor<PaginaDTO> {

    private final int numero;
    private final int tamano;

    public PaginaExtractor(Pagina pagina) {
        this.numero = pagina.numero();
        this.tamano = pagina.tamano();
    }

    @Override
    public PaginaDTO extract(Context context) {
        PaginaDTO pagina = new PaginaDTO();

        Optional<Integer> oNumero = Optional.fromNullable(
                context.getParameterAsInteger("numero"));
        Optional<Integer> oTamano = Optional.fromNullable(
                context.getParameterAsInteger("tamano"));
        pagina.setNumero(oNumero.or(numero));
        pagina.setTamano(oTamano.or(tamano));

        return pagina;
    }

    @Override
    public Class<PaginaDTO> getExtractedType() {
        return PaginaDTO.class;
    }

    @Override
    public String getFieldName() {
        return null;
    }

}
