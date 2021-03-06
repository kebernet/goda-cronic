package com.totsp.goda.chronic.repeaters;

import com.totsp.goda.chronic.tags.Pointer.PointerType;
import com.totsp.goda.time.MutableInterval;



public class RepeaterSeasonName extends Repeater<Object> {
  //  @summer = ['jul 21', 'sep 22']
  //  @autumn = ['sep 23', 'dec 21']
  //  @winter = ['dec 22', 'mar 19']
  //  @spring = ['mar 20', 'jul 20']

  public RepeaterSeasonName(Object type) {
    super(type);
  }

  @Override
  protected MutableInterval _nextMutableInterval(PointerType pointer) {
    throw new IllegalStateException("Not implemented.");
  }

  @Override
  protected MutableInterval _thisMutableInterval(PointerType pointer) {
    throw new IllegalStateException("Not implemented.");
  }

  @Override
  public MutableInterval getOffset(MutableInterval span, double amount, PointerType pointer) {
    throw new IllegalStateException("Not implemented.");
  }

  @Override
  public int getWidth() {
    // WARN: Does not use Calendar
    return (91 * 24 * 60 * 60);
  }

  @Override
  public String toString() {
    return super.toString() + "-season-" + getType();
  }
}
