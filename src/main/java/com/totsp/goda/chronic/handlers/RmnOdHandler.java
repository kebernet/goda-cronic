package com.totsp.goda.chronic.handlers;

import com.totsp.goda.chronic.Options;
import com.totsp.goda.chronic.repeaters.RepeaterMonthName;
import com.totsp.goda.chronic.tags.OrdinalDay;
import com.totsp.goda.util.CollectionUtils;
import com.totsp.goda.chronic.utils.Token;

import com.totsp.goda.time.MutableInterval;

import java.util.List;


public class RmnOdHandler extends MDHandler {
    public MutableInterval handle(List<Token> tokens, Options options) {
        return handle(
            tokens.get(0).getTag(RepeaterMonthName.class), tokens.get(1).getTag(OrdinalDay.class),
            CollectionUtils.subList(tokens, 2, tokens.size()), options);
    }
}
