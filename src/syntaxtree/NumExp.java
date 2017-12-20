package syntaxtree;

import visitor.Visitor;

public class NumExp extends Exp {

	public int num;

	public NumExp(int num) {
		this.num = num;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
