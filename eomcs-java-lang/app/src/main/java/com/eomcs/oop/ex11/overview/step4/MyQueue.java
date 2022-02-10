package com.eomcs.oop.ex11.overview.step4;

public class MyQueue extends MyList {

  public void offer(Object obj) {
    this.add(obj);
  }

  public Object poll() {
    if (size == 0) {
      return null;
    }
    return remove(0);
  }

  @Override
  public Iterator iterator() {
    return new QueueIterator(this);
  }

  static class QueueIterator implements Iterator {

    MyQueue queue;

    public QueueIterator(MyQueue queue) {
      this.queue = queue;
    }

    @Override
    public boolean hasNext() {
      return queue.size() > 0;
    }

    @Override
    public Object next() {
      return queue.poll();
    }
  }
}











