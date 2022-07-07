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

package io.github.celestialmc;

import org.jetbrains.annotations.NotNull;

/**
 * A life cycle.
 *
 * <p>
 *
 * @since 1.0.0
 */
public interface LifeCycle extends AutoCloseable {

    /**
     * Represents the current state of a life cycle.
     *
     * @since 1.0.0
     */
    enum State {

        /**
         * The initial state, before the life cycle has begun.
         *
         * @since 1.0.0
         */
        INITIAL,

        /**
         * The state after the life cycle has started.
         *
         * @since 1.0.0
         */
        RUNNING,

        /**
         * The state after the life cycle is complete.
         *
         * @since 1.0.0
         */
        FINISHED
    }

    /**
     * Returns the current state of the life cycle.
     *
     * @since 1.0.0
     */
    @NotNull
    State getState();

    /**
     * Returns whether the life cycle is running.
     *
     * <p>A life cycle is only running if after it has started, and before it has ended.
     *
     * @since 1.0.0
     */
    boolean isRunning();

    /**
     * Returns whether the life cycle has finished.
     *
     * <p>The life cycle is finished once it has been stopped.
     *
     * @since 1.0.0
     */
    boolean hasFinished();

    /**
     * Starts the life cycle.
     *
     * <p>Life cycles may only be started/stopped 1 time. They may not be reloaded.
     *
     * @throws IllegalStateException if the life cycle has already started.
     * @since 1.0.0
     */
    void start();

    /**
     * Ends the life cycle.
     *
     * <p>Life cycles may only be started/stopped 1 time. They may not be reloaded.
     *
     * @throws IllegalStateException if the life cycle has already stopped.
     * @since 1.0.0
     */
    void stop();

    /** Calls {@link #stop() } if the life cycle is running. */
    @Override
    default void close() {
        if (isRunning()) {
            stop();
        }
    }
}
