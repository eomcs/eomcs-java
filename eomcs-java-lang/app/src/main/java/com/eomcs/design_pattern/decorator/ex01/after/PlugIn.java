package com.eomcs.design_pattern.decorator.ex01.after;

public abstract class PlugIn extends Printer {
  Printer origin;

  public PlugIn(Printer origin) {
    this.origin = origin;
  }
}
