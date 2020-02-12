package com.kwpugh.easy_steel.items.steel;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.kwpugh.easy_steel.init.ItemInit;
import com.kwpugh.easy_steel.util.HammerUtil;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SteelExcavator extends ShovelItem
{
	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.GRASS_BLOCK, Blocks.GRASS_PATH, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.RED_SAND, Blocks.SAND, Blocks.PODZOL, Blocks.GRAVEL);
	
	public static final Set<Material> EFFECTIVE_MATERIALS = ImmutableSet.of(Material.EARTH);

	public SteelExcavator(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder)
	{
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}

 
    public boolean onBlockDestroyed(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity entity)
    {
        stack.attemptDamageItem(1, random, null);

        if (entity instanceof PlayerEntity)
        {
        	HammerUtil.attemptBreakNeighbors(world, pos, (PlayerEntity) entity, EFFECTIVE_ON, EFFECTIVE_MATERIALS);
        }
        return super.onBlockDestroyed(stack, world, state, pos, entity);
    }

    
	@Override
	public int getBurnTime(ItemStack itemStack)
	{
		return 400;
	}
	
	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
	{
		return true;
	}
    
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
	{
		return repair.getItem() == ItemInit.STEEL_INGOT.get();
	}
	
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.easy_steel.excavator").applyTextStyle(TextFormatting.GREEN)));
	}
}
