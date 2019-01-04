package Mani;

import BST.BST;
import Data.Account;
import PanelControllers.InsertPanelController;
import PanelControllers.ListPanelController;
import PanelControllers.SearchPanelController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.FileNotFoundException;
import java.nio.file.Path;

public class Controller {

    @FXML
    private ToggleButton insertButton;

    @FXML
    private ToggleGroup panelSelectGroup;

    @FXML
    private ToggleButton searchButton;

    @FXML
    private ToggleButton listButton;

    @FXML
    private ImageView searchIcon;

    @FXML
    private ImageView insertIcon;

    @FXML
    private ImageView listIcon;

    @FXML
    private AnchorPane searchStack;

    @FXML
    private AnchorPane insertStack;

    @FXML
    private AnchorPane listStack;

    private ToggleButton selectedButton;

    @FXML
    void panelChang() {
        if(panelSelectGroup.getSelectedToggle() != null) {
            selectedButton = (ToggleButton) panelSelectGroup.getSelectedToggle();
            selectStack((String) selectedButton.getUserData());
        }else {
            panelSelectGroup.selectToggle(selectedButton);
            selectStack((String) selectedButton.getUserData());
        }
    }

    private void selectStack(String pane){
        searchStack.setVisible(false);
        listStack.setVisible(false);
        insertStack.setVisible(false);
        switch (pane){
            case "searchStack" :
                searchStack.setVisible(true);
                searchPanelController.reset();
                break;
            case "listStack":
                listStack.setVisible(true);
                listPanelController.resetList();
                break;
            case "insertStack":
                insertStack.setVisible(true);
                break;
        }
    }

    @FXML private InsertPanelController insertPanelController;
    @FXML private ListPanelController listPanelController;
    @FXML private SearchPanelController searchPanelController;

    private BST bstList;

    private void createTestAccounts(){
        Account test0 = new Account
                ("firstName0", "lastName0", "female", 20, "1111111111");
        Account test1 = new Account
                ("firstName1", "lastName1", "female", 20, "1111111111");
        Account test2 = new Account
                ("firstName2", "lastName2", "male", 20, "1111111111");
        Account test3 = new Account
                ("firstName3", "lastName3", "male", 20, "1111111111");
        Account test4 = new Account
                ("firstName4", "lastName4", "male", 20, "1111111111");
        bstList = new BST();
        bstList.insert(test0,test4,test2,test3,test1);
    }

    public static void Alert(String massage){
        Alert alert = new Alert(Alert.AlertType.INFORMATION,massage,ButtonType.OK);
        alert.showAndWait();
    }

    public static void ErrorDisplay(String massage){
        Alert alert = new Alert(Alert.AlertType.ERROR, massage, ButtonType.OK);
        alert.showAndWait();
    }

    public static void setImage(ImageView imageView, String url) throws FileNotFoundException {
        try {
            imageView.setImage(new Image(url));
        }catch (IllegalArgumentException ex){
            throw new FileNotFoundException();
        }
    }

    public static void setImage(ImageView imageView, Path path) {
        imageView.setImage(new Image(path.toUri().toString()));
    }

    @FXML private void initialize(){
        createTestAccounts();
        insertButton.setUserData("insertStack");
        searchButton.setUserData("searchStack");
        listButton.setUserData("listStack");
        selectedButton = searchButton;
        listPanelController.setBSTView(bstList);
        insertPanelController.setList(bstList);
        searchPanelController.setBSTView(bstList);
        try {
            setImage(listIcon,"list.png");
            setImage(insertIcon,"addPerson.png");
            setImage(searchIcon,"searchIcon.jpg");
        }catch (FileNotFoundException ex){
            Mani.Controller.ErrorDisplay("ProgramIcons ERROR : 404\nFile missing!");
        }
    }


}
