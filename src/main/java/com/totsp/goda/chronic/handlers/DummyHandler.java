package com.totsp.goda.chronic.handlers;

import java.util.List;
import com.totsp.goda.chronic.Options;
import com.totsp.goda.chronic.utils.Token;
import com.totsp.goda.time.MutableInterval;


public class DummyHandler implements IHandler {
  public MutableInterval handle(List<Token> tokens, Options options) {
    return null;
  }
}
