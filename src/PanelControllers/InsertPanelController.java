package PanelControllers;

import BST.BSTInsert;
import Data.Account;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import Mani.Controller;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InsertPanelController {

    @FXML
    private StackPane imageStack;

    @FXML
    private TextField insertPaneFnameText;

    @FXML
    private TextField insertPaneLnameText;

    @FXML
    private TextField insertPaneSSNameText;

    @FXML
    private MenuButton insertPaneMenuButton;

    @FXML
    private RadioMenuItem Male;

    @FXML
    private ToggleGroup genderGroup;

    @FXML
    private RadioMenuItem Female;

    @FXML
    private TextField insertPaneAgeText;

    @FXML
    private TextField insertPaneIDText;

    @FXML
    private Button insertClearButton;

    @FXML
    private Button insertSaveButton;

    @FXML
    private Button insertNewButton;

    @FXML
    private Button imageChose;

    @FXML
    private ImageView insertPaneImage;

    @FXML
    void genderSelect() {
        insertPaneMenuButton.setText((String)genderGroup.getSelectedToggle().getUserData());
        if(imagePath == null) {
            try {
                Controller.setImage(insertPaneImage, insertPaneMenuButton.getText() + ".jpg");
            } catch (FileNotFoundException ex) {
                Controller.ErrorDisplay("AccountImage ERROR : 404\nFile missing!");
            }
        }
    }

    @FXML
    void imageChoseSelected() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chose Image");
        try{
            imagePath = Paths.get(fileChooser.showOpenDialog(imageStack.getScene().getWindow()).getAbsolutePath());
        }catch (Exception ex){
            imagePath = null;
            return;
        }
        try {
            Controller.setImage(insertPaneImage,imagePath);
        }catch (Exception ex) {
            imagePath = null;
            Controller.ErrorDisplay("Image choose failed. please try again");
        }
    }

    @FXML
    void insertClearButtonSelected() {
        genderGroup.selectToggle(null);
        insertPaneFnameText.setText("");
        insertPaneLnameText.setText("");
        insertPaneSSNameText.setText("");
        insertPaneMenuButton.setText("Chose");
        insertPaneAgeText.setText("");
        insertPaneIDText.setText("");
        try {
            Controller.setImage(insertPaneImage,"person.jpg");
        } catch (FileNotFoundException ex) {
            Controller.ErrorDisplay("insertPanelImage ERROR : 404\nFile missing!");
        }
    }

    @FXML
    void insertNewButtonSelected() {
        insertNewButton.setVisible(false);
        insertSaveButton.setVisible(true);
        insertPaneFnameText.setDisable(false);
        insertPaneLnameText.setDisable(false);
        insertPaneSSNameText.setDisable(false);
        insertPaneAgeText.setDisable(false);
        Male.setDisable(false);
        Female.setDisable(false);
        insertPaneIDText.setDisable(true);
        insertClearButton.fire();
    }

    @FXML
    void insertSaveButtonSelected() {
        String firstName = insertPaneFnameText.getText().replace(" ","");
        if(firstName.isEmpty()){
            Controller.Alert("Please enter FirstName first!");
            return;
        }
        String lastName = insertPaneLnameText.getText().replace(" ","");
        if(lastName.isEmpty()){
            Controller.Alert("Please enter LastName before save!");
            return;
        }
        String SSNumber;
        SSNumber =
                insertPaneSSNameText.getText().replace(" ","").replace("-","");
        if(SSNumber.isEmpty()){
            Controller.Alert("Please enter SocialSecurityNumber");
            return;
        }
        String gender;
        if(genderGroup.getSelectedToggle() == null){
            Controller.Alert("Please select gender first!");
            return;
        }else{
            gender = (String) genderGroup.getSelectedToggle().getUserData();
        }
        int age;
        try {
            age = Integer.parseInt(insertPaneAgeText.getText().replace(" ",""));
        }catch (Exception ex){
            Controller.Alert("Please enter Age");
            return;
        }

        try {
            Account account = new Account(firstName, lastName, gender, age, SSNumber);
            if(imagePath != null && Files.exists(imagePath)) {
                account.setPicture(imagePath);
            }
            list.insert(account);
            insertPaneIDText.setText(account.getID());
            insertNewButton.setVisible(true);
            insertSaveButton.setVisible(false);
            System.out.println("new Account inserted:" + account);
        }catch (IllegalArgumentException ex ){
            ex.printStackTrace();
            Controller.ErrorDisplay("Account set failed");
            insertClearButton.fire();
            return;
        } catch (Exception ex) {System.out.println(ex.getMessage());}
        insertPaneFnameText.setDisable(true);
        insertPaneLnameText.setDisable(true);
        insertPaneSSNameText.setDisable(true);
        insertPaneAgeText.setDisable(true);
        Male.setDisable(true);
        Female.setDisable(true);
        insertPaneIDText.setDisable(false);
    }

    @FXML
    void onMouseEnteredAction() {
        imageChose.setVisible(true);
    }

    @FXML
    void onMouseExitedAction() {
        imageChose.setVisible(false);
    }

    private BSTInsert list;

    private Path  imagePath = null;

    public void setList(BSTInsert list){
        this.list = list;
    }

    @FXML private void initialize(){
        try {
            Controller.setImage(insertPaneImage,"person.jpg");
        } catch (FileNotFoundException ex) {
            Controller.ErrorDisplay("insertPanelImage ERROR : 404\nFile missing!");
        }
        Male.setUserData("Male");
        Female.setUserData("Female");
    }

}
