package example;

import swinger.router.Router;

public class Example {
  public static void main(String[] args) {
    Router router = Router.getInstance();

    router.register("Menu", MenuPanel::new);
    router.navigate("Menu");
  }
}
