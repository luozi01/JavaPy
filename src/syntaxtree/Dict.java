package syntaxtree;
import visitor.Visitor;

public class Dict extends Exp {

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
