package com.eomcs.net.ex12;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Test {

  public static void main(String[] args) {
    Frame f = new Frame();

    class MyWindowListener extends WindowAdapter {
      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    }

    f.addWindowListener(new MyWindowListener());
    f.setSize(300, 200);
    f.setVisible(true);

  }

}
