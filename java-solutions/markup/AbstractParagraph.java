package markup;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractParagraph implements Printable {

    private final List<Printable> printables;

    protected AbstractParagraph(List<Printable> printables) {
        this.printables = new ArrayList<Printable>(printables);
    }

    protected abstract void wrapMarks(StringBuilder out);

    @Override
    public void toMarkdown(StringBuilder out) {
        wrapMarks(out);
        for (Printable printable : printables) {
            printable.toMarkdown(out);
        }
        wrapMarks(out);
    }
}
