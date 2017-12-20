package syntaxtree;

import java.util.ArrayList;
import java.util.HashMap;

public class Env {
    public HashMap<String, ArrayList<Stm>> procMap = new HashMap<>();
    public HashMap<String, Object> varMap = new HashMap<>();
    private Env nextEnv = null;

    public Env(Env nextEnv) {
        this.nextEnv = nextEnv;
    }

    public Env() {
    }

    public Object getValue(String varName) {
        if (varMap.containsKey(varName))
            return varMap.get(varName);
        else if (nextEnv == null)
            throw new RuntimeException("Undefined identifier: " + varName);
        else
            return nextEnv.getValue(varName);
    }

    private void assign(String varName, Object value) {
        if (varMap.containsKey(varName))
            varMap.put(varName, value);
        else if (nextEnv == null)
            throw new RuntimeException("cannot assign to undefined identifier: " + varName);
        else
            nextEnv.assign(varName, value);
    }

    public void defAssign(String varName, Object value) {
        try {
            assign(varName, value);
        } catch (RuntimeException e) {
            varMap.put(varName, value);
        }
    }

    public boolean isDefine(String id) {
        return varMap.containsKey(id);
    }
}
