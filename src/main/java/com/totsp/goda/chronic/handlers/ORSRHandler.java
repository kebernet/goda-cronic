package com.totsp.goda.chronic.handlers;

import java.util.List;
import com.totsp.goda.chronic.Options;
import com.totsp.goda.util.CollectionUtils;
import com.totsp.goda.chronic.utils.Token;
import com.totsp.goda.time.MutableInterval;


public class ORSRHandler extends ORRHandler {

  public MutableInterval handle(List<Token> tokens, Options options) {
    MutableInterval outerMutableInterval = Handler.getAnchor( CollectionUtils.subList(tokens,3, 4), options);
    return handle(CollectionUtils.subList(tokens,0, 2), outerMutableInterval, options);
  }

}
