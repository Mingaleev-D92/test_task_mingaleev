import java.io.IOException;
import java.util.Stack;

public class Line {
    private static class Element {
        public final int factor;
        public final StringBuilder sb;

        public Element(int factor) {
            this.factor = factor;
            sb = new StringBuilder();
        }
    }

    ;
    private static Stack<Element> stack = new Stack<Element>();

    public static void main(String... args) {
        int c = -1;
        int factor = 0;
        for (; ; ) {
            try {
                c = System.in.read();
            } catch (IOException e) {
                System.exit(1);
            }
            if (c == -1) {
                break;
            }
            if (Character.isDigit(c)) {
                factor = 10 * factor + Character.getNumericValue(c);
            } else if (c == '[') {
                stack.push(new Element(factor));
                factor = 0;
            } else if (c == ']') {
                Element item = stack.pop();
                String s = item.sb.toString();
                append(item.factor, s);
                factor = 0;
            } else {
                append(factor, Character.toString((char) c));
                factor = 0;
            }
        }
    }

    private static void append(int factor, String s) {
        int n = (factor == 0) ? 1 : factor;
        for (int i = 0; i < n; ++i) {
            if (stack.empty()) {
                System.out.print(s);
            } else {
                stack.peek().sb.append(s);
            }
        }
    }
}