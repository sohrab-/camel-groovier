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

import groovy.lang.Closure;
import groovy.lang.DelegatesTo;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.groovier.support.ClosureUtils;
import org.apache.camel.model.ChoiceDefinition;
import org.apache.camel.model.ProcessorDefinition;

/**
 * @author sohrab
 */
public class ChoiceSpec extends ProcessorSpec<ChoiceDefinition> {

    public ChoiceSpec(ChoiceDefinition definition, RouteBuilder routeBuilder) {
        super(definition, routeBuilder);
    }

    public void when(Predicate predicate, @DelegatesTo(ProcessorSpec.class)Closure<?> closure) {
        ProcessorSpec<ChoiceDefinition> choice = new ProcessorSpec<>(definition.when(predicate), routeBuilder);
        ClosureUtils.call(closure, choice, routeBuilder, routeBuilder);
        definition = choice.getDefinition().endChoice();
    }

    public void otherwise(@DelegatesTo(RouteSpec.class)Closure<?> closure) {
        ProcessorSpec<ChoiceDefinition> choice = new ProcessorSpec<>(definition.otherwise(), routeBuilder);
        ClosureUtils.call(closure, choice, routeBuilder, routeBuilder);
        definition = choice.getDefinition().endChoice();
    }

}
