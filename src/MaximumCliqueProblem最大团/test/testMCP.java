package MaximumCliqueProblem最大团.test;

import clique.BBClique;

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
		System.out.println("ͼ���ڽӾ���");
		BBC.printa();
	    System.out.println("\n����Ŷ�����Ϊ��"+bestn); 
	    BBC.printb();
	    System.out.println("\n����ʱ�䣺"+s+"΢��");  
	}

}
