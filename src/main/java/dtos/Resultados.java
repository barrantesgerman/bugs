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

import com.google.common.base.Optional;
import java.util.ArrayList;
import java.util.List;
import ninja.Result;
import ninja.Results;
import ninja.validation.FieldViolation;
import ninja.validation.Validation;

/**
 *
 * @author Herman
 * @since 23/07/2015
 */
public class Resultados {

    public static Result ok() {
        return Results
                .ok()
                .supportedContentTypes(
                        Result.APPLICATION_JSON,
                        Result.APPLICATION_XML)
                .fallbackContentType(Result.APPLICATION_JSON)
                .render(new ResultadoDTO(HttpCode.OK, true));
    }

    public static Result ok(Object object) {
        return Results
                .ok()
                .supportedContentTypes(
                        Result.APPLICATION_JSON,
                        Result.APPLICATION_XML)
                .fallbackContentType(Result.APPLICATION_JSON)
                .render(new ResultadoDTO(HttpCode.OK, object));
    }

    public static Result created(Object object) {
        return Results
                .created(Optional.<String>absent())
                .supportedContentTypes(
                        Result.APPLICATION_JSON,
                        Result.APPLICATION_XML)
                .fallbackContentType(Result.APPLICATION_JSON)
                .render(new ResultadoDTO(HttpCode.CREATED, object));
    }

    public static Result notFound() {
        return Results
                .notFound()
                .supportedContentTypes(
                        Result.APPLICATION_JSON,
                        Result.APPLICATION_XML)
                .fallbackContentType(Result.APPLICATION_JSON)
                .render(new ResultadoDTO(HttpCode.NOT_FOUND, false));
    }
    
    public static Result validation(Validation validation) {
        
        List<ErrorValidationDTO> errores = new ArrayList<>();
        for(FieldViolation violation : validation.getBeanViolations()){
            errores.add(
                    new ErrorValidationDTO(
                            violation.field,
                            violation.constraintViolation.getMessageKey()));
        }
        for(FieldViolation violation : validation.getFieldViolations()){
            errores.add(
                    new ErrorValidationDTO(
                            violation.field,
                            violation.constraintViolation.getMessageKey()));
        }
        
        return Results
                .badRequest()
                .supportedContentTypes(
                        Result.APPLICATION_JSON,
                        Result.APPLICATION_XML)
                .fallbackContentType(Result.APPLICATION_JSON)
                .render(new ResultadoDTO(HttpCode.BAD_REQUEST, errores));
    }

}
