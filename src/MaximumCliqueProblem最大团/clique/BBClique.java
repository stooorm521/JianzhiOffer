package  MaximumCliqueProblem最大团.clique;

public class BBClique {
	/*    public int[][] a=   {{0,1,0,0,0,0,0,0,0,0,0,0,0,0,1},
     {1,0,1,0,0,0,0,0,0,1,0,0,0,0,0},
     {0,1,0,1,0,0,0,1,0,0,0,0,0,0,0},
     {0,0,1,0,1,0,0,0,0,0,0,0,0,0,0},
     {0,0,0,1,0,1,0,0,0,1,0,0,0,0,0},
     {0,0,0,0,1,0,1,0,0,0,0,0,0,0,0},
     {0,0,0,0,0,1,0,1,0,0,0,0,0,0,1},
     {0,0,1,0,0,0,1,0,1,0,0,0,0,0,0},
     {0,0,0,0,0,0,0,1,0,1,0,0,0,0,0},
     {0,1,0,0,1,0,0,0,1,0,1,0,0,0,0},
     {0,0,0,0,0,0,0,0,0,1,0,1,1,1,0},
     {0,0,0,0,0,0,0,0,0,0,1,0,1,1,0},
     {0,0,0,0,0,0,0,0,0,0,1,1,0,1,0},
     {0,0,0,0,0,0,0,0,0,0,1,1,1,0,1},
     {1,0,0,0,0,0,1,0,0,0,0,0,0,1,0}};//15点图邻接矩阵*/

    public int[][] a=  {{0,1,0,1,1},
            {1,0,1,0,1},
            {0,1,0,0,1},
            {1,0,0,0,1},
            {1,1,1,1,0}}; //示例图G的邻接矩阵*/

    /* public int[][] a=  {{0,1,1,0,1},
                         {1,0,1,0,0},
                         {1,1,0,1,0},
                         {0,0,1,0,1},
                         {1,0,0,1,0}}; //对比前分布图的邻接矩阵*/
/* public int[][] a=  {{0,1,0,0,1},
                        {1,0,1,0,0},
                        {0,1,0,1,1},
                        {0,0,1,0,1},
                        {1,0,1,1,0}}; //对比后分布图的邻接矩阵*/
    int[] bestx=new int[a.length];
    CMaxHeap heap;        //活结点优先队列
    /** Creates a new instance of BBClique */
    public BBClique() {

    }
    public int maxClique(){
        //解最大团问题的优先队列式分支限界法
        int n=bestx.length;
        heap = new CMaxHeap();

        CBBnode enode = null;
        int i=1;
        int cn=0;//当前团的顶点数
        int bestn=0;//最大团顶点数
        //搜索子集空间树
        while (i!=n+1){//复杂度N 2^n（O(n)+O(lgn)+O(n)+O(n)）=O(n2^n）
            //非叶子结点
            //检查顶点i与当前团中其他顶点之间是否有边相连
            boolean ok = true;
            CBBnode bnode = enode;
            for (int j=i-1;j>0;bnode=bnode.parent,j--){//T(n)=O(n)
                if (bnode.leftChild && a[i-1][j-1]==0){
                    ok = false;
                    break;
                }
            }
            if (ok){
                //左儿子结点为可行结点
                if (cn+1>bestn) bestn=cn+1;
                addLiveNode(cn+n-i+1,cn+1,i+1,enode,true);//T(n)=O(lgn)
                //heap.printHeap();
            }
            if (cn+n-i>=bestn){
                //右子树可能含最优解
                addLiveNode(cn+n-i,cn,i+1,enode,false);
                //heap.printHeap();
            }
            //取一下扩展结点
            //heap.printHeap();
            CHeapNode node=(CHeapNode)heap.removeMax();//T(n)=O(n)
            //heap.printHeap();
            enode=node.liveNode;
            cn=node.cliqueSize;
            i=node.level;
        }
        //构造当前最优解
        for (int j=n;j>0;j--){//T(n)=O(n)
            bestx[j-1]=(enode.leftChild)?1:0;
            enode=enode.parent;
        }

        return bestn;
    }
    /**
     * 将整数插入到堆中;T(n)=O(lgn)
     *
     * @param
     */
    private void addLiveNode(int up,int size,int lev,CBBnode par,boolean ch){
        //将活结点加入到子集空间树中并插入最大堆中
        CBBnode b = new CBBnode(par,ch);
        CHeapNode node = new CHeapNode(b,up,size,lev);
        this.heap.insert(node);
        //this.heap.
    }

    public void printa(){
        for(int i=0;i<a.length;i++){
            System.out.println("");
            for(int j=0;j<a.length;j++)
                System.out.print(this.a[i][j]+" ");

        }
        System.out.println("");
    }
    public void printb(){
        System.out.print("最大团顶点序列为：");
        for(int i=0;i<bestx.length;i++){
            System.out.print(bestx[i]+" ");
            //System.out.println("");
        }
    }
}
