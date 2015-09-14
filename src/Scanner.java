/**
 * The Scanner class itself. Contains only a string for input and an ArrayList of Tokens
 */

import java.util.ArrayList;

public class Scanner {

    String input;
    ArrayList<Token> tokens;

    /**
     * Constructor
     * @param in String to initialize with
     */
    public Scanner(String in) {
        input = in;
        tokens = new ArrayList<Token>();
    }

    /**
     *
     */
    public void scan() {
        String[] raw_tokens;
        raw_tokens = this.input.split("\n|\t|\r| ");

        for (String raw_token : raw_tokens) {
            raw_token = raw_token.trim().trim();
            if (raw_token.equals("+")) {
                tokens.add(new Token(TokenType.Addition));
            } else if (raw_token.contains("-")) {
                tokens.add(new Token(TokenType.Subtraction));
            } else if (raw_token.contains("*")) {
                tokens.add(new Token(TokenType.Multiplication));
            } else if (raw_token.contains("/")) {
                tokens.add(new Token(TokenType.Division));
            } else if (raw_token.contains("%")) {
                tokens.add(new Token(TokenType.Modulus));
            } else if (raw_token.contains("^")) {
                tokens.add(new Token(TokenType.Exponent));
            } else if (raw_token.contains("=")) {
                tokens.add(new Token(TokenType.Assignment));
            } else if (raw_token.toLowerCase().contains("quit")) {
                tokens.add(new Token(TokenType.Quit));
            } else if (raw_token.length() == 1) {
                if (Character.isAlphabetic(raw_token.charAt(0))) {
                    tokens.add(new Token(raw_token.charAt(0)));
                } else if (!Character.isDigit(raw_token.charAt(0))) {
                    tokens.add(new Token(raw_token));
                } else {
                    int j;
                    j = Integer.parseInt(raw_token);
                    tokens.add(new Token(j));
                }
            } else {
                boolean termflag = false;
                int j;
                if (raw_token.contains(";")) {
                    termflag = true;
                    raw_token = raw_token.replace(";", "");
                }
                try {
                    j = Integer.parseInt(raw_token);
                    tokens.add(new Token(j));
                    if (termflag) {
                        tokens.add(new Token(TokenType.Terminator));
                    }
                } catch (NumberFormatException e) {
                    tokens.add(new Token(raw_token));
                }
            }
            if (raw_token.contains(";")) {
                tokens.add(new Token(TokenType.Terminator));
            }
        }
    }

    public void debug() {
        for (Token t : this.tokens) {
            System.out.println(t.toString());
        }
    }
}
