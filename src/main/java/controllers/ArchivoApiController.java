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
package controllers;

import com.google.common.base.Optional;
import dao.ArchivoDAO;
import dtos.ArchivoDTO;
import java.util.List;
import javax.inject.Inject;
import dtos.Resultados;
import java.io.InputStream;
import models.Archivo;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.params.PathParam;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Herman
 * @since 22/07/2015
 */
public class ArchivoApiController {

    @Inject
    private ArchivoDAO archivoDAO;

    public Result listar(@PathParam("incidenciaId") Long incidenciaId) {

        List<ArchivoDTO> archivos = archivoDAO.listar(incidenciaId);
        if (!archivos.isEmpty()) {
            return Resultados.ok(archivos);
        }
        return Resultados.notFound();
    }

    public Result buscar(
            @PathParam("incidenciaId") Long incidenciaId,
            @PathParam("archivoId") Long archivoId) {

        Optional<Archivo> oArchivo = archivoDAO.buscar(incidenciaId, archivoId);
        if (oArchivo.isPresent()) {
            Archivo archivo = oArchivo.get();
            return Results
                    .ok()
                    .doNotCacheContent()
                    .addHeader("Content-Disposition", "inline; filename=\"" + archivo.getNombre() + "\"")//attachment; o inline;
                    .addHeader("Content-Length", String.valueOf(archivo.getContenido().length))
                    .addHeader("Content-Transfer-Encoding", "binary")
                    .addHeader("Content-Description", "File Transfer")
                    .contentType(archivo.getMimeType())
                    .renderRaw(archivo.getContenido());
        }
        return Resultados.notFound();
    }

    public Result crear(
            @PathParam("incidenciaId") Long incidenciaId,
            Context context) throws Exception {

        if (context.isMultipart()) {

            FileItemIterator fileItemIterator = context.getFileItemIterator();

            while (fileItemIterator.hasNext()) {

                FileItemStream item = fileItemIterator.next();

                if (!item.isFormField()) {

                    String nombre = item.getName();
                    String mimeType = item.getContentType();
                    InputStream stream = item.openStream();

                    Archivo archivo = new Archivo();
                    archivo.setNombre(nombre);
                    archivo.setMimeType(mimeType);
                    archivo.setContenido(IOUtils.toByteArray(stream));

                    boolean resultado = archivoDAO.crear(incidenciaId, archivo);
                }
            }
            return Resultados.ok();
        } else {
            return Resultados.notFound();
        }
    }

    public Result eliminar(
            @PathParam("incidenciaId") Long incidenciaId,
            @PathParam("archivoId") Long archivoId) {

        boolean resultado = archivoDAO.eliminar(incidenciaId, archivoId);
        if (resultado) {
            return Resultados.ok();
        }
        return Resultados.notFound();
    }

}
