package ch29;

import org.springframework.context.ApplicationContext;

public class SpringUtils {
  public static void printObjects(ApplicationContext ctx) {
    String[] names = ctx.getBeanDefinitionNames();
    for (String name : names) {
      System.out.printf("%s : %s\n", 
          name,
          ctx.getBean(name).getClass().getName());
    }
  }
  
  public static void printTypes(ApplicationContext ctx) {
    String[] names = ctx.getBeanDefinitionNames();
    for (String name : names) {
      System.out.printf("%s : %s\n", name, ctx.getType(name));
    }
  }
}
