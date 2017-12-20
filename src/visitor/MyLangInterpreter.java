package visitor;

import syntaxtree.*;
import syntaxtree.Class;

import java.util.*;

public class MyLangInterpreter extends DepthFirstVisitor {

    private HashMap<String, Env> classMap = new HashMap<>();
    private Stack<Env> envStack = new Stack<>(); //recursion backtrack;
    private java.io.PrintStream out = System.out;
    //    private boolean isReturn;
    private Object retVal;
    private String className = "";

    public MyLangInterpreter() {
        envStack.push(new Env());
    }

    public void visit(Program n) {
        for (Class c : n.classList)
            c.accept(this);
        for (Function f : n.funcList)
            f.accept(this);
        for (Stm s : n.stmList)
            s.accept(this);
    }

    /**
     * ArrayList<Class></Class> cl
     * ArrayList<Function></Function> fl
     * ArrayList<Var></Var> vl
     */
    public void visit(Class n) {
        //main class
        if (classMap.containsKey(n.id)) {
            System.err.println(n.id + " class is defined");
            System.exit(7);
        }
        Env classEnv = new Env();
        envStack.push(classEnv);
        for (Var v : n.vl)
            v.accept(this);
        for (Function f : n.fl)
            f.accept(this);
        classMap.put(n.id, classEnv);
        envStack.pop();
        //subclass
        for (Class c : n.cl)
            c.accept(this);
    }

    /**
     * ArrayList<Exp></Exp> el
     */
    public void visit(ClassExp n) {
        if (!classMap.containsKey(n.id)) {
            System.err.println("class application: expected Class, given non-class" + n.id);
            System.exit(7);
        }
        if (!classMap.get(n.id).procMap.containsKey("init")) {
            System.err.println(n.id + "class application: expected Object, given no constructor");
            System.exit(7);
        }
        ProcedureExp exp = new ProcedureExp("init", n.el);
        className = n.id;
        exp.accept(this);
    }

    /**
     * String varName
     * Exp className
     */
    public void visit(ClassVarExp n) {
        n.className.accept(this);
        retVal = classMap.get(retVal).getValue(n.varName);
    }

    /**
     * Exp classExp, funcExp
     */
    public void visit(ClassFuncExp n) {
        n.classExp.accept(this);
        if (!classMap.containsKey(retVal)) {
            System.err.println(retVal + " class application: expected Class, given non-class");
            System.exit(7);
        }
        n.funcExp.accept(this);
    }

    /**
     * ArrayList<Param> pl
     * ArrayList<Stm></Stm> sl
     */
    public void visit(Function n) {
        if (envStack.peek().procMap.containsKey(n.id)) {
            System.err.println(n.id + " method is defined");
            System.exit(7);
        }
        envStack.peek().procMap.put(n.id, n.sl);
        envStack.peek().defAssign(n.id, n.pl);
    }

    /**
     * ArrayList<Exp></Exp> expList
     * String id
     */
    public void visit(ProcedureExp n) {
        if (!className.isEmpty())
            envStack.push(classMap.get(className));
        if (!envStack.peek().procMap.containsKey(n.id)) {
            System.err.println(n.id + " method is not defined");
            System.exit(7);
        }
        @SuppressWarnings("unchecked")
        ArrayList<Param> paramList = (ArrayList<Param>) envStack.peek().getValue(n.id);
        if (paramList.size() != n.expList.size()) {
            System.err.println("procedure expects " + paramList.size() + " arguments, given " + n.expList.size());
            System.exit(8);
        }
        Env ogEnv = new Env(envStack.peek());
        for (int i = 0; i < n.expList.size(); i++) {
            n.expList.get(i).accept(this);
            ogEnv.varMap.put(paramList.get(i).id, retVal);
        }
        ogEnv.procMap = new HashMap<>(envStack.peek().procMap);
        envStack.push(ogEnv);
        ArrayList<Stm> stmList = envStack.peek().procMap.get(n.id);
        for (Stm stm : stmList)
            stm.accept(this);
        envStack.pop();
    }

    /**
     * ArrayList<Stm></Stm> list
     */
    public void visit(CompoundStm n) {
        for (Stm s : n.list)
            s.accept(this);
    }

    /**
     * Exp e
     */
    public void visit(ReturnStm n) {
        n.e.accept(this);
    }

    /**
     * String id;
     * Exp exp;
     */
    public void visit(AssignStm n) {
        n.exp.accept(this);
        envStack.peek().defAssign(n.id, retVal);
    }

    /**
     * Exp e
     * String id
     */
    public void visit(Var n) {
        if (n.exp == null)
            envStack.peek().varMap.put(n.id, null);
        else {
            n.exp.accept(this);
            if (n.isObject)
                envStack.peek().varMap.put(n.id, className);
            else
                envStack.peek().varMap.put(n.id, retVal);
        }
    }

    /**
     * ArrayList<Exp></Exp> list;
     */
    public void visit(PrintStm n) {
        for (Exp e : n.expList) {
            e.accept(this);
            out.print(retVal + " ");
        }
        out.println();
    }

    /**
     * ArrayList<Exp></Exp> expList;
     * ArrayList<Stm></Stm> stmList;
     */
    public void visit(If n) {
        boolean bool = false;
        for (int i = 0; i < n.expList.size(); i++) {
            n.expList.get(i).accept(this);
            if ((Boolean) retVal) {
                n.stmList.get(i).accept(this);
                bool = true;
                break;
            }
        }
        if (!bool && n.expList.size() < n.stmList.size())
            n.stmList.get(n.stmList.size() - 1).accept(this);
    }

    /**
     * String id;
     */
    public void visit(IdExp n) {
        try {
            retVal = envStack.peek().getValue(n.id);
        } catch (RuntimeException e) {
            System.err.println("reference to undefined identifier: " + n.id);
            System.exit(4);
        }
    }

    /**
     * int i;
     */
    public void visit(NumExp n) {
        retVal = n.num;
    }

    /**
     * Exp e1;
     * String op;
     * Exp e2;
     */
    @SuppressWarnings({"unchecked"})
    public void visit(OpExp n) {
        n.e1.accept(this);
        Object o1 = retVal;
        n.e2.accept(this);
        Object o2 = retVal;
        if (n.op.equals("+") && o1 instanceof List<?> && o2 instanceof List<?>) {
            ((List<Object>) o1).addAll((List<Object>) o2);
            retVal = o1;
            return;
        }
        if (!(o1 instanceof Integer) || !(o2 instanceof Integer)) {
            System.err.println(n.op + ": expects type <integer> as arguments");
            System.exit(3);
        }
        int i1 = (Integer) o1;
        int i2 = (Integer) o2;
        if (n.op.equals("/") && i2 == 0) {
            System.err.println("quotient: undefined for 0");
            System.exit(2);
        }
        switch (n.op) {
            case "+":
                retVal = i1 + i2;
                break;
            case "-":
                retVal = i1 - i2;
                break;
            case "*":
                retVal = i1 * i2;
                break;
            case "/":
                retVal = i1 / i2;
                break;
            case "%":
                retVal = i1 % i2;
                break;
            case "**":
                retVal = (int) Math.pow(i1, i2);
                break;
            default:
                throw new RuntimeException("Invalid arithmetic operator: " + n.op);
        }
    }

    /**
     * Exp e1,e2
     * String id
     */
    @Override
    public void visit(Compare n) {
        n.e1.accept(this);
        Object i1 = retVal;
        n.e2.accept(this);
        Object i2 = retVal;
        if (!(i1 instanceof Integer) || !(i2 instanceof Integer)) {
            System.err.println(n.op + ": expects type <integer> as arguments");
            System.exit(3);
        }
        int v1 = (Integer) i1;
        int v2 = (Integer) i2;
        switch (n.op) {
            case "<":
                retVal = v1 < v2;
                break;
            case "<=":
                retVal = (v1 <= v2);
                break;
            case ">=":
                retVal = (v1 >= v2);
                break;
            default:
                retVal = v1 > v2;
                break;
        }
    }

    /**
     * Exp exp
     */
    @Override
    public void visit(Not n) {
        n.exp.accept(this);
        Object o = retVal;
        if (o instanceof Boolean)
            retVal = !(Boolean) retVal;
        else {
            System.err.println("Expected a boolean type.");
            System.exit(1);
        }
    }

    /**
     * Exp e1,e2
     * String id
     */
    @Override
    public void visit(Equal n) {
        n.e1.accept(this);
        Object o1 = retVal;
        n.e2.accept(this);
        Object o2 = retVal;
        if (n.id.equals("==")) {
            if (o1 == null && o2 == null) {
                retVal = true;
            }
            if (o1 == null || o2 == null) {
                retVal = false;
            }
            retVal = o1 == o2 || Objects.equals(o1, o2);
        } else if (n.id.equals("!=")) {
            if (o1 == null && o2 == null) {
                retVal = false;
            }
            if (o1 == null || o2 == null) {
                retVal = true;
            }
            retVal = o1 != o2 && !Objects.equals(o1, o2);
        }
    }

    /**
     * Exp e1,e2
     * String id
     */
    @Override
    public void visit(LogExp n) {
        n.e1.accept(this);
        Object i1 = retVal;
        n.e2.accept(this);
        Object i2 = retVal;
        if (!(i1 instanceof Boolean) || !(i2 instanceof Boolean)) {
            System.err.println(n.op + ": expects type <Boolean> as arguments");
            System.exit(3);
        }
        if (n.op.equals("and"))
            retVal = (Boolean) i1 && (Boolean) i2;
        else
            retVal = (Boolean) i1 || (Boolean) i2;
    }

    @Override
    public void visit(NegInt n) {
        n.exp.accept(this);
        retVal = -(Integer) retVal;
    }

    @Override
    public void visit(Null n) {
        retVal = null;
    }

    @Override
    public void visit(True n) {
        retVal = Boolean.TRUE;
    }

    @Override
    public void visit(False n) {
        retVal = Boolean.FALSE;
    }

    public void visit(ReadExp n) {
        retVal = ReadExp.sc.nextInt();
    }

    /**
     * ArrayList<Exp></Exp> list
     */
    @Override
    public void visit(ArrDefine n) {
        ArrayList<Object> list = new ArrayList<>();
        for (Exp e : n.list) {
            e.accept(this);
            list.add(retVal);
        }
        retVal = list;
    }

    /**
     * String id
     * Exp e
     */
    @SuppressWarnings("unchecked")
    @Override
    public void visit(ArrAdd n) {
        if (!envStack.peek().isDefine(n.id)) {
            System.err.println("cannot add to " + n.id + ", undefined array");
            System.exit(3);
        }
        n.e.accept(this);
        ((ArrayList<Object>) envStack.peek().getValue(n.id)).add(retVal);
    }

    /**
     * Exp e1,e2
     */
    @SuppressWarnings("unchecked")
    @Override
    public void visit(ArrContains n) {
        n.e1.accept(this);
        Object list = retVal;
        n.e2.accept(this);
        retVal = ((ArrayList<Object>) list).contains(retVal);
    }

    /**
     * Exp e1,e2
     */
    @SuppressWarnings("unchecked")
    @Override
    public void visit(ArrIndexOf n) {
        n.e1.accept(this);
        Object list = retVal;
        n.e2.accept(this);
        Object o = retVal;
        retVal = ((ArrayList<Object>) list).indexOf(o);
    }

    /**
     * Exp e1,e2
     * String id
     */
    @SuppressWarnings("unchecked")
    @Override
    public void visit(ArrInsert n) {
        n.e1.accept(this);
        Object o1 = retVal;
        if (!(o1 instanceof Integer)) {
            System.err.println("Index expect to be Integer");
            System.exit(3);
        }
        n.e2.accept(this);
        Object o2 = retVal;
        if (!envStack.peek().isDefine(n.id)) {
            System.err.println("cannot insert to " + n.id + ",undefine array");
            System.exit(3);
        }
        ((ArrayList<Object>) envStack.peek().getValue(n.id)).add((Integer) o1, o2);
    }

    /**
     * Exp e1,e2
     */
    @Override
    public void visit(ArrGet n) {
        n.e1.accept(this);
        Object list = retVal;
        n.e2.accept(this);
        Object o = retVal;
        if (!(o instanceof Integer)) {
            System.err.println("index expects to be Integer");
            System.exit(3);
        }
        retVal = ((ArrayList<?>) list).get((Integer) o);
    }

    /**
     * Exp e
     */
    @SuppressWarnings("unchecked")
    @Override
    public void visit(ArrLength n) {
        n.e.accept(this);
        retVal = ((ArrayList<Object>) retVal).size();
    }

    /**
     * Exp e1,e2
     */
    @SuppressWarnings("unchecked")
    @Override
    public void visit(ArrSet n) {
        if (!envStack.peek().isDefine(n.id)) {
            System.err.println("cannot set to " + n.id + ", undefined array");
            System.exit(3);
        }
        n.e1.accept(this);
        Object ve1 = retVal;
        n.e2.accept(this);
        Object ve2 = retVal;
        if (!(ve1 instanceof Integer)) {
            System.err.println("index expects type <integer> as arguments");
            System.exit(3);
        }
        ((ArrayList<Integer>) envStack.peek().getValue(n.id)).set((Integer) ve1, (Integer) ve2);
    }

    /**
     * Exp e
     */
    @SuppressWarnings("unchecked")
    public void visit(ArrRemoveFirst n) {
        n.e.accept(this);
        if (((ArrayList<Object>) retVal).isEmpty()) {
            System.err.println("cannot remove from empty array");
            System.exit(2);
        }
        retVal = ((ArrayList<Object>) retVal).remove(0);
    }

    /**
     * Exp e
     */
    @SuppressWarnings("unchecked")
    public void visit(ArrEmpty n) {
        n.e.accept(this);
        retVal = ((ArrayList<Object>) retVal).isEmpty();
    }

    public void visit(Dict n) {
        retVal = new HashMap<Integer, ArrayList<Integer>>();
    }

    /**
     * Exp e1,e2
     */
    @SuppressWarnings("unchecked")
    public void visit(DictGet n) {
        n.e1.accept(this);
        Object map = retVal;
        n.e2.accept(this);
        if (!(retVal instanceof Integer)) {
            System.err.println("index expects type <integer> as arguments");
            System.exit(3);
        }
        retVal = ((HashMap<Integer, ArrayList<Integer>>) map).get(retVal);
    }

    /**
     * Exp e
     */
    @SuppressWarnings("unchecked")
    public void visit(DictKeys n) {
        n.e.accept(this);
        retVal = new ArrayList<>(((HashMap<Integer, ArrayList<Integer>>) retVal).keySet());
    }

    /**
     * Exp e1,e2,e3
     */
    @SuppressWarnings("unchecked")
    @Override
    public void visit(DictPut n) {
        n.e1.accept(this);
        Integer key = (Integer) retVal;
        if (!((HashMap<Integer, ArrayList<Integer>>) envStack.peek().getValue(n.id)).containsKey(key))
            ((HashMap<Integer, ArrayList<Integer>>) envStack.peek().getValue(n.id)).put(key, new ArrayList<>());
        n.e2.accept(this);
        if (!(retVal instanceof Integer)) {
            System.err.println("value expects type <integer> as arguments");
            System.exit(3);
        }
        ((HashMap<Integer, ArrayList<Integer>>) envStack.peek().getValue(n.id)).get(key).add((Integer) retVal);
    }

    /**
     * Stm body
     * Exp start,end, list
     */
    @SuppressWarnings("unchecked")
    public void visit(ForLoopExp n) {
        if (envStack.peek().isDefine(n.var)) {
            System.err.println("variable is defined");
            System.exit(1);
        }
        if (n.e == null) {
            if (n.expList.size() > 3 || n.expList.isEmpty()) {
                System.err.println("Invalid loop");
                System.exit(1);
            }
            Object[] o = new Object[n.expList.size()];
            for (int i = 0; i < o.length; i++) {
                n.expList.get(i).accept(this);
                if (!(retVal instanceof Integer)) {
                    System.err.println("syntax error: invalid for loop, not iterable");
                    System.exit(1);
                }
                o[i] = retVal;
            }
            int start, end, increment;
            if (o.length == 1) {
                start = 0;
                end = (Integer) o[0];
                increment = 1;
            } else if (o.length == 2) {
                start = (Integer) o[0];
                end = (Integer) o[1];
                increment = 1;
            } else {
                start = (Integer) o[0];
                end = (Integer) o[1];
                increment = (Integer) o[2];
            }
            envStack.peek().defAssign(n.var, start);
            for (int i = start; i < end; i += increment) {
                envStack.peek().defAssign(n.var, i);
                n.body.accept(this);
            }
        } else {
            n.e.accept(this);
            if (!(retVal instanceof List<?>)) {
                System.err.println("syntax error: list not iterable");
                System.exit(1);
            }
            envStack.peek().defAssign(n.var, null);
            for (Object o : (ArrayList<Object>) retVal) {
                envStack.peek().defAssign(n.var, o);
                n.body.accept(this);
            }
        }
        envStack.peek().varMap.remove(n.var);
    }

    /**
     * Exp bool
     * Stm body
     */
    @Override
    public void visit(WhileExp n) {
        n.bool.accept(this);
        if (retVal instanceof Boolean) {
            while (retVal == Boolean.TRUE) {
                n.body.accept(this);
                n.bool.accept(this);
            }
            return;
        }
        System.err.println("expects type <boolean> as condition");
        System.exit(1);
    }
}
