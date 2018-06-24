package MaximumCliqueProblem最大团.clique;

public  class CHeapNode{
        CBBnode liveNode;    //��Խ��
        int upperSize;       //���ļ�ֲ�Ͻ�
        int cliqueSize;      //�ŵĶ�����
        int level;           //�������Ӽ����������Ĳ����
        
        //���췽��
        CHeapNode(CBBnode node,int up,int size,int lev){
            liveNode=node;
            upperSize=up;
            cliqueSize=size;
            level=lev;
            //System.out.println("liveNode="+liveNode+" upperSize="+upperSize+" cliqueSize="+cliqueSize+" level="+level);
        }

    }

