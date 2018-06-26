package MaximumCliqueProblem最大团.clique;

public class CBBnode {
    CBBnode parent;
    boolean leftChild;

    public CBBnode(CBBnode par,boolean ch) {
        parent=par;//指向父结点
        leftChild=ch;//左儿子结点标志

    }
    public CBBnode(){}

}