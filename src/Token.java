public class Token {
    TokenType type;
    char char_val;
    int int_val;
    String string_val;

    public Token(TokenType t) {
        type = t;
    }

    public Token(char c) {
        type = TokenType.Variable;
        char_val = c;
    }

    public Token(int i) {
        type = TokenType.Integer;
        int_val = i;
    }

    public Token(String s) {
        type = TokenType.Unknown;
        string_val = s;
    }

    public String toString() {
        if (type == TokenType.Integer) {
            return type.toString() + ": " + int_val;
        } else if (type == TokenType.Variable) {
            return type.toString() + ": " + char_val;
        } else if (type == TokenType.Unknown) {
            return type.toString() + ": " + string_val;
        } else {
            return type.toString();
        }
    }
}

enum TokenType {
    Integer,
    Addition,
    Subtraction,
    Multiplication,
    Division,
    Modulus,
    Exponent,
    Assignment,
    Terminator,
    Quit,
    Variable,
    Unknown,
}

