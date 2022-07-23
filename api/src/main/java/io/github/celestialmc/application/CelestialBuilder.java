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

import io.github.celestialmc.Builder;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Builder for {@link Celestial}.
 *
 * @since 1.0.0
 */
public interface CelestialBuilder extends CelestialConfiguration, Builder<Celestial> {

    /**
     * Sets the class loader.
     *
     * @param classLoader The class loader.
     * @throws NullPointerException if {@code classLoader} is null.
     * @return the builder instance (for chaining).
     * @since 1.0.0
     */
    @Contract("_ -> this")
    @NotNull
    CelestialBuilder classLoader(@NotNull ClassLoader classLoader);
}
