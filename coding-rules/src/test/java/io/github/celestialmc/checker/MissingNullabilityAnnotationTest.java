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

import com.google.errorprone.CompilationTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class MissingNullabilityAnnotationTest {

    CompilationTestHelper testHelper;

    @BeforeEach
    void setUp() {

        testHelper =
                CompilationTestHelper.newInstance(MissingNullabilityAnnotation.class, getClass());
    }

    @Test
    void skips_WhenNullabilityAnnotation() {

        testHelper
                .addSourceLines(
                        "Test.java",
                        "import org.jetbrains.annotations.Contract;",
                        "import org.jetbrains.annotations.NotNull;",
                        "import org.jetbrains.annotations.Nullable;",
                        "class Test {",
                        "    @Nullable Object foo() { return null; }",
                        "    @NotNull Object bar() { return new Object(); }",
                        "    @Contract(\"_ -> param1\") <T> T baz(T t) { return t; }",
                        "}")
                .doTest();
    }

    @Test
    void fails_WhenNoNullabilityAnnotation() {

        testHelper
                .addSourceLines(
                        "Test.java",
                        "class Test {",
                        "    // BUG: Diagnostic contains: MissingNullabilityAnnotation",
                        "    Object foo() { return null; }",
                        "    // BUG: Diagnostic contains: MissingNullabilityAnnotation",
                        "    Object bar() { return new Object(); }",
                        "    // BUG: Diagnostic contains: MissingNullabilityAnnotation",
                        "    <T> T baz(T t) { return t; }",
                        "}")
                .doTest();
    }
}
