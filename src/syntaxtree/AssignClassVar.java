package syntaxtree;

import visitor.Visitor;

public class AssignClassVar extends Stm {

    Exp e1, e2;
    String id;

    public AssignClassVar(Exp e1, String id, Exp e2) {
        super();
        this.e1 = e1;
        this.e2 = e2;
        this.id = id;
    }

    @Override
    public void accept(Visitor v) {
        // TODO Auto-generated method stub
    }
}
