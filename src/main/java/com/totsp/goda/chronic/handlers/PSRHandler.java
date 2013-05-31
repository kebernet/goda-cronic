package com.totsp.goda.chronic.handlers;

import java.util.LinkedList;
import java.util.List;
import com.totsp.goda.chronic.Options;
import com.totsp.goda.chronic.utils.Token;
import com.totsp.goda.time.MutableInterval;


public class PSRHandler extends SRPHandler {

  @Override
  public MutableInterval handle(List<Token> tokens, Options options) {
    List<Token> newTokens = new LinkedList<Token>();
    newTokens.add(tokens.get(1));
    newTokens.add(tokens.get(2));
    newTokens.add(tokens.get(0));
    return super.handle(newTokens, options);
  }

}
