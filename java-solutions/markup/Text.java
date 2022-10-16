package markup;

public class Text implements Printable {
    private StringBuilder text;

    public Text(String text) {
        this.text = new StringBuilder(text);
    }

    public Text(StringBuilder text) {
        this.text = new StringBuilder(text);
    }

    @Override
    public void toMarkdown(StringBuilder out) {
        out.append(text);
    }
}
