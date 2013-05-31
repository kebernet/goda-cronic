package com.totsp.goda.chronic.repeaters;

import com.totsp.goda.chronic.tags.Pointer;
import com.totsp.goda.chronic.tags.Pointer.PointerType;
import com.totsp.goda.chronic.utils.Time;
import com.totsp.goda.time.DateTime;
import com.totsp.goda.time.MutableInterval;




public class RepeaterSecond extends RepeaterUnit {
  public static final int SECOND_SECONDS = 1; // (60 * 60);

  private DateTime _secondStart;

  @Override
  protected MutableInterval _nextMutableInterval(PointerType pointer) {
    int direction = (pointer == Pointer.PointerType.FUTURE) ? 1 : -1;
    if (_secondStart == null) {
      _secondStart = Time.cloneAndAdd(getNow(), Time.SECOND, direction);
    }
    else {
      _secondStart = _secondStart.plusSeconds(direction);
    }

    return new MutableInterval(_secondStart, _secondStart.plusSeconds( 1));
  }

  @Override
  protected MutableInterval _thisMutableInterval(PointerType pointer) {
    return new MutableInterval(getNow(), getNow().plusSeconds(1));
  }

  @Override
  public MutableInterval getOffset(MutableInterval span, double amount, Pointer.PointerType pointer) {
    int direction = (pointer == Pointer.PointerType.FUTURE) ? 1 : -1;
    // WARN: Does not use Calendar
    int seconds = (int) (direction * amount * RepeaterSecond.SECOND_SECONDS);
    return new MutableInterval( span.getStart().plusSeconds(seconds), span.getEnd().plusSeconds(seconds));
  }

  @Override
  public int getWidth() {
    // WARN: Does not use Calendar
    return RepeaterSecond.SECOND_SECONDS;
  }

  @Override
  public String toString() {
    return super.toString() + "-second";
  }
}
