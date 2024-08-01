package hexagonal;


import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AddSelectedTextAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getProject();
        VirtualFile selectedFile = event.getData(com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE);

        if (project == null || selectedFile == null) {
            Messages.showErrorDialog(project, "No file selected", "Error");
            return;
        }


        JFrame hexagonalFrame = new JFrame("New Hexagonal Architecture");
        hexagonalFrame.setSize(300, 200);
        hexagonalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton create = new JButton("Create");
        JButton cancel = new JButton("Cancel");

        JPanel panelButtons = new JPanel();
        JPanel panelLabels = new JPanel();

        JLabel messageFiles = new JLabel("Create Message");
        JCheckBox consumerB = new JCheckBox("Create consumer package");
        JCheckBox producerB = new JCheckBox("Create producer package");

        //Add CheckBox into Main Panel
        panelLabels.add(consumerB);
        panelLabels.add(producerB);
        panelLabels.setLayout(new FlowLayout(FlowLayout.LEFT));
        //
        panelButtons.add(create);
        panelButtons.add(cancel);
        panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER));

        hexagonalFrame.add(messageFiles);
        hexagonalFrame.add(panelLabels);
        hexagonalFrame.add(panelButtons);
        hexagonalFrame.setLayout(new GridLayout(3, 1));


        //Actions
        create.addActionListener(createButtonListener(project, selectedFile, hexagonalFrame, consumerB, producerB));
        cancel.addActionListener(e -> hexagonalFrame.dispose());

        hexagonalFrame.setResizable(false);
        // Configura la posición de la ventana
        hexagonalFrame.setLocationRelativeTo(null);
        // Mostrar la ventana
        hexagonalFrame.setVisible(true);

    }

    private ActionListener createButtonListener(Project project, VirtualFile selectedFile, JFrame frame, JCheckBox consumer, JCheckBox producer) {
        return e -> {
            try {

                String basePath = selectedFile.getPath();
                String newDirPathInfra = basePath + "/infrastructure";
                String newDirPathAppli = basePath + "/application";
                String newDirPathDomai = basePath + "/domain";
                Files.createDirectory(Paths.get(newDirPathInfra));
                // adapter, config, controller, entities, repository, message
                String newDirAdapters = newDirPathInfra + "/adapters";
                String newDirConfig = newDirPathInfra + "/config";
                String newDirController = newDirPathInfra + "/controllers";
                String newDirEntitiesInf = newDirPathInfra + "/entities";
                String newDirRepository = newDirPathInfra + "/repositories";
                Files.createDirectory(Paths.get(newDirAdapters));
                Files.createDirectory(Paths.get(newDirConfig));
                Files.createDirectory(Paths.get(newDirController));
                Files.createDirectory(Paths.get(newDirEntitiesInf));
                Files.createDirectory(Paths.get(newDirRepository));

                if(consumer.isSelected()){
                    String newDirConsumer = newDirPathInfra + "/message/consumer";
                    Files.createDirectories(Paths.get(newDirConsumer));
                }
                if(producer.isSelected()){
                    String newDirProducer = newDirPathInfra + "/message/producer";
                    Files.createDirectories(Paths.get(newDirProducer));
                }

                Files.createDirectory(Paths.get(newDirPathAppli));
                //service and use case
                String newDirServices = newDirPathAppli + "/services";
                String newDirUseCases = newDirPathAppli + "/useCases";
                Files.createDirectory(Paths.get(newDirServices));
                Files.createDirectory(Paths.get(newDirUseCases));

                Files.createDirectory(Paths.get(newDirPathDomai));
                //entities, ports (in, out)
                String newDirEntities = newDirPathDomai + "/entities";
                String newDirIn = newDirPathDomai + "/port/in";
                String newDirOut = newDirPathDomai + "/port/out";
                Files.createDirectory(Paths.get(newDirEntities));
                Files.createDirectories(Paths.get(newDirIn));
                Files.createDirectories(Paths.get(newDirOut));



                Messages.showInfoMessage(project, "All files have been created", "Success");

            } catch (IOException ioException) {
                Messages.showErrorDialog(project, "Error creating architecture", "Error");
            }
            frame.dispose();
        };
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        Presentation presentation = e.getPresentation();
        VirtualFile selectedFile = e.getData(com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE);

        // La acción es visible solo si el archivo seleccionado es una carpeta
        // Oculta la acción si no se selecciona una carpeta
        presentation.setVisible(selectedFile != null && selectedFile.isDirectory());
    }

}