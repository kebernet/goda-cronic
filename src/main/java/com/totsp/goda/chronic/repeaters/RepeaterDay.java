package com.totsp.goda.chronic.repeaters;

import com.totsp.goda.chronic.tags.Pointer;
import com.totsp.goda.chronic.tags.Pointer.PointerType;
import com.totsp.goda.chronic.utils.Time;
import com.totsp.goda.time.DateTime;
import com.totsp.goda.time.MutableInterval;



public class RepeaterDay extends RepeaterUnit {
  public static final int DAY_SECONDS = 86400; // (24 * 60 * 60);

  private DateTime _currentDayStart;

  @Override
  protected MutableInterval _nextMutableInterval(PointerType pointer) {
    if (_currentDayStart == null) {
      _currentDayStart = Time.ymd(getNow());
    }

    int direction = (pointer == Pointer.PointerType.FUTURE) ? 1 : -1;
     _currentDayStart = _currentDayStart.plusDays(direction);

    return new MutableInterval(_currentDayStart, _currentDayStart.plusDays(1));
  }

  @Override
  protected MutableInterval _thisMutableInterval(PointerType pointer) {
    DateTime dayBegin;
    DateTime dayEnd;
    if (pointer == PointerType.FUTURE) {
      dayBegin = Time.cloneAndAdd(Time.ymdh(getNow()), Time.HOUR, 1);
      dayEnd = Time.cloneAndAdd(Time.ymd(getNow()), Time.DAY_OF_MONTH, 1);
    }
    else if (pointer == PointerType.PAST) {
      dayBegin = Time.ymd(getNow());
      dayEnd = Time.ymdh(getNow());
    }
    else if (pointer == PointerType.NONE) {
      dayBegin = Time.ymd(getNow());
      dayEnd = Time.cloneAndAdd(Time.ymdh(getNow()), Time.SECOND, RepeaterDay.DAY_SECONDS);
    }
    else {
      throw new IllegalArgumentException("Unable to handle pointer " + pointer + ".");
    }
    return new MutableInterval(dayBegin, dayEnd);
  }

  @Override
  public MutableInterval getOffset(MutableInterval mutableInterval, double amount, Pointer.PointerType pointer) {
    int direction = (pointer == Pointer.PointerType.FUTURE) ? 1 : -1;
    // WARN: Does not use Calendar
    int seconds = (int) (direction * amount * RepeaterDay.DAY_SECONDS);
    return new MutableInterval( mutableInterval.getStart().plusSeconds(seconds), mutableInterval.getEnd().plusSeconds(seconds));
  }

  @Override
  public int getWidth() {
    // WARN: Does not use Calendar
    return RepeaterDay.DAY_SECONDS;
  }

  @Override
  public String toString() {
    return super.toString() + "-day";
  }
}
