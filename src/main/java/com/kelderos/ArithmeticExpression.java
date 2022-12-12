package main.java.com.kelderos;

import java.util.ArrayList;

public class ArithmeticExpression {
    String expression;
    ArrayList<Parameter> parameters;

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression)
    {
        this.expression = expression;
    }

    public ArrayList<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<Parameter> parameters) {
        this.parameters = parameters;
    }
}
