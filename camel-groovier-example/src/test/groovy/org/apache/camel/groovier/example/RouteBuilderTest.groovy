package org.apache.camel.groovier.example

import groovy.transform.CompileStatic
import org.apache.camel.EndpointInject
import org.apache.camel.RoutesBuilder
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.component.mock.MockEndpoint
import org.apache.camel.test.junit4.CamelTestSupport
import org.junit.Test

/**
 * @author sohrab
 */
class RouteBuilderTest extends CamelTestSupport {

    @EndpointInject(uri = 'mock:out')
    MockEndpoint mockEndpoint

    @Override
    @CompileStatic
    protected RoutesBuilder createRouteBuilder() throws Exception {
        new RouteBuilder() {
            @Override
            void configure() throws Exception {
                onException(RuntimeException) {
                    handled true
                    log 'Something has gone seriously wrong'
                }

                from ('direct:input') {
                    routeId 'myDslRoute'

                    choice {
                        when (body().isEqualTo(constant('Hi'))) {
                            log 'Hello There'
                        }
                        otherwise {
                            throwException RuntimeException, 'Oh no!'
                        }
                    }

                    to 'mock:out'
                }
            }
        }
    }

    @Test
    void 'test route'() {
        mockEndpoint.expectedMessageCount 1
        template.sendBody 'direct:input', 'Hi'
        assertMockEndpointsSatisfied()
    }

}


