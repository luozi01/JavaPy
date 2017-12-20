package syntaxtree;

import visitor.Visitor;

public class Null extends Exp {

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
