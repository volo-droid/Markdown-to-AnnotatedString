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
package dev.volo.markdown.annotatedstring

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import org.intellij.markdown.MarkdownElementTypes
import org.intellij.markdown.MarkdownTokenTypes
import org.intellij.markdown.ast.ASTNode
import org.intellij.markdown.ast.getTextInNode

class AnnotatedStringGenerator(
    private val markdownText: String,
    private val markdownTree: ASTNode,
) {
    fun generateAnnotatedString(): AnnotatedString =
        AnnotatedString.Builder(capacity = markdownText.length).appendMarkdown(markdownTree).toAnnotatedString()

    private fun AnnotatedString.Builder.appendMarkdown(node: ASTNode): AnnotatedString.Builder {
        node.children.forEach { child ->
            when (child.type) {

                MarkdownElementTypes.PARAGRAPH ->
                    appendMarkdown(child)

                MarkdownElementTypes.EMPH -> withStyle(SpanStyle(fontStyle = FontStyle.Italic)) {
                    appendMarkdown(child)
                }

                MarkdownElementTypes.STRONG -> withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                    appendMarkdown(child)
                }

                MarkdownElementTypes.CODE_SPAN -> withStyle(SpanStyle(fontFamily = FontFamily.Monospace)) {
                    appendMarkdown(child)
                }

                MarkdownTokenTypes.TEXT -> append(child.getTextInNode(markdownText).toString())

                MarkdownTokenTypes.BACKTICK -> append('`')
                MarkdownTokenTypes.COLON -> append(':')
                MarkdownTokenTypes.DOUBLE_QUOTE -> append('"')
                MarkdownTokenTypes.EOL -> append('\n')
                MarkdownTokenTypes.ESCAPED_BACKTICKS -> append('`')
                MarkdownTokenTypes.EXCLAMATION_MARK -> append('!')
                MarkdownTokenTypes.GT -> append('>')
                MarkdownTokenTypes.HARD_LINE_BREAK -> append("\n\n")
                MarkdownTokenTypes.LBRACKET -> append('[')
                MarkdownTokenTypes.LPAREN -> append('(')
                MarkdownTokenTypes.LT -> append('<')
                MarkdownTokenTypes.RBRACKET -> append(']')
                MarkdownTokenTypes.RPAREN -> append(')')
                MarkdownTokenTypes.SINGLE_QUOTE -> append('\'')
                MarkdownTokenTypes.WHITE_SPACE -> repeat(child.charCount) { append(' ') }
            }
        }
        return this
    }

    private val ASTNode.charCount
        get() = endOffset - startOffset
}
