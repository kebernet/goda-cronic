package com.totsp.goda.chronic.handlers;

import com.totsp.goda.chronic.Options;
import com.totsp.goda.chronic.repeaters.Repeater;
import com.totsp.goda.chronic.tags.Tag;
import com.totsp.goda.chronic.utils.Time;
import com.totsp.goda.chronic.utils.Token;

import com.totsp.goda.time.DateTime;
import com.totsp.goda.time.DateTimeFieldType;
import com.totsp.goda.time.MutableInterval;

import java.util.List;


public abstract class MDHandler implements IHandler {
    public MutableInterval handle(Repeater<?> month, Tag<? extends Number> day, List<Token> timeTokens, Options options) {
        month.setStart(new DateTime(options.getNow().getMillis()));

        MutableInterval mutableInterval = month.thisMutableInterval(options.getContext());
        DateTime dayStart = Time.construct(
                mutableInterval.getStart().get(DateTimeFieldType.year()),
                mutableInterval.getStart().get(DateTimeFieldType.monthOfYear()) , day.getType().intValue());

        return Handler.dayOrTime(dayStart, timeTokens, options);
    }
}
