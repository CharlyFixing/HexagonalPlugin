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
                        data.setLocationString("Contains application-specific logic (use cases)");
                        break;
                    case "inputPort":
                        data.setLocationString("Contains all interfaces for input ports, defining how the application interacts with external systems.");
                        break;
                    case "service":
                        data.setLocationString("Contains service classes that implement application use cases.");
                        break;
                    case "dto":
                        data.setLocationString("Contains Data Transfer Objects (DTOs) for transferring data between processes.");
                        break;
                    case "request":
                        data.setLocationString("Contains DTOs specifically for incoming requests.");
                        break;
                    case "response":
                        data.setLocationString("Contains DTOs specifically for outgoing responses.");
                        break;
                    case "domain":
                        data.setLocationString("Contains domain entities that represent the core of the business logic.");
                        break;
                    case "model":
                        data.setLocationString("Contains classes that represent domain models and encapsulate business rules.");
                        break;
                    case "outputPort":
                        data.setLocationString("Contains all interfaces for output ports, defining how the application communicates with external resources.");
                        break;
                    case "repository":
                        data.setLocationString("Contains interfaces for data access and persistence operations.");
                        break;
                    case "infrastructure":
                        data.setLocationString("Contains infrastructure-related code that supports the application.");
                        break;
                    case "dao":
                        data.setLocationString("Contains implementations of Data Access Objects (DAO) for interacting with data sources.");
                        break;
                    case "inputAdapter":
                        data.setLocationString("Contains implementations of input ports, such as controllers for incoming requests.");
                        break;
                    case "controller":
                        data.setLocationString("Contains REST or GraphQL controllers that handle incoming requests and return responses.");
                        break;
                    case "validator":
                        data.setLocationString("Contains classes for validating input data from requests.");
                        break;
                    case "converter":
                        data.setLocationString("Contains converters that transform DTOs into domain models.");
                        break;
                    case "feign":
                        data.setLocationString("Contains Feign clients for communicating with external services.");
                        break;
                    case "outputAdapter":
                        data.setLocationString("Contains implementations of output ports that handle communication with external resources.");
                        break;
                    case "persistence":
                        data.setLocationString("Contains implementations of persistence adapters for interacting with databases.");
                        break;
                    case "messaging":
                        data.setLocationString("Contains implementations for messaging systems (e.g., Kafka consumers and producers).");
                        break;
                    case "configuration":
                        data.setLocationString("Contains application configuration classes for managing dependencies and settings.");
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
