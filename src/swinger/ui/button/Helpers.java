package swinger.ui.button;

import java.util.function.Consumer;

import swinger.loader.Loader;

public class Helpers {
  public static Consumer<Button> setText(String text){
    return b->b.text = text;
  }

  public static Consumer<Button> setNormalBg(String path){
    return b->b.normalBgImage = Loader.loadImageIcon(path);
  }

  public static Consumer<Button> setHorizontalAligment(float aligment){
    return b->b.setAlignmentX(aligment);
  }
}
