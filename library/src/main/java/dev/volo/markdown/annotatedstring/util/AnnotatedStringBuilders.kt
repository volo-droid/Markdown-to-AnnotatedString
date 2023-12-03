/*
 * Copyright 2023 Volodymyr Galandzij
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.volo.markdown.annotatedstring.util

import androidx.compose.ui.text.AnnotatedString

inline fun buildAnnotatedStringFrom(text: CharSequence, builder: (AnnotatedString.Builder).() -> Unit) =
    annotatedStringBuilderFrom(text)
        .apply(builder)
        .toAnnotatedString()

fun annotatedStringBuilderFrom(text: CharSequence) =
    AnnotatedString.Builder(text.length).apply {
        append(text)
    }

fun String.toAnnotatedString() = AnnotatedString(this)
