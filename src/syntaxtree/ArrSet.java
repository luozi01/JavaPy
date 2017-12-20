package syntaxtree;

import visitor.Visitor;

public class ArrSet extends Stm {

    public String id;
    public Exp e1, e2;

    public ArrSet(String id, Exp e1, Exp e2) {
        this.id = id;
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
