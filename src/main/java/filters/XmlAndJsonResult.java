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
package filters;

import ninja.Context;
import ninja.Filter;
import ninja.FilterChain;
import ninja.Result;

/**
 *
 * @author Herman
 * @since 22/07/2015
 */
public class XmlAndJsonResult implements Filter {

    @Override
    public Result filter(FilterChain chain, Context context) {
        Result result = chain.next(context);
        return result
                .supportedContentTypes(
                        Result.APPLICATION_JSON,
                        Result.APPLICATION_XML)
                .fallbackContentType(Result.APPLICATION_JSON);
    }
}
