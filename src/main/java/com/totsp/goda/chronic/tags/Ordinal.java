package com.totsp.goda.chronic.tags;

import com.totsp.goda.chronic.Options;
import com.totsp.goda.chronic.utils.Token;

import java.util.List;


public class Ordinal extends Tag<Number> {

    public static final Scanner SCANNER = new Scanner() {

        public List<Token> scan(List<Token> tokens, Options options) {
            return Ordinal.scan(tokens, options);
        }

    };

    public static String ORDINAL_PATTERN = "^(\\d*)(st|nd|rd|th)$";

    public Ordinal(Integer type) {
        super(type);
    }

    public static List<Token> scan(List<Token> tokens, Options options) {
        for (Token token : tokens) {
            Ordinal t;
            t = Ordinal.scan(token, options);

            if (t != null) {
                token.tag(t);
            }

            t = OrdinalDay.scan(token);

            if (t != null) {
                token.tag(t);
            }
        }

        return tokens;
    }

    public static Ordinal scan(Token token, Options options) {
       
        if (token.getWord().matches(ORDINAL_PATTERN)) {
            System.out.println("Ordinal: "+token.getWord().substring(0, token.getWord().length() - 2));
            return new Ordinal(Integer.valueOf(token.getWord().substring(0, token.getWord().length() - 2)));
        }

        return null;
    }

    @Override
    public String toString() {
        return "ordinal";
    }
}
