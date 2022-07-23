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

import java.util.Optional;
import java.util.ServiceLoader;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * A utility class for working with the {@link java.util.ServiceLoader}.
 *
 * <p>
 *
 * <h2>Definitions</h2>
 *
 * service: An interface. <br>
 * service implementation: An implementation of a service that can be found in a jar's {@code
 * META-INF/services/{services-name}}, {service-name} being the fully qualified name of the service,
 * as specified in {@link ServiceLoader}.
 *
 * @since 1.0.0
 */
public final class Services {

    private Services() {
        throw new AssertionError();
    }

    /**
     * Finds the first service of type {@code service} from {@code
     * META-INF/services/{service-name}}, {service-name} being the name of {@code service}. If the
     * service was not found, an illegal state exception is thrown.
     *
     * @param service The service class.
     * @param classLoader The class loader to be used to load the service.
     * @return The service.
     * @param <T> The type of the service.
     * @throws IllegalStateException if the service was not found.
     * @see ServiceLoader#load(Class, ClassLoader)
     * @since 1.0.0
     */
    @Contract(pure = true)
    public static <T> @NotNull T findRequiredService(
            @NotNull Class<T> service, @NotNull ClassLoader classLoader) {
        return findService(service, classLoader)
                .orElseThrow(
                        () ->
                                new IllegalStateException(
                                        "Required service ["
                                                + service.getName()
                                                + "] was not found"));
    }

    /**
     * Finds the first service implementation of type {@code service} from {@code
     * META-INF/services/{service-name}}, {@code {service-name}} being the name of {@code service}.
     *
     * <p>An example of how to load a service with a default implementation.
     *
     * <pre>{@code
     * Service service = ServiceLoader.load(Service.class)
     *         .findFirst()
     *         .orElseGet(() -> new DefaultService())
     * }</pre>
     *
     * @param service The service class.
     * @param classLoader The class loader to be used to load the service.
     * @return An optional containing the service implementation or @{code Optional.empty()} if no
     *     implementation was found.
     * @param <T> The type of the service.
     * @see ServiceLoader#load(Class, ClassLoader)
     * @since 1.0.0
     */
    @Contract(pure = true)
    public static <T> @NotNull Optional<T> findService(
            @NotNull Class<T> service, @NotNull ClassLoader classLoader) {
        Validate.validateNotNull(service, "service");
        Validate.validateNotNull(classLoader, "classLoader");
        return ServiceLoader.load(service, classLoader).findFirst();
    }
}
