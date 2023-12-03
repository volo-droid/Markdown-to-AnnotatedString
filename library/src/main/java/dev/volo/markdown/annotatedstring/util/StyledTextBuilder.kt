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
@file:Suppress("TestFunctionName")

package dev.volo.markdown.annotatedstring.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit

class StyledTextBuilder(private val builder: AnnotatedString.Builder, private val range: IntRange) {

    constructor(text: CharSequence, range: IntRange) : this(annotatedStringBuilderFrom(text), range)

    fun with(spanStyle: SpanStyle) = with(builder) {
        spanStyle.applyTo(this, range)
        toAnnotatedString()
    }

    fun with(color: Color, background: Color = Color.Unspecified) =
        with(SpanStyle(color = color, background = background))

    fun with(fontFamily: FontFamily) = with(SpanStyle(fontFamily = fontFamily))

    fun with(fontSize: TextUnit) = with(SpanStyle(fontSize = fontSize))

    fun with(fontStyle: FontStyle) = with(SpanStyle(fontStyle = fontStyle))

    fun with(fontWeight: FontWeight) = with(SpanStyle(fontWeight = fontWeight))

    fun with(textDecoration: TextDecoration) = with(SpanStyle(textDecoration = textDecoration))
}

private fun SpanStyle.applyTo(builder: AnnotatedString.Builder, range: IntRange) =
    builder.addStyle(this, range.first, range.last + 1)

fun CharSequence.styleSubstring(substring: String): StyledTextBuilder =
    when (val start = indexOf(substring)) {
        -1 -> StyledTextBuilder(this, IntRange.EMPTY)
        else -> StyledTextBuilder(this, start..<start + substring.length)
    }

fun CharSequence.styleRange(range: IntRange) = StyledTextBuilder(this, range)

fun CharSequence.styleRange(start: Int, end: Int) = StyledTextBuilder(this, start..<end)
