package syntaxtree;

import visitor.Visitor;

import java.util.ArrayList;

public class If extends Stm {
    public ArrayList<Exp> expList;
    public ArrayList<Stm> stmList;

    public If(ArrayList<Exp> expList, ArrayList<Stm> stmList) {
        this.expList = expList;
        this.stmList = stmList;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
