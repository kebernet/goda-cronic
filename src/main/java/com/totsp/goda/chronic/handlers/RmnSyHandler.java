package com.totsp.goda.chronic.handlers;

import java.util.List;
import com.totsp.goda.chronic.Options;
import com.totsp.goda.chronic.repeaters.RepeaterMonthName;
import com.totsp.goda.chronic.tags.ScalarYear;
import com.totsp.goda.chronic.utils.Time;
import com.totsp.goda.chronic.utils.Token;
import com.totsp.goda.time.DateTime;
import com.totsp.goda.time.MutableInterval;



public class RmnSyHandler implements IHandler {

  public MutableInterval handle(List<Token> tokens, Options options) {
    int month = tokens.get(0).getTag(RepeaterMonthName.class).getType().ordinal();
    int year = tokens.get(1).getTag(ScalarYear.class).getType().intValue();

    MutableInterval MutableInterval;
    try {
      DateTime start = Time.construct(year, month);
      DateTime end = Time.cloneAndAdd(start, Time.MONTH, 1);
      MutableInterval = new MutableInterval(start, end);
    }
    catch (IllegalArgumentException e) {
      if (options.isDebug()) {
        e.printStackTrace(System.out);
      }
      MutableInterval = null;
    }

    return MutableInterval;
  }

}
