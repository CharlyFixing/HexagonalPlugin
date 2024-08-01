package hexagonal;

import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;

public class DependencyInspector extends LocalInspectionTool {

    @NotNull
    @Override
    public PsiElementVisitor buildVisitor(@NotNull final ProblemsHolder holder, boolean isOnTheFly) {

        return new JavaElementVisitor() {
            @Override
            public void visitImportStatement(@NotNull PsiImportStatement statement) {

                super.visitImportStatement(statement);
                checkImport(statement, holder);
            }

            private void checkImport(PsiImportStatement statement, ProblemsHolder holder) {


                PsiFile containingFile = statement.getContainingFile();
                if (containingFile instanceof PsiJavaFile) {
                    PsiJavaFile file = (PsiJavaFile) containingFile;
                    String packageName = file.getPackageName();
                    PsiJavaCodeReferenceElement importReference = statement.getImportReference();

                    if (importReference != null) {
                        String importedPackage = importReference.getQualifiedName();


                        // Validate only imports between 'domain', 'application' and 'infrastructure' packages
                        if (!isCustomPackage(packageName) || !isCustomPackage(importedPackage)) {

                            return;
                        }

                        if (packageName.contains("infrastructure")) {
                            return;
                        }

                        if (packageName.contains("application") && (importedPackage.contains("infrastructure"))) {

                                holder.registerProblem(statement, "Imports from 'infrastructure' are not allowed in 'application' package");

                        }

                        if (packageName.contains("domain") && (!importedPackage.contains("domain"))) {

                                holder.registerProblem(statement, "Imports outside 'domain' package are not allowed in 'domain' package");

                        }
                    }
                }
            }

            private boolean isCustomPackage(String packageName) {
                return packageName.contains("domain") || packageName.contains("application") || packageName.contains("infrastructure");
            }
        };
    }
}
