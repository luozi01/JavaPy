package syntaxtree;

import visitor.Visitor;

public class ClassVarAssign extends Stm {

    public Exp e1, e2;
    public String id;

    public ClassVarAssign(Exp e1, String id, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.id = id;
    }

    @Override
    public void accept(Visitor v) {
       v.visit(this);
    }
}
