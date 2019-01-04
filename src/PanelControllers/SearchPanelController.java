package PanelControllers;

import BST.BSTView;
import Data.Account;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import Mani.Controller;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.nio.file.Path;

public class SearchPanelController {

    @FXML
    private GridPane searchPanePersonInformation;

    @FXML
    private TextField searchPaneFnameText;

    @FXML
    private TextField searchPaneLnameText;

    @FXML
    private TextField searchPaneSSNumText;

    @FXML
    private TextField searchPaneGenderText;

    @FXML
    private TextField searchPaneAgeText;

    @FXML
    private TextField searchPaneIDText;

    @FXML
    private HBox searchBox;

    @FXML
    private TextField searchText;

    @FXML
    private ImageView searchImage;

    @FXML
    private Text searchDisplay;

    private boolean animationRunning = false;

    private void openSearchInfo(Path imageURL){
        if(!animationRunning) {
            //set person Image
            try {
                if(imageURL.isAbsolute())
                    Controller.setImage(searchImage, imageURL);
                else {
                    Controller.setImage(searchImage, imageURL.toString());
                }
            }catch (Exception ex){
                Controller.ErrorDisplay("Account image ERROR : 404\nAccount image not founded");
            }
            //set search box animation;
            TranslateTransition searchBoxAnimation = new TranslateTransition(Duration.millis(900));
            searchBoxAnimation.setToY(-175);
            searchBoxAnimation.setNode(searchBox);
            searchBoxAnimation.setInterpolator(Interpolator.EASE_IN);
            //set info box animation;
            FadeTransition infoBoxAnimation = new FadeTransition(Duration.millis(400));
            searchPanePersonInformation.setVisible(true);
            infoBoxAnimation.setFromValue(0.0);
            infoBoxAnimation.setToValue(1.0);
            infoBoxAnimation.setNode(searchPanePersonInformation);
            //set display animation;
            FadeTransition displayAnimation = new FadeTransition(Duration.millis(500));
            displayAnimation.setToValue(0.0);
            displayAnimation.setNode(searchDisplay);
            //set animation player;
            ParallelTransition animationPlayer =
                    new ParallelTransition(searchBoxAnimation, infoBoxAnimation, displayAnimation);
            animationPlayer.setOnFinished(event -> animationRunning = false);
            animationRunning = true;
            animationPlayer.play();
        }
    }

    private void closeSearchInfo(String displayMassage){
        if(!animationRunning) {
            //set search image;
            try {
                Controller.setImage(searchImage, "person.jpg");
            } catch (FileNotFoundException ex) {
                Controller.ErrorDisplay("person Image Error : 404\nplease reinstall program");
            }
            //set search bar animation;
            TranslateTransition searchBoxAnimation = new TranslateTransition(Duration.millis(1000));
            searchBoxAnimation.setToY(0);
            searchBoxAnimation.setNode(searchBox);
            searchBoxAnimation.setInterpolator(Interpolator.EASE_IN);
            //set info box animation;
            FadeTransition infoBoxAnimation = new FadeTransition(Duration.millis(1000));
            infoBoxAnimation.setToValue(0.0);
            infoBoxAnimation.setNode(searchPanePersonInformation);
            //set display
            searchDisplay.setText(displayMassage);
            FadeTransition displayAnimation = new FadeTransition(Duration.millis(1000));
            displayAnimation.setToValue(1.0);
            displayAnimation.setNode(searchDisplay);
            displayAnimation.setInterpolator(Interpolator.EASE_IN);
            displayAnimation.play();
            //set animation player for info box and search bar and display;
            ParallelTransition animationPlayer = new ParallelTransition(searchBoxAnimation, infoBoxAnimation , displayAnimation);
            animationPlayer.setOnFinished(event -> {
                searchDisplay.setText("Enter Person ID");
                animationRunning = false;
            });
            animationRunning = true;
            animationPlayer.play();
        }
    }

    @FXML
    void searchButtonSelected() {
        String temp = searchText.getText().replace("-","").replace(" ","");
        if(!temp.isEmpty()) {
            Account account = (Account) bstView.search(new BigDecimal(temp));
            if(account != null) {
                searchPaneFnameText.setText(account.getFirstName());
                searchPaneLnameText.setText(account.getLastName());
                searchPaneAgeText.setText(String.valueOf(account.getAge()));
                searchPaneGenderText.setText(account.getGender());
                searchPaneSSNumText.setText(String.valueOf(account.getSocialSecurityNumber()));
                searchPaneIDText.setText(account.getID());
                openSearchInfo(account.getPicture());
            }
            else{
                closeSearchInfo("Account not founded");
            }
        }else{
            closeSearchInfo("Enter some ID to search!");
        }
    }

    private BSTView bstView;

    public void reset(){
        closeSearchInfo("Enter Person ID");
    }

    public void setBSTView(BSTView bstVew){
        this.bstView = bstVew;
    }

    @FXML private void initialize(){
        try {
            Controller.setImage(searchImage,"person.jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
