package com.totsp.goda.chronic.repeaters;

import com.totsp.goda.chronic.tags.Pointer;
import com.totsp.goda.chronic.tags.Pointer.PointerType;
import com.totsp.goda.chronic.utils.Time;
import com.totsp.goda.time.DateTime;
import com.totsp.goda.time.MutableInterval;

public class RepeaterMinute extends RepeaterUnit {
  public static final int MINUTE_SECONDS = 60;

  private DateTime _currentMinuteStart;

  @Override
  protected MutableInterval _nextMutableInterval(PointerType pointer) {
    if (_currentMinuteStart == null) {
      if (pointer == PointerType.FUTURE) {
        _currentMinuteStart = Time.cloneAndAdd(Time.ymdhm(getNow()), Time.MINUTE, 1);
      }
      else if (pointer == PointerType.PAST) {
        _currentMinuteStart = Time.cloneAndAdd(Time.ymdhm(getNow()), Time.MINUTE, -1);
      }
      else {
        throw new IllegalArgumentException("Unable to handle pointer " + pointer + ".");
      }
    }
    else {
      int direction = (pointer == Pointer.PointerType.FUTURE) ? 1 : -1;
      _currentMinuteStart=_currentMinuteStart.plusMinutes( direction);
    }
    
    return new MutableInterval(_currentMinuteStart, _currentMinuteStart.plusSeconds( RepeaterMinute.MINUTE_SECONDS));
  }

  @Override
  protected MutableInterval _thisMutableInterval(PointerType pointer) {
    DateTime minuteBegin;
    DateTime minuteEnd;
    if (pointer == Pointer.PointerType.FUTURE) {
      minuteBegin = getNow();
      minuteEnd = Time.ymdhm(getNow());
    }
    else if (pointer == Pointer.PointerType.PAST) {
      minuteBegin = Time.ymdhm(getNow());
      minuteEnd = getNow();
    }
    else if (pointer == Pointer.PointerType.NONE) {
      minuteBegin = Time.ymdhm(getNow());
      minuteEnd = Time.cloneAndAdd(Time.ymdhm(getNow()), Time.SECOND, RepeaterMinute.MINUTE_SECONDS);
    }
    else {
      throw new IllegalArgumentException("Unable to handle pointer " + pointer + ".");
    }
    return new MutableInterval(minuteBegin, minuteEnd);
  }

  @Override
  public MutableInterval getOffset(MutableInterval span, double amount, Pointer.PointerType pointer) {
    int direction = (pointer == Pointer.PointerType.FUTURE) ? 1 : -1;
    // WARN: Does not use Calendar
    int seconds = (int) (direction * amount * RepeaterMinute.MINUTE_SECONDS);
    return new MutableInterval( span.getStart().plusSeconds(seconds), span.getEnd().plusSeconds(seconds));
  }

  @Override
  public int getWidth() {
    // WARN: Does not use Calendar
    return RepeaterMinute.MINUTE_SECONDS;
  }

  @Override
  public String toString() {
    return super.toString() + "-minute";
  }
}
