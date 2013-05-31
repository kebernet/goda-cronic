package com.totsp.goda.chronic.handlers;

import java.util.List;
import com.totsp.goda.chronic.Options;
import com.totsp.goda.util.CollectionUtils;
import com.totsp.goda.chronic.utils.Token;
import com.totsp.goda.time.MutableInterval;


public class SRPAHandler extends SRPHandler {

  @Override
  public MutableInterval handle(List<Token> tokens, Options options) {
    MutableInterval anchorMutableInterval = Handler.getAnchor(CollectionUtils.subList(tokens,3, tokens.size()), options);
    return super.handle(tokens, anchorMutableInterval, options);
  }

}
