package syntaxtree;

import visitor.Visitor;

public class Param extends Exp{
	public String id;

	public Param(String id) {
		this.id = id;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}
}
