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
package org.apache.camel.groovier.extend;

import groovy.lang.Closure;
import groovy.lang.DelegatesTo;
import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.groovier.dsl.OnExceptionSpec;
import org.apache.camel.groovier.dsl.RouteSpec;
import org.apache.camel.groovier.support.ClosureUtils;
import org.apache.camel.model.ProcessorDefinition;

/**
 * @author sohrab
 */
public final class CamelGroovierMethods {

    private CamelGroovierMethods() {
        // Utility Class
    }

    /**
     * Creates a new route from the given URI input
     *
     * @param self the Route builder
     * @param uri the from uri
     * @param closure the route definition
     * @return the builder
     */
    public static ProcessorDefinition<?> from(RouteBuilder self, String uri,
                                              @DelegatesTo(RouteSpec.class) Closure<?> closure) {
        RouteSpec route = new RouteSpec(self.from(uri), self);
        ClosureUtils.call(closure, route, self, self);
        return route.getDefinition();
    }

    /**
     * Creates a new route from the given endpoint
     *
     * @param self the Route builder
     * @param endpoint the from endpoint
     * @param closure the route definition
     * @return the builder
     */
    public static ProcessorDefinition<?> from(RouteBuilder self, Endpoint endpoint,
                                              @DelegatesTo(RouteSpec.class) Closure<?> closure) {
        RouteSpec route = new RouteSpec(self.from(endpoint), self);
        ClosureUtils.call(closure, route, self, self);
        return route.getDefinition();
    }

    /**
     * Creates a new route from the given URIs input
     *
     * @param self the Route builders
     * @param uris the from uri
     * @param closure the route definition
     * @return the builder
     */
    public static ProcessorDefinition<?> from(RouteBuilder self, String[] uris,
                                              @DelegatesTo(RouteSpec.class) Closure<?> closure) {
        RouteSpec route = new RouteSpec(self.from(uris), self);
        ClosureUtils.call(closure, route, self, self);
        return route.getDefinition();
    }

    /**
     * Creates a new route from the given endpoints
     *
     * @param self the Route builder
     * @param endpoints the from endpoints
     * @param closure the route definition
     * @return the builder
     */
    public static ProcessorDefinition<?> from(RouteBuilder self, Endpoint[] endpoints,
                                              @DelegatesTo(RouteSpec.class) Closure<?> closure) {
        RouteSpec route = new RouteSpec(self.from(endpoints), self);
        ClosureUtils.call(closure, route, self, self);
        return route.getDefinition();
    }

    public static ProcessorDefinition<?> onException(RouteBuilder self, Class<? extends Throwable> exceptionClass,
                                                     @DelegatesTo(OnExceptionSpec.class) Closure<?> closure) {
        OnExceptionSpec onException = new OnExceptionSpec(self.onException(exceptionClass), self);
        ClosureUtils.call(closure, onException, self, self);
        return onException.getDefinition();
    }

}
