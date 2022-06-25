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
import static com.google.errorprone.matchers.Matchers.allOf;
import static com.google.errorprone.matchers.Matchers.anyOf;
import static com.google.errorprone.matchers.Matchers.hasAnnotation;
import static com.google.errorprone.matchers.Matchers.isVoidType;
import static com.google.errorprone.matchers.Matchers.methodReturns;
import static com.google.errorprone.matchers.Matchers.not;
import static com.google.errorprone.matchers.Matchers.singleStatementReturnMatcher;

import com.google.auto.service.AutoService;
import com.google.errorprone.BugPattern;
import com.google.errorprone.VisitorState;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CheckReturnValue;
import com.google.errorprone.bugpatterns.BugChecker;
import com.google.errorprone.matchers.Description;
import com.google.errorprone.matchers.Matcher;
import com.sun.source.tree.MethodTree;
import org.jetbrains.annotations.NotNull;

/**
 * Ensures that all methods except void or simple getter methods (only return statement) should be
 * annotated with {@code @com.google.errorprone.annotations.CanIgnoreReturnValue} or
 * {@code @com.google.errorprone.annotations.CheckReturnValue}.
 *
 * @author Sparky983
 * @since 1.0.0
 */
@AutoService(BugChecker.class)
@BugPattern(
        summary = "Ensures methods have @CanIgnoreReturnValue or @CheckReturnValue",
        severity = ERROR)
public class MissingCheckReturnValue extends BugChecker implements BugChecker.MethodTreeMatcher {

    /** Matches simple methods with a single return statement. */
    private final Matcher<MethodTree> SIMPLE_GETTER =
            allOf(not(isVoidType()), singleStatementReturnMatcher(allOf()));
    /** Matches methods that should not be annotated. */
    private final Matcher<MethodTree> SHOULD_SKIP_MATCHER =
            anyOf(
                    methodReturns(isVoidType()),
                    SIMPLE_GETTER,
                    hasAnnotation(CheckReturnValue.class),
                    hasAnnotation(CanIgnoreReturnValue.class));

    @Override
    public @NotNull Description matchMethod(@NotNull MethodTree tree, @NotNull VisitorState state) {

        if (SHOULD_SKIP_MATCHER.matches(tree, state)) {
            return Description.NO_MATCH;
        }

        return describeMatch(tree);
    }
}
