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

import org.junit.jupiter.api.Test;

class ValidateTest {

    @Test
    void validateNotNull_ReturnsInput_WhenInputIsNotNull() {
        Object input = new Object();

        Object returnedInput = Validate.validateNotNull(input, "");

        assertEquals(input, returnedInput);
    }

    @Test
    void validateNotNull_Fails_WhenInputIsNull() {
        Throwable thrown =
                assertThrows(
                        NullPointerException.class,
                        () -> Validate.validateNotNull(null, "parameterName"));

        assertTrue(
                thrown.getMessage().contains("parameterName"),
                "thrown message should contain \"parameterName\"");
    }
}
