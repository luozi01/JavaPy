package syntaxtree;

import visitor.Visitor;

public class False extends Exp {
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
