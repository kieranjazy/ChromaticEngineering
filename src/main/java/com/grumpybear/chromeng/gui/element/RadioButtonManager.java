package com.grumpybear.chromeng.gui.element;

public class RadioButtonManager{

   public IconRadioButton button1;
   public IconRadioButton button2;

   public RadioButtonManager(IconRadioButton button1, IconRadioButton button2) {
      this.button1 = button1;
      this.button2 = button2;
   }

   public void switchButtons() {
      if (button1.isActive) {
         button1.setActive(false);
         button2.setActive(true);
      } else {
         button1.setActive(true);
         button2.setActive(false);
      }
   }

}
