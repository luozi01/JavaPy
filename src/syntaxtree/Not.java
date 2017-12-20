package syntaxtree;

import visitor.Visitor;

public class Not extends Exp {
    public Exp exp;

    public Not(Exp exp) {
        this.exp = exp;
    }

    @Override
    public void accept(Visitor V) {
        V.visit(this);
    }
}
