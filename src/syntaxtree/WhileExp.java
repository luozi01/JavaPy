package syntaxtree;

import visitor.Visitor;

public class WhileExp extends Stm {

	public Exp bool;
	public Stm body;

	public WhileExp(Exp bool, Stm body) {
		super();
		this.bool = bool;
		this.body = body;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
