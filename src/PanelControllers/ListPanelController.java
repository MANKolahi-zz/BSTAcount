package PanelControllers;

import BST.BSTView;
import Data.Account;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

public class ListPanelController {

    @FXML
    private ListView<Account> list;

    @FXML
    void listPaneDeleteButtonSelected() {
       /* Account account = list.getSelectionModel().getSelectedItem();
        if(account != null && Controller.confirmation("Are you sure to delete this Account:" + account)){
            list.getItems().remove(account);
            bstView.removeData(account.getComparableValue());
        }*/
    }

    private BSTView bstView;

    public void setBSTView(BSTView bstView){
        this.bstView = bstView;
    }

    public void resetList(){
        list.getItems().remove(0,list.getItems().size());
        List temp = bstView.getList();
        for (Object aTemp : temp) {
            list.getItems().add((Account) aTemp);
        }
    }

}
