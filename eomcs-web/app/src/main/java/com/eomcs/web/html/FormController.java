package com.eomcs.web.html;

import java.util.HashMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FormController {

  @RequestMapping("/html/form/exam01")
  public Object exam01() {
    return "Hello!";
  }

  @RequestMapping("/html/form/exam02")
  public Object exam02(String name, int age) {
    HashMap<String,Object> map = new HashMap<>();
    map.put("name", name);
    map.put("age", age);

    return map; 
  }
}
