package MaximumCliqueProblem最大团.clique;
/**
 * 定义了一个大根的二分堆，以及堆上的一些基本操作和堆排序等等
 */
public class CMaxHeap{
	final static int DEFAULT_CAPACITY = 200;// 堆的最大默认存储容量

	CHeapNode[] array;   // 存储堆中的元素的数组

	int heapSize;        // 堆中当前存储的元素的个数，即堆的长度

	/**
	 * 默认构造方法，做一些变量初始化工作
	 */
	public CMaxHeap() {
		heapSize = 0;
		array = new CHeapNode[DEFAULT_CAPACITY + 1];// 第一位不存储元素，其默认值为0
	}

	/**
	 * 利用输入的整数集来构造出一个大根堆 MaxHeap
	 *
	 * @param input
	 *            是一个整型数组
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
	 * 将任意输入的整数集构造成堆；时间复杂度为O(n)
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
	 * 对当前的堆作调整，以使其满足大根堆的特性；时间复杂度为O(logn)
	 *
	 * @param startIndex
	 *            堆调整的起始位置
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
		//堆排序
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
	 * 将整数插入到堆中;T(n)=O(logn)
	 *
	 * @param val
	 */
	public void insert(CHeapNode val) {
		// 在堆中加入新的节点，并将其值设为最小的整数
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
	 * 由于是大根堆，所以该操作返回堆顶元素;
	 *
	 * @return 堆中的最大元素
	 */
	public double getMaximum() {
		return array[1].upperSize ;
	}

	/**
	 * 由于是大根堆，所以该操作返回堆顶元素，同时将其它元素前移，T(n)=O(n)
	 *
	 * @return 堆中的最大元素
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
	 * @return 堆的大小，即堆中元素的个数
	 */
	public int getSize() {
		return heapSize;
	}

	public void printHeap(){
		System.out.println("\n------输出堆：------");
		for(int i=1;i<=heapSize;i++){
			System.out.println("\n----第"+i+"个活节点----");
			System.out.println("livenode leftchild:"+array[i].liveNode.leftChild+",parent:"+array[i].liveNode.parent);
			System.out.println("cliqueSize:"+array[i].cliqueSize+",level:"+array[i].level+",upperSize:"+array[i].upperSize);
		}
		System.out.println("\n------输出结束------\n");
	}

	/**
	 * 交换堆中两个元素的位置
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