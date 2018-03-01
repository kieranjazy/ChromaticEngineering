package com.grumpybear.chromeng.block.tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.grumpybear.chromeng.init.ModItems;
import com.grumpybear.chromeng.item.workonlater.ItemBlockDesignator;
import com.grumpybear.chromeng.util.BlockPosUtil;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class TileEnergiser extends TileInventory{

	public ArrayList<BlockPos> blocks;
	public HashMap<EntityPlayer, Boolean> playerStatus = new HashMap<EntityPlayer, Boolean>();
	
	@Override
	public int getSizeInventory() {
		return 1;
	}
	
	@Override
	public void update() {
		ItemStack stack = this.getStackInSlot(0);
		if (stack.getItem() == ModItems.blockDesignator) {
			//ItemBlockDesignator bd = (ItemBlockDesignator)stack.getItem();
			blocks = new ArrayList<BlockPos>();
			
			if (ItemBlockDesignator.getNBT(stack).getKeySet().size() > 0) {
				for (String key : ItemBlockDesignator.getNBT(stack).getKeySet()) {
					blocks.add(BlockPosUtil.longToPos(ItemBlockDesignator.getNBT(stack).getLong(key)));
				}
			}

			if (blocks.size() != 0) {
				for (BlockPos pos : blocks) {
					AxisAlignedBB temp = new AxisAlignedBB(pos);
					temp.offset(0, 1, 0);
					List<EntityPlayer> players = world.getEntitiesWithinAABB(EntityPlayer.class, temp);
					if (players.size() > 0) {
						for (EntityPlayer player : players) {
							playerStatus.put(player, true);
						}
					} else {
						if (!playerStatus.isEmpty()) {
							for (EntityPlayer player : playerStatus.keySet()) {
								if (playerStatus.get(player)) {
									playerStatus.put(player, false);
								}
							}
						}
					}
				}
			}
			
			if (!playerStatus.isEmpty()) {
				for (EntityPlayer player: playerStatus.keySet()) {
					if (!playerStatus.get(player)) {
						player.capabilities.setPlayerWalkSpeed(0.1f);
					} else {
						player.capabilities.setPlayerWalkSpeed(0.5f);
					}
				}
			}
			
		}
	}

}
