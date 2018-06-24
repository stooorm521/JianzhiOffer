package MaximumCliqueProblem最大团.clique;

public class CBBnode {
    CBBnode parent;
    boolean leftChild;

    public CBBnode(CBBnode par,boolean ch) {
        parent=par;//ָ�򸸽��
        leftChild=ch;//����ӽ���־
                
    }
    public CBBnode(){}
    
}