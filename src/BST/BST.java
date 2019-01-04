package BST;

import Data.ComparableData;

import java.math.BigDecimal;
import java.util.List;

public class BST implements BSTInsert , BSTView{
    private BSTNode head = new BSTNode();

    private void insert(ComparableData data){
        head.insert(data);
    }

    public void insert(ComparableData... data){
        for (ComparableData comparableData:
            data ) {
            insert(comparableData);
        }
    }

    public ComparableData search(ComparableData data){
        return search(data.getComparableValue());
    }

    public ComparableData search(BigDecimal dataID){
        return head.search(dataID);
    }

    @Override
    public List<ComparableData> getList() {
        return head.getList();
    }

}