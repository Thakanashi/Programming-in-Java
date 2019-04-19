
public class ListElement {

    private String name;
    private String expression;

    public ListElement(String n, String e){

        this.name = n;
        this.expression = e;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getExpression() {
        return expression;
    }
}

