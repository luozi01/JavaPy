package syntaxtree;

import visitor.Visitor;

public class AssignStm extends Stm {
    public String id;
    public Exp exp;

    public AssignStm(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
