package Iterator;
import java.util.Iterator;
/*
 Iterator����3������: hasNext ,  next , remove��
 remove������ʵ�֣�һ���������õ���
 ������Eclipse�ӿڷ����Զ���ȫʱ����������remove�ŷ�����
 */

public class IteratorDemo implements Iterator<Integer>{
	IterableDemo it;
	int index = -1; //�±�
	IteratorDemo(IterableDemo it){
		this.it = it;
	}
	
	@Override
	public boolean hasNext() {
		// TODO �ж��Ƿ�ɵ���
		if(index < it.arrgs.length-1)
			return true;
		return false;
	}

	@Override
	public Integer next() {
		// TODO ������һ��
		index++;
		return it.arrgs[index];
	}

}
