/*
 * Copyright 2022 Sparky
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

package io.github.celestialmc.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import org.junit.jupiter.api.Test;

class ServicesTest {

    ClassLoader classLoader = getClass().getClassLoader();

    @Test
    void findService_ReturnsEmptyOptional_WhenServiceNotFound() {
        Optional<?> service = Services.findService(NotImplementedService.class, classLoader);

        assertTrue(service.isEmpty());
    }

    @Test
    void findService_ReturnsServiceImplementation_WhenServiceFound() {
        Optional<TestService> service = Services.findService(TestService.class, classLoader);

        assertEquals("TestServiceImpl", service.orElseThrow().getName());
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    void findRequiredService_Fails_WhenServiceNotFound() {
        assertThrows(
                IllegalStateException.class,
                () -> Services.findRequiredService(NotImplementedService.class, classLoader));
    }

    @Test
    void findRequiredService_ReturnsServiceImplementation_WhenServiceFound() {
        TestService service = Services.findRequiredService(TestService.class, classLoader);

        assertEquals("TestServiceImpl", service.getName());
    }
}
