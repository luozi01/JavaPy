package syntaxtree;

import visitor.Visitor;

import java.util.ArrayList;

public class Class extends ASTNode {

    public String id;
    public ArrayList<Class> cl;
    public ArrayList<Var> vl;
    public ArrayList<Function> fl;

    public Class(String id, ArrayList<Class> cl, ArrayList<Var> vl, ArrayList<Function> fl) {
        this.id = id;
        this.cl = cl;
        this.vl = vl;
        this.fl = fl;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
