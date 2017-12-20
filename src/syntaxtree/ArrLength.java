package syntaxtree;

import visitor.Visitor;

public class ArrLength extends Exp {

    public Exp e;

    public ArrLength(Exp e) {
        this.e = e;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
