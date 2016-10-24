package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class Controller {


    public TextArea txtArea;
    public Menu menEdicio;
    public MenuItem itEnganxar;
    public MenuItem itTallar;
    public MenuItem itCopiar;
    public Button  btTallar;
    public Button  btCopiar;
    public MenuItem ctCopiar;
    public MenuItem ctTallar;
    public ContextMenu ctMenu;



    public void desferAction(ActionEvent actionEvent) {

          txtArea.undo();
    }

    public void tallarAction(ActionEvent actionEvent) {

        txtArea.cut();
    }

    public void copiarAction(ActionEvent actionEvent) {
        txtArea.copy();
    }

    public void enganxarAction(ActionEvent actionEvent) {
        txtArea.paste();

    }

    public void canviarFont (ActionEvent actionEvent) {

        MenuItem clickeado = (MenuItem) actionEvent.getSource();
        txtArea.setFont(Font.font(clickeado.getText(), FontWeight.NORMAL, txtArea.getFont().getSize()));

    }



    public void canviarTamany (ActionEvent actionEvent) {

        MenuItem clickeado = (MenuItem) actionEvent.getSource();
        txtArea.setFont(Font.font(txtArea.getFont().getFamily(), FontWeight.NORMAL, Double.parseDouble(clickeado.getText())));
    }

    public void alertShowAction (ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sobre l'editor");
        alert.setHeaderText(null);
        alert.setContentText("Creat per Albert Acerete");

        alert.showAndWait();

    }

    public void activateMenu(){


        txtArea.selectionProperty().addListener(observable -> {
            if (txtArea.getSelectedText().isEmpty()){
                btCopiar.setDisable(true);
                btTallar.setDisable(true);
            }else{
                btCopiar.setDisable(false);
                btTallar.setDisable(false);
            }

        });

        if (txtArea.getSelectedText().isEmpty()){
            itTallar.setDisable(true);
            itCopiar.setDisable(true);
           //ctCopiar.setDisable(true);
           //ctTallar.setDisable(true);
        }else{
            itTallar.setDisable(false);
            itCopiar.setDisable(false);
            btTallar.setDisable(false);
            btCopiar.setDisable(false);
            //ctCopiar.setDisable(false);
            //ctTallar.setDisable(false);
        }

    }

    public void obreFitcher(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) txtArea.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Obre un arxiu");
        File f = fileChooser.showOpenDialog(stage);

        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String resultat = "";

        while (br.ready()) {
            resultat = resultat + br.readLine() + "\n";
        }

        txtArea.setText(resultat);

        stage.setTitle(f.getName());

        br.close();
    }

    public void guardaFitcher(ActionEvent actionEvent){

        Stage stage = (Stage) txtArea.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Obre un arxiu");

            //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);


            //Show save file dialog
        File file = fileChooser.showSaveDialog(stage);

            if(file != null){
                SaveFile(txtArea.getText(), file);
            }

        stage.setTitle(file.getName());

    }

    private void SaveFile(String content, File file){
        try {
            FileWriter fileWriter;

            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {

        }

    }




    public void salirAction(ActionEvent actionEvent) {
        Platform.exit();
    }

}
