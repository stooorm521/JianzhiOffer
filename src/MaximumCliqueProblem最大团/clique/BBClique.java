package MaximumCliqueProblem最大团.clique;

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
     {1,0,0,0,0,0,1,0,0,0,0,0,0,1,0}};//15��ͼ�ڽӾ���*/
    
    public int[][] a=  {{0,1,0,1,1},
        {1,0,1,0,1},
        {0,1,0,0,1},
        {1,0,0,0,1},
        {1,1,1,1,0}}; //ʾ��ͼG���ڽӾ���*/
 
/* public int[][] a=  {{0,1,1,0,1},
	                 {1,0,1,0,0},
	                 {1,1,0,1,0},
	                 {0,0,1,0,1},
	                 {1,0,0,1,0}}; //�Ա�ǰ�ֲ�ͼ���ڽӾ���*/
/* public int[][] a=  {{0,1,0,0,1},
                        {1,0,1,0,0},
                        {0,1,0,1,1},
                        {0,0,1,0,1},
                        {1,0,1,1,0}}; //�ԱȺ�ֲ�ͼ���ڽӾ���*/
    int[] bestx=new int[a.length];
    CMaxHeap heap;        //�������ȶ���
    /** Creates a new instance of BBClique */
    public BBClique() {

    }
    public int maxClique(){
        //���������������ȶ���ʽ��֧�޽編
        int n=bestx.length;
        heap = new CMaxHeap();
        
        CBBnode enode = null;
        int i=1;
        int cn=0;//��ǰ�ŵĶ�����
        int bestn=0;//����Ŷ�����
        //�����Ӽ��ռ���
        while (i!=n+1){//���Ӷ�N 2^n��O(n)+O(lgn)+O(n)+O(n)��=O(n2^n��
            //��Ҷ�ӽ��
            //��鶥��i�뵱ǰ������������֮���Ƿ��б�����
            boolean ok = true;
            CBBnode bnode = enode;
            for (int j=i-1;j>0;bnode=bnode.parent,j--){//T(n)=O(n)
                if (bnode.leftChild && a[i-1][j-1]==0){
                ok = false;
                break;
                }
            }
            if (ok){
                //����ӽ��Ϊ���н��
                if (cn+1>bestn) bestn=cn+1;
                addLiveNode(cn+n-i+1,cn+1,i+1,enode,true);//T(n)=O(lgn)
                //heap.printHeap();
            }
            if (cn+n-i>=bestn){
                //���������ܺ����Ž�
                addLiveNode(cn+n-i,cn,i+1,enode,false);
                //heap.printHeap();
            }
            //ȡһ����չ���
            //heap.printHeap();
            CHeapNode node=(CHeapNode)heap.removeMax();//T(n)=O(n)
            //heap.printHeap();
            enode=node.liveNode;
            cn=node.cliqueSize;
            i=node.level;           
        }
        //���쵱ǰ���Ž�
        for (int j=n;j>0;j--){//T(n)=O(n)
            bestx[j-1]=(enode.leftChild)?1:0;
            enode=enode.parent;
        }

        return bestn;
    }
	/**
	 * ���������뵽����;T(n)=O(lgn)
	 * 
	 * @param val
	 */
    private void addLiveNode(int up,int size,int lev,CBBnode par,boolean ch){
     //��������뵽�Ӽ��ռ����в�����������
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
		System.out.print("����Ŷ�������Ϊ��");
    	for(int i=0;i<bestx.length;i++){
    		System.out.print(bestx[i]+" ");
    		//System.out.println("");
    	}
    }
}
