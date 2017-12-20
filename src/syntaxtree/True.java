package syntaxtree;

import visitor.Visitor;

public class True extends Exp {

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
