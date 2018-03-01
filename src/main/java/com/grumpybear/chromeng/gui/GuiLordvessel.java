package com.grumpybear.chromeng.gui;

import com.grumpybear.chromeng.block.tile.TileBonfire;
import com.grumpybear.chromeng.gui.container.ContainerLordvessel;
import com.grumpybear.chromeng.gui.element.Button;
import com.grumpybear.chromeng.gui.element.Element;
import com.grumpybear.chromeng.gui.element.IconButton;
import com.grumpybear.chromeng.gui.element.LordvesselButton;
import com.grumpybear.chromeng.init.ModItems;
import com.grumpybear.chromeng.item.ItemLordvessel;
import com.grumpybear.chromeng.network.ChromEngPacketHandler;
import com.grumpybear.chromeng.network.MessageCEChange;
import com.grumpybear.chromeng.network.MessageNBTLordvessel;
import com.grumpybear.chromeng.network.MessageTeleport;
import com.grumpybear.chromeng.util.BlockPosUtil;
import com.grumpybear.chromeng.util.ItemStackUtil;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

/**
 * Created by Kieran on 6/29/2017.
 */
public class GuiLordvessel extends GuiCE{
   private final String texLoc = "chromaticengineering:textures/gui/lordvessel.png";
   private final ResourceLocation GUI_LOCATION = new ResourceLocation(texLoc);

   private GuiTextField textField;

   private EntityPlayer player;

   private LordvesselButton selected;

   private ItemStack stack;

   public GuiLordvessel(IInventory playerInv) {
      super(new ContainerLordvessel(playerInv));

      this.xSize = 176;
      this.ySize = 166;


      player = ((InventoryPlayer) playerInv).player;
      stack = player.getHeldItemMainhand();
   }

   @Override
   public void initGui() {
      super.initGui();

      this.textField = new GuiTextField(0, fontRendererObj, this.guiLeft + 100, this.guiTop + 20, 68, 15);
      this.textField.setTextColor(-1);
      this.textField.setDisabledTextColour(-1);
      this.textField.setEnableBackgroundDrawing(false);
      this.textField.setMaxStringLength(10);

      initTeleportButtons(stack);

      addElement(new Button(27, 18, this.guiLeft + 100, this.guiTop + 62, this, texLoc, 0, 166, 0, 184, "Link"));
      addElement(new Button(27, 18, this.guiLeft + 142, this.guiTop + 62, this, texLoc, 0, 166, 0, 184, "Erase"));


   }

   public void initTeleportButtons(ItemStack stack) { //Sync itemstack with server
      int count = 0;

      for (int i = 1; i <= 4; i++) {
         for (int j = 1; j <= 3; j++) {
            if (ItemStackUtil.getNBT(stack).getBoolean("Set" + count)) {
               NBTTagCompound nbt = ItemStackUtil.getNBT(stack);
               addElement(new LordvesselButton(this.guiLeft + 9 + (22 * (i - 1)), this.guiTop + 9 + (24 * (j - 1)), new IconButton.IconReturn(nbt.getInteger("IconX" + count), nbt.getInteger("IconY" + count)), this, nbt.getString("Name" + count), BlockPosUtil.getFromNBT(nbt, count)));
               count++;
            }
         }
      }
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      drawDefaultBackground();
      super.drawScreen(mouseX, mouseY, partialTicks);
      fontRendererObj.drawString("Name:", this.guiLeft + 99, this.guiTop + 7, 16579836);
   }

   @Override
   protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
      GlStateManager.color(1.0f,  1.0f,  1.0f, 1.0f);

      this.mc.getTextureManager().bindTexture(GUI_LOCATION);

      this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

      super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);

      this.textField.drawTextBox();
   }

   @Override
   protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
      super.drawGuiContainerForegroundLayer(mouseX, mouseY);

      if (selected != null) {
         this.textField.setText(selected.name);
         fontRendererObj.drawString("X:" + selected.pos.getX() + " Y:" + selected.pos.getY() + " Z:" + selected.pos.getZ(), 100, 40, 4210752);
      }
   }

   @Override
   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      for (Element element : elements) {
         if (element.intersectsWith(mouseX, mouseY)) {
            if (element instanceof Button) {
               if (element instanceof LordvesselButton) {
                  selected = (LordvesselButton) element;
               } else if (selected != null && (stack.getItem() == ModItems.lordvessel || stack.getItem() == ModItems.darksign)) {
                  if (((Button) element).buttonString == "Link") {
                     if (player.world.getTileEntity(selected.pos) instanceof TileBonfire && (((TileBonfire) player.world.getTileEntity(selected.pos)).isBurning()) && ((ItemLordvessel) stack.getItem()).minusCE(stack)) {
                        ChromEngPacketHandler.INSTANCE.sendToServer(new MessageTeleport(selected.pos));
                        ChromEngPacketHandler.INSTANCE.sendToServer(new MessageCEChange(MessageCEChange.OPS.MINUS, ((ItemLordvessel) stack.getItem()).getCECost()));
                        this.mc.displayGuiScreen(null);
                     }
                  } else if (((Button) element).buttonString == "Erase") {
                     shiftIconsBack(selected);
                     break;
                  }
               }
            }
         }
      }
   }

   public void shiftIconsBack(LordvesselButton button) {
      if (selected != null) {
         int index = elements.indexOf(button);
         int count = -1;

         for (Element element : elements) {
            if (element instanceof LordvesselButton)
               count++;
         }

         ((ItemLordvessel) stack.getItem()).shiftNBTData(index, count, stack);
         ChromEngPacketHandler.INSTANCE.sendToServer(new MessageNBTLordvessel(index, count));
         initGui();
         selected = null;
      }
   }



}
