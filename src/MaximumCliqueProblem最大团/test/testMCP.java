package MaximumCliqueProblem最大团.test;

import MaximumCliqueProblem最大团.clique.BBClique;

public class testMCP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int[] bestx=new int[4];
		BBClique BBC = new BBClique();
		long start=System.nanoTime();
		int bestn= BBC.maxClique();
		long end=System.nanoTime();
		long s=(end-start)/1000;
		System.out.println("图的邻接矩阵：");
		BBC.printa();
		System.out.println("\n最大团顶点数为："+bestn);
		BBC.printb();
		System.out.println("\n运行时间："+s+"微秒");
	}

}
