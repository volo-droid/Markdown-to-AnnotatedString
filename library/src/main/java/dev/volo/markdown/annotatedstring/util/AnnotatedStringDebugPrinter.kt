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

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.unit.TextUnit

fun AnnotatedString.toDebugString() =
    "AnnotatedString{" +
        "\n\ttext: $text" +
        "\n\tlength: $length" +
        "\n\tspanStyles: ${spanStyles.toDebugString()}" +
        "\n\tparagraphStyles: ${paragraphStyles.toDebugString()}" +
        "\n\tannotations: ${getStringAnnotations(0, length)}" +
        "\n}"

fun SpanStyle.toDebugString() =
    StringBuilder()
        .append("SpanStyle { ")
        .appendNonDefault("color", color, Color.Unspecified)
        .appendNonDefault("brush", brush, null)
        .appendNonDefault("alpha", alpha, Float.NaN)
        .appendNonDefault("fontSize", fontSize, TextUnit.Unspecified)
        .appendNonDefault("fontWeight", fontWeight, null)
        .appendNonDefault("fontStyle", fontStyle, null)
        .appendNonDefault("fontSynthesis", fontSynthesis, null)
        .appendNonDefault("fontFamily", fontFamily, null)
        .appendNonDefault("fontFeatureSettings", fontFeatureSettings, null)
        .appendNonDefault("letterSpacing", letterSpacing, TextUnit.Unspecified)
        .appendNonDefault("baselineShift", baselineShift, null)
        .appendNonDefault("textGeometricTransform", textGeometricTransform, null)
        .appendNonDefault("localeList", localeList, null)
        .appendNonDefault("background", background, Color.Unspecified)
        .appendNonDefault("textDecoration", textDecoration, null)
        .appendNonDefault("shadow", shadow, null)
        .appendNonDefault("platformStyle", platformStyle, null)
        .appendNonDefault("drawStyle", drawStyle, null)
        .removeTrailingComma()
        .append(" }")
        .toString()

fun ParagraphStyle.toDebugString() =
    StringBuilder()
        .append("textAlign", textAlign, null)
        .append("textDirection", textDirection, null)
        .append("lineHeight", lineHeight, TextUnit.Unspecified)
        .append("textIndent", textIndent, null)
        .append("platformStyle", platformStyle, null)
        .append("lineHeightStyle", lineHeightStyle, null)
        .append("lineBreak", lineBreak, null)
        .append("hyphens", hyphens, null)
        .append("textMotion", textMotion, null)
        .removeTrailingComma()
        .append(" }")
        .toString()

private fun <T> StringBuilder.appendNonDefault(name: String, value: T, default: T): StringBuilder {
    if (value != default) {
        append("$name=$value, ")
    }
    return this
}

private fun StringBuilder.removeTrailingComma(): StringBuilder {
    val trailingComma = ", "
    if (this.endsWith(trailingComma)) {
        this.setLength(length - trailingComma.length)
    }
    return this
}

@JvmName("listOfSpanStylesRangeToDebugString")
private fun List<AnnotatedString.Range<SpanStyle>>.toDebugString() =
    joinToString(prefix = "[", postfix = "]", separator = "; ") { it.toDebugString() }

@JvmName("listOfParagraphStylesRangeToDebugString")
private fun List<AnnotatedString.Range<ParagraphStyle>>.toDebugString() =
    joinToString(prefix = "[", postfix = "]", separator = "; ") { it.toDebugString() }

@JvmName("rangeOfSpanStyleToDebugString")
private fun AnnotatedString.Range<SpanStyle>.toDebugString() =
    "Range: $start-$end, ${item.toDebugString()}" + (if (tag.isEmpty()) "" else ", tag: $tag")

@JvmName("rangeOfParagraphStyleToDebugString")
private fun AnnotatedString.Range<ParagraphStyle>.toDebugString() =
    "Range: $start-$end, ${item.toDebugString()}"
