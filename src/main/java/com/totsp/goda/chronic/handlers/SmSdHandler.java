package com.totsp.goda.chronic.handlers;

import java.util.List;

import com.totsp.goda.chronic.Options;
import com.totsp.goda.chronic.tags.ScalarDay;
import com.totsp.goda.chronic.tags.ScalarMonth;
import com.totsp.goda.chronic.utils.Time;
import com.totsp.goda.chronic.utils.Token;
import com.totsp.goda.time.DateTime;
import com.totsp.goda.time.DateTimeFieldType;
import com.totsp.goda.time.MutableInterval;

public class SmSdHandler implements IHandler {
  public MutableInterval handle(List<Token> tokens, Options options) {
    int month = tokens.get(0).getTag(ScalarMonth.class).getType().intValue();
    int day = tokens.get(1).getTag(ScalarDay.class).getType().intValue();
    DateTime start = Time.construct(options.getNow().get(DateTimeFieldType.year()), month, day);
    DateTime end = Time.cloneAndAdd(start, Time.DAY_OF_MONTH, 1);
    return new MutableInterval(start, end);
  }

}
