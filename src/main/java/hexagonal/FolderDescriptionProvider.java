package hexagonal;

import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ProjectViewNodeDecorator;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;
public class FolderDescriptionProvider implements ProjectViewNodeDecorator {

    @Override
    public void decorate(@NotNull com.intellij.ide.projectView.ProjectViewNode<?> node, @NotNull PresentationData data) {
        Project project = node.getProject();
        if (project == null) {
            return;
        }

        VirtualFile file = node.getVirtualFile();
        if (file != null && file.isDirectory()) {
            PsiDirectory directory = PsiManager.getInstance(project).findDirectory(file);
            if (directory != null) {
                String folderName = directory.getName();
                switch (folderName) {
                    case "application":
                        data.setLocationString("Contains application-specific logic (use-case)");
                        break;
                    case "domain":
                        data.setLocationString("Contains domain entities");
                        break;
                    case "infrastructure":
                        data.setLocationString("Contains infrastructure-related code");
                        break;
                    case "inputAdapter":
                        data.setLocationString("Contains the implementation from inputPorts");
                        break;
                    case "inputPort":
                        data.setLocationString("Contains all interfaces example: service");
                        break;
                    case "outputAdapter":
                        data.setLocationString("Contains the implementation from outputPorts");
                        break;
                    case "outputPort":
                        data.setLocationString("Contains all interfaces example: db");
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
