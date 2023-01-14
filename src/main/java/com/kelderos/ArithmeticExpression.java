package com.kelderos;

import java.util.ArrayList;

public class ArithmeticExpression {
    ArrayList<String> expressions;
    ArrayList<Parameter> parameters;

    public ArrayList<String> getExpressions() {
        return expressions;
    }

    public void setExpressions(ArrayList<String> expression)
    {
        this.expressions = expression;
    }

    public ArrayList<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<Parameter> parameters) {
        this.parameters = parameters;
    }
}
