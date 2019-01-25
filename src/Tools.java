import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tools {

  public void promptInput(List<Dish> menu) {
    System.out.println("欢迎进入--吃什么鸭--点餐系统\n\n今日菜单：");
    printMenu(menu);
    System.out.println("\n请输入您需要的菜品和数量（例如：黄焖鸡 1，冰峰 1）：");
  }

  public List<Dish> loadMenu() {
    List<Dish> menu = new ArrayList<Dish>();
    menu.add(new Dish("ITEM0001", "黄焖鸡", 18.00, true));
    menu.add(new Dish("ITEM0013", "肉夹馍", 6.00, false));
    menu.add(new Dish("ITEM0022", "凉皮", 8.00, true));
    menu.add(new Dish("ITEM0030", "冰锋", 2.00, false));
    return menu;
  }

  public void printMenu(List<Dish> menu) {
    for (Dish dish : menu) {
      System.out.println(dish.name + "--" + dish.price + "元");
    }
  }

  public String readUserInput() {
    InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
    try {
      String userInput = bufferedReader.readLine();
      return userInput;
    } catch (IOException e) {
      System.out.println("Exception encountered: " + e);
      return null;
    }
  }

  public Map parseInput(String userInput) {
    Map orderedDishes = new HashMap();
    if (userInput == null || userInput.isEmpty()) {
      System.out.println("请输入您需要的菜品和数量（例如：黄焖鸡 1，冰峰 1）：");
      userInput = readUserInput();
      orderedDishes = parseInput(userInput);
    } else {
      String[] inputArray = userInput.split(",|，");
      for (String item : inputArray) {
        String[] itemInfor = item.split(" ");
        if (itemInfor.length == 2 && itemInfor[0] != "" && itemInfor[1].matches("^\\d*$")) {
          String dishName = itemInfor[0];
          int dishCount = Integer.parseInt(itemInfor[1]);
          orderedDishes.put(dishName, dishCount);
        } else {
          System.out.println("输入格式有误，请重新输入您需要的菜品和数量（例如：黄焖鸡 1，冰峰 1）：");
          userInput = readUserInput();
          orderedDishes = parseInput(userInput);
        }
      }
    }
    return orderedDishes;
  }

}