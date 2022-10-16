package markup;

import java.util.List;

public class Emphasis extends AbstractParagraph {

    public Emphasis(List<Printable> printables) {
        super(printables);
    }

    @Override
    protected void wrapMarks(StringBuilder out) {
        out.append("*");
    }
}
