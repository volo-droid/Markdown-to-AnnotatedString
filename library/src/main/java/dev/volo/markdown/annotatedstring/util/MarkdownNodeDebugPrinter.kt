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

import org.intellij.markdown.MarkdownElementTypes
import org.intellij.markdown.MarkdownTokenTypes
import org.intellij.markdown.ast.ASTNode

fun ASTNode.toDebugString(indent: Int = 0, textReader: (start: Int, end: Int) -> String): String =
    StringBuilder().apply {
        append(" ".repeat(indent))
        append("$type $startOffset..$endOffset")
        val text = when (type) {
            MarkdownElementTypes.MARKDOWN_FILE, MarkdownTokenTypes.EOL -> ""
            else -> textReader(startOffset, endOffset)
        }
        if (text.isNotEmpty()) {
            append(", text: ")
            append(text)
        }
        children.forEach { child ->
            append('\n')
            append(child.toDebugString(indent + 2, textReader))
        }
    }.toString()
