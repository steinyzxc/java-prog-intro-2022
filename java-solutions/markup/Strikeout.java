package markup;

import java.util.List;

public class Strikeout extends AbstractParagraph {

    public Strikeout(List<Printable> printables) {
        super(printables);
    }


    @Override
    protected void wrapMarks(StringBuilder out) {
        out.append("~");
    }
}
