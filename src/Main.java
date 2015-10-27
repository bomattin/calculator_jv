// Calculator Compiler for Professor Sorenson's Organization of Programming Languages
// Bret Mattingly, Jr.

public class Main {

    public static void main(String[] args) {
        //System.out.println("Hello World!");
        Scanner s = new Scanner("a = 3; + 5 31; b = + * A 4 - 5 4; quit;");
        s.scan();
        s.debug();
        Parser p = new Parser(s.tokens);
        p.parse();
    }
}
