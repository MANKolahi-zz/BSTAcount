package BST;

import Data.ComparableData;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BSTNode{
    private ComparableData Data;
    private BSTNode right;
    private BSTNode left;

    private BSTNode(ComparableData Data, BSTNode right, BSTNode left){
        this.Data = Data;
        this.right = right;
        this.left = left;
    }

    BSTNode(){
        this(null,null,null);
    }

    void insert(ComparableData data){
        if(this.getData() == null){
            this.setData(data);
        }else if (this.getData().getComparableValue().compareTo(data.getComparableValue()) < 0 ){
            if(left == null)
                left = new BSTNode();
            left.insert(data);
        }else {
            if(this.right == null)
                this.right = new BSTNode();
            right.insert(data);
        }
    }

    ComparableData search(BigDecimal dataID){
        int compareResult = getData().getComparableValue().compareTo(dataID);
        if(compareResult == 0){
            return getData();
        }else if(compareResult > 0){
            if (this.right == null){
                return null;
            }else return right.search(dataID);
        }else {
            if(this.left == null){
                return null;
            }else return  left.search(dataID);
        }
    }

    List<ComparableData> getList(){  //PostOrder : LVR
        List<ComparableData> list = new ArrayList<>();
        if(getData() != null) {
            if (this.left != null) { //L
                list.addAll(this.left.getList());
            }
            list.add(getData()); // V
            if (this.right != null) { //R
                list.addAll(this.right.getList());
            }
        }
        return list;
    }

    public ComparableData getData(){
        return this.Data;
    }

    private void setData(ComparableData data){
        this.Data = data;
    }


}
