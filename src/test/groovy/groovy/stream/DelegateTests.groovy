/*
 * Copyright 2013-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package groovy.stream

class DelegateTests extends spock.lang.Specification {
    def "unfilteredIndex map test"() {
        setup:
            def index = 0
            def stream = Stream.from 1..4 tapWithIndex { obj, idx -> index = idx } map { it * index }
        when:
            def result = stream.collect()
        then:
            result == [ 0, 2, 6, 12 ]
    }

    def "unfilteredIndex filter test"() {
        setup:
            def index = 0
            def stream = Stream.from 1..4 tapWithIndex { obj, idx -> index = idx } filter { 2 != index }
        when:
            def result = stream.collect()
        then:
            result == [ 1, 2, 4 ]
    }

    def "unfilteredIndex until test"() {
        setup:
            def index = 0
            def stream = Stream.from 1..4 tapWithIndex { obj, idx -> index = idx } until { index == 2 }
        when:
            def result = stream.collect()
        then:
            result == [ 1, 2 ]
    }
}