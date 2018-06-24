package MaximumCliqueProblem最大团.clique;
/**
 * ������һ������Ķ��ֶѣ��Լ����ϵ�һЩ���������Ͷ�����ȵ�
 */
public class CMaxHeap{
	final static int DEFAULT_CAPACITY = 200;// �ѵ����Ĭ�ϴ洢����

	CHeapNode[] array;   // �洢���е�Ԫ�ص�����

	int heapSize;        // ���е�ǰ�洢��Ԫ�صĸ��������ѵĳ���

	/**
	 * Ĭ�Ϲ��췽������һЩ������ʼ������
	 */
	public CMaxHeap() {
		heapSize = 0;
		array = new CHeapNode[DEFAULT_CAPACITY + 1];// ��һλ���洢Ԫ�أ���Ĭ��ֵΪ0
	}

	/**
	 * ����������������������һ������� MaxHeap
	 * 
	 * @param input
	 *            ��һ����������
	 */
	public CMaxHeap(CHeapNode[] input) {
		// heapSize=input.length;
		array = new CHeapNode[DEFAULT_CAPACITY + 1];
		for (int i = 0; i < input.length; i++) {
			array[i + 1] = input[i];
		}
		HeapSort();
	}

	/**
	 * ���������������������ɶѣ�ʱ�临�Ӷ�ΪO(n)
	 */
	public void buildMaxHeap() {
		//heapSize = array.length;
		// this.array=input;
		/*for (int i = 0; i < input.length; i++) {
			array[i + 1] = input[i];
		}*/
		for (int  i = (int)(heapSize / 2); i >= 1; i--) {
        //  System.out.print("heapSize="+heapSize);
			maxHeapify(i);
		}
		/** ********test begin******** */

		//System.out.println();
		/** ********test end******** */
	}

	/**
	 * �Ե�ǰ�Ķ�����������ʹ���������ѵ����ԣ�ʱ�临�Ӷ�ΪO(logn)
	 * 
	 * @param startIndex
	 *            �ѵ�������ʼλ��
	 */
	public void maxHeapify(int startIndex) {
		int l = startIndex*2;
		int r = startIndex*2+1;
		int largest = startIndex;
		//int largest;
        //System.out.print("startIndex="+startIndex+"--");
		if (l <= heapSize){
            if (array[l].upperSize > array[startIndex].upperSize) 
               largest = l;
		}
		/*******************/
		// else largest=startIndex;
		if (r <= heapSize && array[r].upperSize  > array[largest].upperSize ) {
			largest = r;
		}
		if (largest != startIndex) {
			exchange(startIndex, largest);
			maxHeapify(largest);
		}
	}
    private void HeapAdjust(int nStart,int nEnd){
    	CHeapNode Father=array[nStart];
        //System.out.println("Adajust start="+nStart+"End"+nEnd);
        for (int j=nStart*2;j<=nEnd;j*=2)
        {
        	if (j<nEnd && (array[j].upperSize<array[j+1].upperSize)) 
        		j++;
            if (Father.upperSize>=array[j].upperSize) 
            	break;
            array[nStart]=array[j];nStart=j;
        }
        array[nStart]=Father;
   }

   private void HeapSort(){
   //������
      int i;
      CHeapNode tmp;
      for (i=heapSize/2;i>0;--i)
    	  HeapAdjust(i,heapSize);   //initail the heap
      for (i=heapSize;i>1;i--)
      {
    	  tmp=array[1];
    	  array[1]=array[i];
    	  array[i]=tmp;
          HeapAdjust(1,i-1);
      }
}
	/**
	 * ���������뵽����;T(n)=O(logn)
	 * 
	 * @param val
	 */
	public void insert(CHeapNode val) {
		// �ڶ��м����µĽڵ㣬������ֵ��Ϊ��С������
        //System.out.print("HeapNode Insert : "+val.upperSize);
        //System.out.print("hello");
        heapSize++;                
        array[heapSize] = val;
        buildMaxHeap();
        //System.out.print("Heap:");
        //for(int i=1 ;i< heapSize+1; i++) 
        //	System.out.print(array[i].upperSize+"  ");
        // System.out.println();
	}


	/**
	 * �����Ǵ���ѣ����Ըò������ضѶ�Ԫ��;
	 * 
	 * @return ���е����Ԫ��
	 */
	public double getMaximum() {
		return array[1].upperSize ;
	}
	
	/**
	 * �����Ǵ���ѣ����Ըò������ضѶ�Ԫ�أ�ͬʱ������Ԫ��ǰ�ƣ�T(n)=O(n)
	 * 
	 * @return ���е����Ԫ��
	 */
	
    public CHeapNode removeMax(){
        if (heapSize < 1)
        	throw new NullPointerException("The heap is empty!");
		CHeapNode max = array[1];
		for(int i=1;i<=heapSize-1;i++){
			array[i] = array[i+1];
		}		
		//array[1] = array[heapSize];
        //System.out.println("HeapNode Delete : "+max.upperSize);
		heapSize--;
		this.maxHeapify(1);
        return max;
        }


	/**
	 * @return �ѵĴ�С��������Ԫ�صĸ���
	 */
	public int getSize() {
		return heapSize;
	}
	
	public void printHeap(){
		System.out.println("\n------����ѣ�------");
		for(int i=1;i<=heapSize;i++){
			System.out.println("\n----��"+i+"����ڵ�----");
			System.out.println("livenode leftchild:"+array[i].liveNode.leftChild+",parent:"+array[i].liveNode.parent);
			System.out.println("cliqueSize:"+array[i].cliqueSize+",level:"+array[i].level+",upperSize:"+array[i].upperSize);
		}
		System.out.println("\n------�������------\n");	
	}

	/**
	 * ������������Ԫ�ص�λ��
	 * 
	 * @param index_1
	 * @param index_2
	 */
	private void exchange(int index_1, int index_2) {
		CHeapNode temp = array[index_1];
		array[index_1] = array[index_2];
		array[index_2] = temp;
	}
}
