/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.groovier.dsl;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.OptionalIdentifiedDefinition;

/**
 * @author sohrab
 */
public class OptionalIdentifiedSpec<T extends OptionalIdentifiedDefinition<T>> {

    protected RouteBuilder routeBuilder;
    protected T definition;

    public OptionalIdentifiedSpec(T definition, RouteBuilder routeBuilder) {
        this.definition = definition;
        this.routeBuilder = routeBuilder;
    }

    public T getDefinition() {
        return definition;
    }

    public void id(String id) {
        definition = definition.id(id);
    }

    public void description(String text) {
        definition = definition.description(text);
    }

    public void description(String id, String text, String lang) {
        definition = definition.description(id, text, lang);
    }
}
