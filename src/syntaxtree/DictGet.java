package syntaxtree;
import visitor.Visitor;

public class DictGet extends Exp {

	public Exp e1,e2;

	public DictGet(Exp e1,Exp e2) {
		this.e2 = e2;
		this.e1 = e1;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}