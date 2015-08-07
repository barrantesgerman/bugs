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
import dtos.FiltroDTO;
import ninja.Context;
import ninja.params.ArgumentExtractor;

/**
 *
 * @author Herman Barrantes
 * @since 07/08/2015
 */
public class FiltroParamExtractor implements ArgumentExtractor<FiltroDTO> {

    @Override
    public FiltroDTO extract(Context context) {
        FiltroDTO fdto = new FiltroDTO();
        fdto.setProyectoId(
                Optional.fromNullable(
                        context.getParameterAsInteger("proyectoId")));
        fdto.setModuloId(
                Optional.fromNullable(
                        context.getParameterAsInteger("moduloId")));
        fdto.setCategoriaId(
                Optional.fromNullable(
                        context.getParameterAsInteger("categoriaId")));
        fdto.setResumen(
                Optional.fromNullable(
                        context.getParameter("resumen")));
        fdto.setTamano(
                Optional.fromNullable(
                        context.getParameterAsInteger("tamano")));
        fdto.setPagina(
                Optional.fromNullable(
                        context.getParameterAsInteger("pagina")));
        fdto.setTotal(
                Optional.fromNullable(
                        context.getParameterAsInteger("total")));
        return fdto;
    }

    @Override
    public Class<FiltroDTO> getExtractedType() {
        return FiltroDTO.class;
    }

    @Override
    public String getFieldName() {
        return null;
    }

}
