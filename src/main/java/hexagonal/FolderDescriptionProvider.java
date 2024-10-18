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
                String parentFolder = directory.getParentDirectory() != null ? directory.getParentDirectory().getName() : "";

                switch (folderName) {
                    case "application":
                        data.setLocationString("Contains application-specific logic (use cases).");
                        break;
                    case "port":
                        data.setLocationString("Contains interfaces for input and output ports, defining how the application interacts with external systems.");
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
                        if ("domain".equals(parentFolder)) {
                            data.setLocationString("Contains classes that represent domain models and encapsulate business rules.");
                        } else if ("persistence".equals(parentFolder)) {
                            data.setLocationString("Contains classes that represent infrastructure models, which may include database entities or mappings.");
                        }
                        break;
                    case "repository":
                        data.setLocationString("Contains interfaces for data access and persistence operations.");
                        break;
                    case "infrastructure":
                        data.setLocationString("Contains infrastructure-related code that supports the application.");
                        break;
                    case "adapter":
                        data.setLocationString("Contains input and output adapters for interacting with external systems.");
                        break;
                    case "input":
                        if ("adapter".equals(parentFolder)) {
                            data.setLocationString("Contains input adapters (REST controllers, etc.) that handle incoming requests.");
                        } else {
                            data.setLocationString("Contains input ports (e.g., use cases).");
                        }
                        break;
                    case "output":
                        if ("adapter".equals(parentFolder)) {
                            data.setLocationString("Contains output adapters that handle communication with external resources.");
                        } else {
                            data.setLocationString("Contains output ports, defining how the application communicates with infrastructure.");
                        }
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
                    case "dao":
                        data.setLocationString("Contains implementations of Data Access Objects (DAO) for interacting with data sources.");
                        break;
                    case "persistence":
                        data.setLocationString("Contains implementations of persistence adapters for interacting with databases.");
                        break;
                    case "messaging":
                        data.setLocationString("Contains implementations for messaging systems (e.g., Kafka, RabbitMQ).");
                        break;
                    case "consumer":
                        if ("messaging".equals(parentFolder)) {
                            data.setLocationString("Contains classes for consuming messages from messaging systems.");
                        }
                        break;
                    case "producer":
                        if ("messaging".equals(parentFolder)) {
                            data.setLocationString("Contains classes for producing messages to messaging systems.");
                        }
                        break;
                    case "feign":
                        data.setLocationString("Contains Feign clients for communicating with external services.");
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
