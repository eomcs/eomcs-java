var myQuery = function(selector) {
  var el = [];

  if (selector.startsWith("<")) { // <h1>  => 태그 생성할 경우
    var tagName = selector.substr(1, selector.length - 2);
    var e = document.createElement(tagName);
    el.push(e);
  } else { // 태그를 찾을 경우
    var nodeList = document.querySelectorAll(selector);
    for (var node of nodeList) {
      el.push(node);
    }
  }

  // 이벤트 리스너를 등록시킨다.
  el.on = function(events, handler) {
    for (var e of el) {
      e.addEventListener(events, handler);
    }
  };

  // click 이벤트 리스너를 등록시킨다.
  el.click = function(handler) {
    el.on("click", handler);
  };

  // 태그의 콘텐트를 설정한다.
  el.html = function(htmlString) {
    for (var e of el) {
      e.innerHTML = htmlString;
    }
    return el;
  };

  // 배열에 들어 있는 태그를 target 부모의 자식으로 추가한다.
  el.appendTo = function(target) {
    for (var e of el) {
      target.appendChild(e);
    }
    return el;
  };

  return el;
};

myQuery.ajax = function(p1, p2) {
  if (typeof p1 == "string") {
    if (arguments.length == 1) {
      var settings = {};
      settings.url = p1;
    } else if (arguments.length == 2) {
      var settings = p2;
      settings.url = p1;
    }
  } else {
    var settings = p1;
  }

  if (!settings.method) { // undefined
    settings.method = "GET";
  }

  if (!settings.dataType) {
    settings.dataType = "text";
  }

  if (!settings.contentType) {
    settings.contentType = "application/x-www-form-urlencoded; charset=UTF-8";
  }

  var xhr = new XMLHttpRequest();
  xhr.onreadystatechange = () => {
      if (xhr.readyState == 4) {
          if (xhr.status == 200) {
            if (settings.success) {
              if (settings.dataType == "json") {
                settings.success(JSON.parse(xhr.responseText));
              } else {
                settings.success(xhr.responseText);
              }
            }
          } else {
            if (settings.error) {
              settings.error();
            }
          }
      }
  };
  xhr.open(settings.method, settings.url, true);

  if (settings.method == "GET") {
    xhr.send();
  } else if (settings.method == "POST") {
    xhr.setRequestHeader("Content-Type", settings.contentType);
    xhr.send(this.toQueryString(settings.data));
  }
};

myQuery.toQueryString = function(obj) {
  var qs = "";
  if (obj != null) {
    for (var propName in obj) {
      if (qs.length > 0) {
        qs += "&";
      }
      qs += propName + "=" + obj[propName];
    }
  }
  return qs;
};

myQuery.getJSON = function(p1, p2, p3) {
  var settings = {
    url: p1,
    dataType: "json"
  };

  if (typeof p2 == "function") {
    settings.success = p2;
  }

  if (typeof p2 == "object") {
    settings.data = p2;
  }

  if (p3) {
    settings.success = p3;
  }

  this.ajax(settings);
};

var $ = myQuery;
