package com.kwpugh.easy_steel.items.steel;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SteelArrow extends ArrowItem
{
	   public SteelArrow(Item.Properties builder)
	   {
	      super(builder);
	   }

	   public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter)
	   {
	      ArrowEntity arrowentity = new ArrowEntity(worldIn, shooter);
	      arrowentity.setPotionEffect(stack);
	      return arrowentity;
	   }

	   public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.PlayerEntity player)
	   {
	      int enchant = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.enchantment.Enchantments.INFINITY, bow);
	      return enchant <= 0 ? false : this.getClass() == SteelArrow.class;
	   }
	}