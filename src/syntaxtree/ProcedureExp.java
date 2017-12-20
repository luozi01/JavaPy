package syntaxtree;

import visitor.Visitor;

import java.util.ArrayList;

public class ProcedureExp extends Exp {
    public String id;
    public ArrayList<Exp> expList;

    public ProcedureExp(String id, ArrayList<Exp> expList) {
        this.id = id;
        this.expList = expList;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
