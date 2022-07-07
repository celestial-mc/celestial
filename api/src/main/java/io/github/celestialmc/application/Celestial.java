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

import io.github.celestialmc.LifeCycle;

/**
 * The application interface.
 *
 * @since 1.0.0
 */
public interface Celestial extends DelegatingCelestialConfiguration, LifeCycle {

    /**
     * Returns a new celestial builder.
     *
     * @since 1.0.0
     */
    @Contract(value = "-> new", pure = true)
    static CelestialBuilder builder() {
        return new CelestialBuilderImpl();
    }

    /**
     * Returns a new celestial builder using the specified class loader.
     *
     * @param classLoader The class loader.
     * @since 1.0.0
     */
    @Contract(value = "_ -> new", pure = true)
    static CelestialBuilder builder(@NotNull ClassLoader classLoader) {
        return builder().classLoader(classLoader);
    }

    /**
     * Returns the application's configuration.
     *
     * @since 1.0.0
     */
    @Override
    @NotNull
    CelestialConfiguration getConfiguration();
}
