package syntaxtree;

import visitor.Visitor;

import java.util.ArrayList;

public class ForLoopExp extends Stm {

    public ArrayList<Exp> expList;
    public Stm body;
    public String var;
    public Exp e;

    public ForLoopExp(ArrayList<Exp> expList, Stm body, String var) {
        this.expList = expList;
        this.body = body;
        this.var = var;
    }

    public ForLoopExp(Exp e, Stm body, String var) {
        this.body = body;
        this.var = var;
        this.e = e;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
