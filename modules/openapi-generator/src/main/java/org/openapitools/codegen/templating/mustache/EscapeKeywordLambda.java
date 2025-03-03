/*
 * Copyright 2018 OpenAPI-Generator Contributors (https://openapi-generator.tech)
 * Copyright 2018 SmartBear Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openapitools.codegen.templating.mustache;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;

import java.io.IOException;
import java.io.Writer;
import java.util.function.UnaryOperator;

/**
 * Converts text in a fragment to escape_keyword.
 * <p>
 * Register:
 * <pre>
 * additionalProperties.put("escape_keyword", new EscapeKeywordLambda((val) -> this.escapeKeyword(val))));
 * </pre>
 * <p>
 * Use:
 * <pre>
 * {{#escape_keyword}}{{name}}{{/escape_keyword}}
 * </pre>
 */
public class EscapeKeywordLambda implements Mustache.Lambda {
    private UnaryOperator<String> callback;

    public EscapeKeywordLambda(final UnaryOperator<String> callback) {
        this.callback = callback;
    }

    @Override
    public void execute(Template.Fragment fragment, Writer writer) throws IOException {
        String text = fragment.execute();

        text = this.callback.apply(text);

        writer.write(text);
    }
}
