package syntaxtree;

import visitor.Visitor;

public class NegInt extends Exp {

    public Exp exp;

    public NegInt(Exp exp) {
        this.exp = exp;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
