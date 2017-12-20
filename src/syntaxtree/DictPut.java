package syntaxtree;
import visitor.Visitor;

public class DictPut extends Stm {

	public String id;
	public Exp e1,e2;


	public DictPut(String id, Exp e1, Exp e2) {
		this.e1 = e1;
		this.e2 = e2;
		this.id = id;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
