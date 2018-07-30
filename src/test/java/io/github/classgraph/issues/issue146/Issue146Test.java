/*
 * This file is part of ClassGraph.
 *
 * Author: Luke Hutchison
 *
 * Hosted at: https://github.com/lukehutch/fast-classpath-scanner
 *
 * --
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Luke Hutchison
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.classgraph.issues.issue146;

import static org.assertj.core.api.StrictAssertions.assertThat;

import java.io.IOException;

import org.junit.Test;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.MethodInfo;

public class Issue146Test {
    @Test
    public void issue146Test() throws IOException {
        // Scans io.github.classgraph.issues.issue146.CompiledWithJDK8, which is in
        // src/test/resources
        final String pkg = Issue146Test.class.getPackage().getName();
        final ClassInfo classInfo = new ClassGraph().whitelistPackages(pkg) //
                .enableMethodInfo() //
                .scan() //
                .getClassInfo(pkg + "." + "CompiledWithJDK8");
        assertThat(classInfo).isNotNull();
        final MethodInfo methodInfo = classInfo //
                .getMethodInfo("method") //
                .get(0);
        assertThat(methodInfo.toString()) //
                .isEqualTo("public void method(int param0, java.lang.String param1, double[] param2)");
    }
}
