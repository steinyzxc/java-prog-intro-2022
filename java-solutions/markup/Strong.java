package markup;

import java.util.List;

public class Strong extends AbstractParagraph {

    public Strong(List<Printable> printables) {
        super(printables);
    }


    @Override
    protected void wrapMarks(StringBuilder out) {
        out.append("__");
    }
}
