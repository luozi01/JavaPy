package syntaxtree;
import visitor.Visitor;

public class DictKeys extends Exp {

	public Exp e;

	public DictKeys(Exp e) {
		this.e = e;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
