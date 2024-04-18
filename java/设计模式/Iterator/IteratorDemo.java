
import java.util.Iterator;
/*
 Iterator包含3个方法: hasNext ,  next , remove。
 remove按需求实现，一般它很少用到，
 以至于Eclipse接口方法自动补全时，都忽略了remove放方法。
 */

public class IteratorDemo implements Iterator<Integer>{
	IterableDemo it;
	int index = -1; //下标
	IteratorDemo(IterableDemo it){
		this.it = it;
	}
	
	@Override
	public boolean hasNext() {
		// TODO 判断是否可迭代
		if(index < it.arrgs.length-1)
			return true;
		return false;
	}

	@Override
	public Integer next() {
		// TODO 返回下一个
		index++;
		return it.arrgs[index];
	}

}
