package com.totsp.goda.chronic.handlers;

import java.util.List;
import com.totsp.goda.chronic.Options;
import com.totsp.goda.chronic.repeaters.RepeaterMonthName;
import com.totsp.goda.chronic.tags.ScalarDay;
import com.totsp.goda.util.CollectionUtils;
import com.totsp.goda.chronic.utils.Token;
import com.totsp.goda.time.MutableInterval;


public class RmnSdHandler extends MDHandler {
  public MutableInterval handle(List<Token> tokens, Options options) {
    return handle(tokens.get(0).getTag(RepeaterMonthName.class), tokens.get(1).getTag(ScalarDay.class), CollectionUtils.subList(tokens,2, tokens.size()), options);
  }
}
