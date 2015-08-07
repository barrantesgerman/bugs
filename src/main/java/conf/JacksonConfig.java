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
package conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.inject.Inject;
import javax.inject.Singleton;
import ninja.lifecycle.Start;

/**
 *
 * @author Herman
 * @since 06/08/2015
 */
@Singleton
public class JacksonConfig {

    @Inject
    private ObjectMapper objectMapper;
    @Inject 
    private XmlMapper xmlMapper;

    @Start(order = 90)
    public void configureObjectMapper() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", new Locale("es", "CR"));
        objectMapper.setDateFormat(sdf);
        xmlMapper.setDateFormat(sdf);
    }

}
