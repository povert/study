package strategy;
/*
 * ����ģʽ����һϵ���㷨������ʹ�����ǿ����໥�滻��
 * ��ģʽʹ���㷨���Զ�����ʹ�����ǵĿͻ��˶��仯
 */
// UMLͼ
/*
 *  CashContext{ <>--strategy------------> CashSuper{acceptCash()}
 *  GetResult()o--->strategy.acceptCash}           |
 *   }          ---------------------------------<ʵ��>------------
 *              |                    |                            |
 *        CashDiscount           CashNormal                  CashReturn
 *        {acceptCash()}         {acceptCash()}             {acceptCash()}                         
 */ 
 
public class Strategy {
	public static void main(String[] args) {
		double money = 976.0;
		CashContext shop = new CashContext(TYPE.NORMAL);
		System.out.println("�����շ�:"+shop.GetResult(money));
		shop = new CashContext(TYPE.CASH_DISCOUNT);
		System.out.println("�ۿ��շ�:"+shop.GetResult(money));
		shop = new CashContext(TYPE.CASH_RETURN);
		System.out.println("�����շ�:"+shop.GetResult(money));
		
	}
}
/* �������Գ�������Ϊ��������������Ϊ3�֣����������ۣ����Ż�*/
interface CashSuper{
	double acceptCash(double money);
}
enum TYPE{NORMAL,CASH_DISCOUNT,CASH_RETURN}

//�����շ�
class CashNormal implements CashSuper{
	@Override
	public double acceptCash(double money) {
		// TODO �Զ����ɵķ������
		return money;
	} 
}
//�ۿ�
class CashDiscount implements CashSuper{
	private double moneyDiscount;
	CashDiscount(double moneyDiscount){this.moneyDiscount=moneyDiscount;}
	@Override
	public double acceptCash(double money) {
		// TODO �Զ����ɵķ������
		return money*moneyDiscount;
	} 
}
// ����
class CashReturn implements CashSuper{
	private double moneyCondition;
	private double moneyReturn;
	
	CashReturn(double moneyCondition,double moneyReturn){
		this.moneyCondition = moneyCondition;
		this.moneyReturn = moneyReturn;
		}
	@Override
	public double acceptCash(double money) {
		// TODO �Զ����ɵķ������
		double result = money;
		if(money>=moneyCondition)
			result = money - Math.floor(money/moneyCondition)*moneyReturn;
		return result;
	} 
}

//����
class CashContext{
	private CashSuper cs;
	private TYPE t;
	CashContext(TYPE t){
		switch (t) {
		case NORMAL: cs = new CashNormal();
			break;
		case CASH_DISCOUNT: cs = new CashDiscount(0.8);//��8��
			break;
		case CASH_RETURN: cs = new CashReturn(300, 100);//��300����100
			break;
		}
	}
	public double GetResult(double money) {
		return cs.acceptCash(money);
	}
}
