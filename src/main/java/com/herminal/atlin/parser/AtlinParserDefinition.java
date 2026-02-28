package com.herminal.atlin.parser;

import com.herminal.atlin.AtlinFileType;
import com.herminal.atlin.AtlinLanguage;
import com.herminal.atlin.lexer.AtlinLexer;
import com.herminal.atlin.psi.AtlinElementTypes;
import com.herminal.atlin.psi.AtlinTokenTypes;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.lang.*;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.tree.*;
import org.jetbrains.annotations.NotNull;

public class AtlinParserDefinition implements ParserDefinition {

    @NotNull @Override
    public Lexer createLexer(Project project) { return new AtlinLexer(); }

    @NotNull @Override
    public PsiParser createParser(Project project) {
        return (root, builder) -> {
            Marker file = builder.mark();
            while (!builder.eof()) {
                Marker entry = builder.mark();
                builder.advanceLexer();
                entry.done(AtlinElementTypes.ENTRY);
            }
            file.done(root);
            return builder.getTreeBuilt();
        };
    }

    @NotNull @Override
    public IFileElementType getFileNodeType() {
        return new IFileElementType(AtlinLanguage.INSTANCE);
    }

    @NotNull @Override
    public TokenSet getCommentTokens() {
        return TokenSet.create(AtlinTokenTypes.COMMENT);
    }

    @NotNull @Override
    public TokenSet getStringLiteralElements() {
        return TokenSet.create(AtlinTokenTypes.VALUE);
    }

    @NotNull @Override
    public PsiElement createElement(ASTNode node) {
        return new ASTWrapperPsiElement(node);
    }

    @NotNull @Override
    public PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new PsiFileBase(viewProvider, AtlinLanguage.INSTANCE) {
            @NotNull @Override
            public FileType getFileType() {
                return AtlinFileType.INSTANCE;
            }
        };
    }
}
