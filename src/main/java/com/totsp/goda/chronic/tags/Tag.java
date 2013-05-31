package com.totsp.goda.chronic.tags;

import java.util.List;
import com.totsp.goda.chronic.Options;
import com.totsp.goda.chronic.utils.Token;
import com.totsp.goda.time.DateTime;


/**
 * Tokens are tagged with subclassed instances of this class when
 * they match specific criteria
 */
public class Tag<T> {
    private DateTime _now;
    private T _type;

    public Tag(T type) {
        _type = type;
    }

    public DateTime getNow() {
        return _now;
    }

    public void setStart(DateTime s) {
        _now = s;
    }

    public void setType(T type) {
        _type = type;
    }

    public T getType() {
        return _type;
    }


    public static interface Scanner {
        List<Token> scan(List<Token> tokens, Options options);
    }

}
