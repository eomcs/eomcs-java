function myQuery(p) {
  let e = document.querySelector(p);

  e.val = function(v) {
    if (v == undefined) {
      return e.value;
    } else {
      e.value = v;
    }
  };

  e.on = function(eventName, listener) {
    e.addEventListener(eventName, listener);
  };

  e.click = function(listener) {
    if (listener) {
      e.on('click', listener);
    } else {
      e.dispatchEvent(new MouseEvent('click'));
    }
  };

  return e;
}


let $ = myQuery;