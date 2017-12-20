package syntaxtree;

import visitor.Visitor;

public abstract class ASTNode {
	public abstract void accept(Visitor v);
}
