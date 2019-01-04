package BST;

import Data.ComparableData;

import java.math.BigDecimal;
import java.util.List;

public interface BSTView {
    public abstract ComparableData search(ComparableData data);
    public abstract ComparableData search(BigDecimal dataID);
    public abstract List<ComparableData> getList();
}
