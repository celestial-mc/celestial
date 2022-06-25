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

package io.github.celestialmc.checker;

import static com.google.errorprone.BugPattern.SeverityLevel.ERROR;
import static com.google.errorprone.matchers.Matchers.anyOf;
import static com.google.errorprone.matchers.Matchers.hasAnnotation;
import static com.google.errorprone.matchers.Matchers.methodHasParameters;
import static com.google.errorprone.matchers.Matchers.methodReturns;

import com.google.auto.service.AutoService;
import com.google.errorprone.BugPattern;
import com.google.errorprone.VisitorState;
import com.google.errorprone.bugpatterns.BugChecker;
import com.google.errorprone.matchers.Description;
import com.google.errorprone.matchers.Matcher;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.Tree;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Ensures that all methods and method parameters are annotated with
 * {@code @org.jetbrains.annotations.NotNull} or {@code @org.jetbrains.annotations.Nullable}, or
 * {@code @org.jetbrains.annotations.Contract}.
 *
 * <p>Use {@code @Contract} for when nullability is unknown to explain when it will and when it
 * won't be null.
 *
 * @author Sparky983
 * @since 1.0.0
 */
@AutoService(MissingNullabilityAnnotation.class)
@BugPattern(
        summary =
                "Ensures that all methods and method parameters are annotated with "
                        + "@org.jetbrains.annotations.NotNull or @org.jetbrains.annotations.Nullable, "
                        + "@org.jetbrains.annotations.Contract.",
        severity = ERROR)
public class MissingNullabilityAnnotation extends BugChecker
        implements BugChecker.MethodTreeMatcher {

    private static final Matcher<MethodTree> METHOD_AND_PARAMETERS_ANNOTATED =
            anyOf(
                    hasNullabilityAnnotation(),
                    methodReturns(hasNullabilityAnnotation()),
                    methodHasParameters(hasNullabilityAnnotation()));

    private static <T extends Tree> @NotNull Matcher<T> hasNullabilityAnnotation() {
        return anyOf(
                hasAnnotation(NotNull.class),
                hasAnnotation(Nullable.class),
                hasAnnotation(Contract.class));
    }

    @Override
    public @NotNull Description matchMethod(@NotNull MethodTree tree, @NotNull VisitorState state) {

        if (METHOD_AND_PARAMETERS_ANNOTATED.matches(tree, state)) {
            return Description.NO_MATCH;
        }

        return describeMatch(tree);
    }
}
