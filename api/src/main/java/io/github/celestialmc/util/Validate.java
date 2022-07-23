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

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A utility class for validating arguments with messages that follow Celestial conventions.
 *
 * <p>Also known as preconditions.
 *
 * @since 1.0.0
 */
public final class Validate {

    private Validate() {
        throw new AssertionError();
    }

    /**
     * Validates that the input is not {@code null}. If it is {@code null} an exception is thrown,
     * if not the input is returned.
     *
     * @param input The input to validate.
     * @param parameterName The name of the parameter that is being validated. This is used to
     *     construct an error message.
     * @return The input.
     * @param <T> The input type.
     * @throws NullPointerException if {@code input} is {@code null}.
     * @since 1.0.0
     */
    @Contract(value = "null, _ -> fail; !null, _ -> param1", pure = true)
    public static <T> @NotNull T validateNotNull(
            @Nullable T input, @NonNls @Nullable String parameterName) {
        if (input == null) {
            throw new NullPointerException("[" + parameterName + "] cannot be null");
        }
        return input;
    }
}
