/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2017
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.ReactorCraft.API;

import net.minecraft.world.World;


/** Implement this on a block to make it shield neutrons. */
public interface NeutronShield {

	/** Return a percentage from 0-100. Args: Neutron Type. Types:

		DECAY: Randomly spat out by fueled cores
		FISSION: Generated by uranium fission reactions
		BREEDER: Generated by fuel breeding reactions
		FUSION: Generated by fusion reactions
		WASTE: Generated by spent fuel decay
		THORIUM: Generated by thorium fission reactions

		Note that values may be clamped such that the absorption chance cannot exceed that of native materials, usually around 95-99%. */
	public double getAbsorptionChance(String type);

	/** Return a double from 0-1 (return is clamped) to modify radiation spawn chances on neutron absorption. 1 is default settings, 0 is no radiation. */
	public double getRadiationSpawnMultiplier(World world, int x, int y, int z, String type);


}
