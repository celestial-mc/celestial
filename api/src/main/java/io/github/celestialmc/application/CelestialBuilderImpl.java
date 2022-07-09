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

import java.util.ServiceLoader;
import org.jetbrains.annotations.NotNull;

import io.github.celestialmc.util.Validate;

/**
 * A {@link CelestialBuilder} implementation.
 *
 * <p>Constructs the {@link Celestial} using services from {@link CelestialFactory
 * CelestialFactories} found in
 * META-INF/services/io.github.celestialmc.application.CelestialFactory.
 *
 * @see Celestial#builder()
 */
final class CelestialBuilderImpl implements CelestialBuilder {

    private ClassLoader classLoader;

    @Override
    public @NotNull CelestialBuilder classLoader(@NotNull ClassLoader classLoader) {
        Validate.validateNotNull(classLoader, "classLoader");
        this.classLoader = classLoader;
        return this;
    }

    @Override
    public @NotNull ClassLoader getClassLoader() {
        if (classLoader != null) {
            return classLoader;
        }
        return Thread.currentThread().getContextClassLoader();
    }

    @Override
    public @NotNull Celestial build() {
        return ServiceLoader.load(CelestialFactory.class, getClassLoader())
                .findFirst()
                .orElseThrow(
                        () ->
                                new IllegalStateException(
                                        "No services found in META-INF/services/"
                                                + CelestialFactory.class.getName()))
                .createCelestial(this);
    }
}
