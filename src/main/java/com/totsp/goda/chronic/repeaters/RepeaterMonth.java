package com.totsp.goda.chronic.repeaters;

import com.totsp.goda.chronic.tags.Pointer;
import com.totsp.goda.chronic.tags.Pointer.PointerType;
import com.totsp.goda.chronic.utils.Time;
import com.totsp.goda.time.DateTime;
import com.totsp.goda.time.MutableInterval;


public class RepeaterMonth extends RepeaterUnit {
  private static final int MONTH_SECONDS = 2592000; // 30 * 24 * 60 * 60

  private DateTime _currentMonthStart;

  @Override
  protected MutableInterval _nextMutableInterval(PointerType pointer) {
    int direction = (pointer == Pointer.PointerType.FUTURE) ? 1 : -1;
    if (_currentMonthStart == null) {
      _currentMonthStart = Time.cloneAndAdd(Time.ym(getNow()), Time.MONTH, direction);
    }
    else {
      _currentMonthStart = Time.cloneAndAdd(_currentMonthStart, Time.MONTH, direction);
    }

    return new MutableInterval(_currentMonthStart, _currentMonthStart.plusMonths(1));
  }

  @Override
  public MutableInterval getOffset(MutableInterval mutableInterval, double amount, Pointer.PointerType pointer) {
    int direction = (pointer == Pointer.PointerType.FUTURE) ? 1 : -1;
    return new MutableInterval(Time.cloneAndAdd(mutableInterval.getStart(), Time.MONTH, (int)(amount * direction)), Time.cloneAndAdd(mutableInterval.getEnd(), Time.MONTH, (int)(amount * direction)));
  }

  @Override
  protected MutableInterval _thisMutableInterval(PointerType pointer) {
    DateTime monthStart;
    DateTime monthEnd;
    if (pointer == PointerType.FUTURE) {
      monthStart = Time.cloneAndAdd(Time.ymd(getNow()), Time.DAY_OF_MONTH, 1);
      monthEnd = Time.cloneAndAdd(Time.ym(getNow()), Time.MONTH, 1);
    }
    else if (pointer == PointerType.PAST) {
      monthStart = Time.ym(getNow());
      monthEnd = Time.ymd(getNow());
    }
    else if (pointer == PointerType.NONE) {
      monthStart = Time.ym(getNow());
      monthEnd = Time.cloneAndAdd(Time.ym(getNow()), Time.MONTH, 1);
    }
    else {
      throw new IllegalArgumentException("Unable to handle pointer " + pointer + ".");
    }
    return new MutableInterval(monthStart, monthEnd);
  }

  @Override
  public int getWidth() {
    // WARN: Does not use Calendar
    return RepeaterMonth.MONTH_SECONDS;
  }

  @Override
  public String toString() {
    return super.toString() + "-month";
  }
}
