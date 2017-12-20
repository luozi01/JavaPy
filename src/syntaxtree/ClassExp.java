package syntaxtree;

import visitor.Visitor;

import java.util.ArrayList;

public class ClassExp extends Exp {

    public String id;
    public ArrayList<Exp> el;


    public ClassExp(String id, ArrayList<Exp> el) {
        this.id = id;
        this.el = el;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
