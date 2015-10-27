import java.util.ArrayList;

public class Parser {


    ArrayList<Token> tokens;
    int pos;

    public Parser(ArrayList<Token> t) {
        tokens = t;
        pos = 0;
    }

    public void parse() {
        while (pos < tokens.size()){
            switch (tokens.get(pos).type) {
                case Variable:
                    System.out.println("Found variable, looking ahead.");
                    this.match_expression_or_var();
                    break;
                case Addition: case Subtraction: case Multiplication: case Division:
                case Modulus: case Exponent:
                    System.out.println("Found operator, matching expression;");
                    this.match_expression();
                    this.match_terminator();
                    break;
                case Integer:
                    System.out.println("Found integer, matching expression;");
                    this.match_expression();
                    break;
                case Quit:
                    System.out.println("Found quit, matching quit;");
                    this.match_quit();
                    this.match_terminator();
                    break;
                default:
                    this.no_match();
                    break;
            }
        }
        System.out.println("Parsed successfully.");
    }

    public void match_expression_or_var() {
        switch(tokens.get(pos+1).type) {
            case Assignment: System.out.println("ass");this.match_variable_assignment(); this.match_terminator(); break;
            default: this.match_expression_or_var(); this.match_terminator(); break;
        }
    }

    public void match_expression() {
        debug();
        switch(tokens.get(pos).type) {
            case Variable: case Integer:
                pos += 1;
                break;
            case Addition: case Subtraction: case Multiplication: case Division:
            case Modulus: case Exponent:
                pos += 1;
                match_expression();
                match_expression();
                break;
            default:
                System.out.println("Syntax error in match_expression().");
        }

    }

    public void match_quit() {
        debug();
        if (tokens.get(pos).type == TokenType.Quit) {
            pos += 1;
        } else {
            System.out.println("Syntax error in match_quit().");
        }

    }

    public void match_variable_assignment() {
        debug();
        switch(tokens.get(pos).type) {
            case Variable:
                if (tokens.get(pos+1).type == TokenType.Assignment) {
                    pos += 2;
                    match_expression();
                    break;
                } else {
                    System.out.println("Syntax error in match_variable_assignment() (early)");
                    break;
                }
            default:
                System.out.println("Syntax error in match_variable_assignment() (late)");
        }
    }

    public void match_terminator() {
        debug();
        switch(tokens.get(pos).type) {
            case Terminator:
                pos +=1;
                break;
            default:
                System.out.println("Syntax error in match_terminator");
        }
    }

    public void no_match() {
        debug();
        System.out.println("Syntax error. No Match.");
        pos = tokens.size();
    }

    public void debug() {
        System.out.println("Current token: " + tokens.get(pos).type);
        System.out.println("Current position: " + pos);
    }
}
