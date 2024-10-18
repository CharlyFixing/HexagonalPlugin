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
                infrastructureDirectory(basePath, consumer, producer);
                applicationDirectory(basePath);
                domainDirectory(basePath);

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

    public void infrastructureDirectory(String basePath, JCheckBox consumer, JCheckBox producer) throws IOException {
        String newDirPathInfra = basePath + "/infrastructure";
        Files.createDirectory(Paths.get(newDirPathInfra));

        // dao, inputAdapter, outputAdapter, configuration
        String newDirAdapter = newDirPathInfra + "/adapter";
        String newDirConfig = newDirPathInfra + "/configuration";
        String newDirInputAdapter = newDirAdapter + "/input";
        String newDirInputAdapterCon = newDirInputAdapter + "/controller";
        String newDirInputAdapterV = newDirInputAdapter + "/validator";
        String newDirInputAdapterC = newDirInputAdapter + "/converter";

        String newDirOutPutAdapter = newDirAdapter + "/output";
        String newDirOutAdapterF = newDirOutPutAdapter + "/feign";
        String newDirOutPutAdapterP = newDirOutPutAdapter + "/persistence";

        Files.createDirectories(Paths.get(newDirAdapter));
        Files.createDirectories(Paths.get(newDirConfig));
        Files.createDirectories(Paths.get(newDirInputAdapter));
        Files.createDirectories(Paths.get(newDirInputAdapterCon));
        Files.createDirectory(Paths.get(newDirInputAdapterV));
        Files.createDirectory(Paths.get(newDirInputAdapterC));

        Files.createDirectories(Paths.get(newDirOutPutAdapter));
        Files.createDirectory(Paths.get(newDirOutAdapterF));
        Files.createDirectory(Paths.get(newDirOutPutAdapterP));

        if(consumer.isSelected()){
            String newDirConsumer = newDirInputAdapter + "/messaging/consumer";
            Files.createDirectories(Paths.get(newDirConsumer));
        }
        if(producer.isSelected()){
            String newDirProducer = newDirOutPutAdapter + "/messaging/producer";
            Files.createDirectories(Paths.get(newDirProducer));
        }
    }
    public void applicationDirectory(String basePath) throws IOException {
        String newDirPathAppli = basePath + "/application";
        Files.createDirectory(Paths.get(newDirPathAppli));
        //service and use case

        String newDirInputPort = newDirPathAppli + "/port/input";
        String newDirOutPutPort = newDirPathAppli + "/port/output";
        String newDirService = newDirPathAppli + "/service";
        String newDirDto = newDirPathAppli + "/dto";
        String newDirDtoR = newDirDto + "/request";
        String newDirDtoRP = newDirDto + "/response";

        Files.createDirectories(Paths.get(newDirInputPort));
        Files.createDirectories(Paths.get(newDirOutPutPort));
        Files.createDirectories(Paths.get(newDirService));
        Files.createDirectories(Paths.get(newDirDto));
        Files.createDirectories(Paths.get(newDirDtoR));
        Files.createDirectories(Paths.get(newDirDtoRP));
    }
    public void domainDirectory(String basePath) throws IOException{
        String newDirPathDomain = basePath + "/domain";
        Files.createDirectory(Paths.get(newDirPathDomain));

        //model, outputPort, repository
        String newDirModel = newDirPathDomain + "/model";
        String newDirRepository = newDirPathDomain + "/repository";

        Files.createDirectory(Paths.get(newDirModel));
        Files.createDirectory(Paths.get(newDirRepository));
    }
}