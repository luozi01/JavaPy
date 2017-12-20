package syntaxtree;

import visitor.Visitor;

import java.util.ArrayList;

public class Function extends ASTNode {
    public String id;
    public ArrayList<Param> pl;
    public ArrayList<Stm> sl;

    public Function(String id, ArrayList<Param> pl, ArrayList<Stm> sl) {
        this.id = id;
        this.pl = pl;
        this.sl = sl;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
