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

class MissingCheckReturnValueTest {

    CompilationTestHelper testHelper;

    @BeforeEach
    void setUp() {

        testHelper = CompilationTestHelper.newInstance(MissingCheckReturnValue.class, getClass());
    }

    @Test
    void skips_WhenSimpleGetter() {

        testHelper
                .addSourceLines(
                        "Test.java",
                        "class Test {",
                        "    private String test;",
                        "    public String getTest() { return test; }",
                        "    public int getTestLength() { return getTest().length(); }",
                        "}")
                .doTest();
    }

    @Test
    void skips_WhenVoidReturnType() {

        testHelper
                .addSourceLines(
                        "Test.java",
                        "class Test {",
                        "    public void noOp() {}",
                        "    public void complicatedMethod() {",
                        "        System.out.println(\"Very complicated method.\");",
                        "    }",
                        "}")
                .doTest();
    }

    @Test
    void skips_WhenCheckReturnValueAnnotationPresent() {

        testHelper
                .addSourceLines(
                        "LengthGetter.java",
                        "import com.google.errorprone.annotations.CanIgnoreReturnValue;",
                        "import com.google.errorprone.annotations.CheckReturnValue;",
                        "class LengthGetter {",
                        "    @CheckReturnValue",
                        "    int complicatedGetLength(String string) {",
                        "        int length = string.length();",
                        "        return length;",
                        "    }",
                        "    @CanIgnoreReturnValue",
                        "    int complicatedGetLengthWithSideEffect(String string) {",
                        "        // some side effect",
                        "        int length = string.length();",
                        "        return length;",
                        "    }",
                        "}")
                .doTest();
    }

    @Test
    void fails_WhenNoCheckReturnValue() {

        testHelper
                .addSourceLines(
                        "LengthGetter.java",
                        "class LengthGetter {",
                        "    // BUG: Diagnostic contains: MissingCheckReturnValue",
                        "    int complicatedGetLength(String string) {",
                        "        int length = string.length();",
                        "        return length;",
                        "    }",
                        "}")
                .doTest();
    }
}
