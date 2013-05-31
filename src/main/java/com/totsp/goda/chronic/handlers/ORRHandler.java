package com.totsp.goda.chronic.handlers;

import java.util.List;
import com.totsp.goda.chronic.Options;
import com.totsp.goda.chronic.repeaters.Repeater;
import com.totsp.goda.chronic.tags.Ordinal;
import com.totsp.goda.chronic.tags.Pointer;
import com.totsp.goda.chronic.utils.Time;
import com.totsp.goda.chronic.utils.Token;
import com.totsp.goda.time.MutableInterval;


public abstract class ORRHandler implements IHandler {
  public MutableInterval handle(List<Token> tokens, MutableInterval outerMutableInterval, Options options) {
    Repeater<?> repeater = tokens.get(1).getTag(Repeater.class);
    repeater.setStart(Time.cloneAndAdd(outerMutableInterval.getStart(), Time.SECOND, -1));
    Number ordinalValue = tokens.get(0).getTag(Ordinal.class).getType();
    MutableInterval MutableInterval = null;
    for (int i = 0; i < ordinalValue.intValue(); i++) {
      MutableInterval = repeater.nextMutableInterval(Pointer.PointerType.FUTURE);
      if (MutableInterval.getStartMillis() > outerMutableInterval.getEndMillis()) {
        MutableInterval = null;
        break;
      }
    }
    return MutableInterval;
  }
}
