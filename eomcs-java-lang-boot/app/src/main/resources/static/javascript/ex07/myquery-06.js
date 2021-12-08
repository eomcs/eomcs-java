var myQuery = new Object();

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
