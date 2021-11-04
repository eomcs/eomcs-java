var myQuery = new Object();

myQuery.ajax = function(url, success, fail) {
  var xhr = new XMLHttpRequest();
  xhr.onreadystatechange = () => {
      if (xhr.readyState == 4) {
          if (xhr.status == 200) {
            success(xhr.responseText);
          } else {
            fail();
          }
      }
  };
  xhr.open("GET", url, true);
  xhr.send();
};
