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

package io.github.celestialmc.application;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Factory for {@link Celestial}.
 *
 * @since 1.0.0
 */
public interface CelestialFactory {

    /**
     * Creates a new celestial with the specified configuration.
     *
     * <p>A clone of the configuration will be used to ensure the configuration is mutated while the
     * application is running.
     *
     * @param configuration The configuration.
     * @return The created celestial.
     * @throws NullPointerException if {@code configuration} is null.
     * @since 1.0.0
     */
    @Contract(value = "_ -> new", pure = true)
    @NotNull
    Celestial createCelestial(@NotNull CelestialConfiguration configuration);

    /**
     * Creates a new celestial with no custom configuration.
     *
     * @return The created celestial.
     * @since 1.0.0
     */
    @Contract(value = "-> new", pure = true)
    @NotNull
    Celestial createCelestial();
}
