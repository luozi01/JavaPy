package syntaxtree;

import visitor.Visitor;

public class ArrRemoveFirst extends Exp {

    public Exp e;

    public ArrRemoveFirst(Exp e) {
        this.e = e;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
