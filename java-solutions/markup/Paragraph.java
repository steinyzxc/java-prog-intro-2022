package markup;

import java.util.List;

public class Paragraph extends AbstractParagraph {

    public Paragraph(List<Printable> printables) {
        super(printables);
    }

    @Override
    protected void wrapMarks(StringBuilder out) {
    }
}
