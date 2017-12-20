package syntaxtree;

import visitor.Visitor;

import java.util.Scanner;

public class ReadExp extends Exp {

    public static Scanner sc = new Scanner(System.in);

    public ReadExp() {
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
