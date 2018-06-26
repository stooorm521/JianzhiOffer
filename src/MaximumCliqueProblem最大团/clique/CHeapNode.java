package MaximumCliqueProblem最大团.clique;

public  class CHeapNode{
    CBBnode liveNode;    //活对结点
    int upperSize;       //结点的价植上界
    int cliqueSize;      //团的顶点数
    int level;           //活结点在子集树中所处的层序号

    //构造方法
    CHeapNode(CBBnode node,int up,int size,int lev){
        liveNode=node;
        upperSize=up;
        cliqueSize=size;
        level=lev;
        //System.out.println("liveNode="+liveNode+" upperSize="+upperSize+" cliqueSize="+cliqueSize+" level="+level);
    }

}
