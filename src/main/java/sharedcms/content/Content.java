package sharedcms.content;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import sharedcms.audio.BlockSound;
import sharedcms.audio.DSound;
import sharedcms.audio.SFX;
import sharedcms.base.AresBlockShrub;
import sharedcms.base.AresNaturalLeavesBlock;
import sharedcms.content.block.*;
import sharedcms.content.effect.EffectDust;
import sharedcms.content.effect.EffectFallingLeaf;
import sharedcms.content.effect.EffectFirefly;
import sharedcms.content.item.ItemCrusader;
import sharedcms.content.item.ItemGladius;
import sharedcms.content.item.ItemSpada;
import sharedcms.content.structure.brush.Brush;
import sharedcms.content.structure.brush.Brushable;
import sharedcms.content.structure.brush.IBrush;
import sharedcms.content.structure.brush.IBrushBuilder;
import sharedcms.content.structure.model.IModel;
import sharedcms.content.structure.model.IModelBuilder;
import sharedcms.content.structure.model.IModelMaterial;
import sharedcms.content.structure.model.Model;
import sharedcms.content.structure.model.ModelMaterial;
import sharedcms.content.structure.model.SmoothingMode;
import sharedcms.content.tab.TabNatural;
import sharedcms.content.world.biome.BiomeCanopy;
import sharedcms.content.world.biome.BiomeDesert;
import sharedcms.content.world.biome.BiomeForest;
import sharedcms.content.world.biome.BiomeMountains;
import sharedcms.content.world.biome.BiomePlains;
import sharedcms.content.world.biome.BiomeRiver;
import sharedcms.content.world.generator.WorldGeneratorEmpty;
import sharedcms.content.world.type.WorldTypeAres;
import sharedcms.controller.shared.ContentController;
import sharedcms.fx.BlockEffect;
import sharedcms.fx.SimpleBlockEffect;
import sharedcms.registry.IRegistrant;
import sharedcms.util.GEN;
import sharedcms.util.GList;
import sharedcms.util.Location;
import sharedcms.util.M;
import sharedcms.util.SimplexProperties;
import sharedcms.voxel.VoxelRegistry;
import sharedcns.api.biome.BiomeEffectType;
import sharedcns.api.biome.BiomeSimplexEffect;
import sharedcns.api.biome.LeveledBiome;
import sharedcns.api.biome.LudicrousBiome;

public class Content implements IRegistrant
{
	public static int biomeid = 0;
	private static GList<LeveledBiome> biomeLevels = new GList<LeveledBiome>();
	private static GList<BiomeSimplexEffect> biomeEffects = new GList<BiomeSimplexEffect>();

	public static int nextBiomeId()
	{
		return biomeid++;
	}

	public static class Tab
	{
		public static TabNatural NATURAL = new TabNatural("tnatural");
		public static TabNatural SHRUBS = new TabNatural("tshrubs");
		public static TabNatural WEAPONS = new TabNatural("tweapons");

		public static void s()
		{

		}
	}

	public static class Block
	{
		public static BlockColdSandstone COLD_SANDSTONE = new BlockColdSandstone("sandstone", Material.rock, Sound.STONE);
		public static BlockColdSandstoneBrick COLD_SANDSTONE_BRICK = new BlockColdSandstoneBrick("sandstone_brick", Material.rock, Sound.STONE);
		public static BlockPlanksDark PLANKS_DARK = new BlockPlanksDark("plank_spruce", Material.wood, Sound.WOOD);
		public static BlockPlanksOak PLANKS_OAK = new BlockPlanksOak("planks_oak", Material.wood, Sound.WOOD);
		public static BlockPlanksAcacia PLANKS_ACACIA = new BlockPlanksAcacia("planks_acacia", Material.wood, Sound.WOOD);
		public static BlockPlanksBirch PLANKS_BIRCH = new BlockPlanksBirch("planks_birch", Material.wood, Sound.WOOD);
		public static BlockPlanksJungle PLANKS_JUNGLE = new BlockPlanksJungle("plank_jungle", Material.wood, Sound.WOOD);
		public static BlockLamp LAMP = new BlockLamp("redstone_lamp", Material.glass, Sound.GLASS);
		public static BlockMoldedStone MOLDED_STONE = new BlockMoldedStone("molded_stone", Material.rock, Sound.STONE);
		public static BlockLapisBlock LAPIS_BLOCK = new BlockLapisBlock("lapis_block", Material.rock, Sound.STONE);
		public static BlockIceSmooth IICE_SMOOTH = new BlockIceSmooth("ice", Material.rock, Sound.STONE);
		public static BlockIceSmoothFrame ICE_SMOOTH_FRAME = new BlockIceSmoothFrame("frosted_ice_frame_smooth", Material.rock, Sound.STONE);
		public static BlockIceScratchedFrame ICE_SCRATCHED_FRAME = new BlockIceScratchedFrame("frosted_ice_frame_scratched", Material.rock, Sound.STONE);
		public static BlockIceCrushedFrame ICE_CRUSHED_FRAME = new BlockIceCrushedFrame("frosted_ice_crushed_frame", Material.rock, Sound.STONE);
		public static BlockIceFrame ICE_FRAME = new BlockIceFrame("frosted_ice_frame", Material.rock, Sound.STONE);
		public static BlockCrispyBrick CRISPY_BRICK = new BlockCrispyBrick("cripsy_brick", Material.rock, Sound.STONE);
		public static BlockCrispyPile CRISPY_PILE = new BlockCrispyPile("cripsy_pile", Material.ground, Sound.GRAVEL);
		public static BlockCharredBrick CHARRED_BRICK = new BlockCharredBrick("charred_brick", Material.rock, Sound.STONE);
		public static BlockCharredChisled CHARRED_CHISLED = new BlockCharredChisled("charred_brick_chisled", Material.rock, Sound.STONE);
		public static BlockCharredEarth CHARRED_EARTH = new BlockCharredEarth("charred_earth", Material.rock, Sound.STONE);
		public static BlockCharredStone CHARRED_STONE = new BlockCharredStone("charred_stone", Material.rock, Sound.STONE);
		public static BlockCharredStoneSurface CHARRED_STONE_SURFACE = new BlockCharredStoneSurface("charred_stone_surface", Material.rock, Sound.STONE);
		public static BlockFlowerLotusBlack FLOWER_LOTUS_BLACK = new BlockFlowerLotusBlack("flower_lotus_black", Material.plants, Sound.GRASS, 0);
		public static BlockColdGrass COLD_GRASS = new BlockColdGrass("grass", Material.grass, Sound.GRASS);
		public static BlockColdDirt COLD_DIRT = new BlockColdDirt("dirt", Material.ground, Sound.GRAVEL);
		public static BlockColdStone COLD_STONE = new BlockColdStone("cold_stone", Material.rock, Sound.STONE);
		public static Blockfb BLOCK_FB = new Blockfb("blockfb", Material.rock, Sound.STONE);
		public static BlockPathStoneBrick PATH_STONE_BRICK = new BlockPathStoneBrick("path_stonebrick", Material.rock, Sound.STONE);
		public static BlockPathStoneBrickCracked PATH_STONE_BRICK_CRACKED = new BlockPathStoneBrickCracked("path_stonebrick_cracked", Material.rock, Sound.STONE);
		public static BlockPathStoneBrickCrushed PATH_STONE_BRICK_CRUSHED = new BlockPathStoneBrickCrushed("path_stonebrick_crushed", Material.rock, Sound.STONE);
		public static BlockPathGravelWarm PATH_GRAVEL_WARM = new BlockPathGravelWarm("path_gravel_warm", Material.ground, Sound.GRAVEL);
		public static BlockPathGravelRough PATH_GRAVEL_ROUGH = new BlockPathGravelRough("path_gravel_rough", Material.ground, Sound.GRAVEL);
		public static BlockPathSand PATH_SAND = new BlockPathSand("path_sand", Material.sand, Sound.SAND);
		public static BlockPathStoneFrame PATH_STONE_FRAME = new BlockPathStoneFrame("path_stone_frame", Material.rock, Sound.STONE);
		public static BlockPathStone PATH_STONE = new BlockPathStone("path_stone", Material.rock, Sound.STONE);
		public static BlockSmoothDirt SMOOTH_DIRT = new BlockSmoothDirt("smooth_dirt", Material.ground, Sound.GRAVEL);
		public static BlockRiverStone RIVER_STONE = new BlockRiverStone("river_stone", Material.rock, Sound.STONE);
		public static BlockDeadBush DEAD_BUSH = new BlockDeadBush("dead_bush", Material.plants, Sound.GRASS, 0);
		public static BlockDeadTwig DEAD_TWIG = new BlockDeadTwig("dead_twig", Material.plants, Sound.GRASS, 0);
		public static BlockTallGrass TALL_GRASS = new BlockTallGrass("tall_grass", Material.plants, Sound.GRASS, 0);
		public static BlockThinGrass THIN_GRASS = new BlockThinGrass("thin_grass", Material.plants, Sound.GRASS, 0);
		public static BlockStubbedGrass STUBBED_GRASS = new BlockStubbedGrass("stubbed_grass", Material.plants, Sound.GRASS, 0);
		public static BlockSpokedGrass SPOKED_GRASS = new BlockSpokedGrass("spoked_grass", Material.plants, Sound.GRASS, 0);
		public static BlockFern FERN = new BlockFern("fern", Material.plants, Sound.GRASS, 0);
		public static BlockShortGrass SHORT_GRASS = new BlockShortGrass("short_grass", Material.plants, Sound.GRASS, 0);
		public static BlockFlowerDaisy FLOWER_DAISY = new BlockFlowerDaisy("flower_daisy", Material.plants, Sound.GRASS, 4);
		public static BlockFlowerAllium FLOWER_ALLIUM = new BlockFlowerAllium("flower_allium", Material.plants, Sound.GRASS, 2);
		public static BlockFlowerBlueOrchid FLOWER_BLUE_ORCHID = new BlockFlowerBlueOrchid("flower_blue_orchid", Material.plants, Sound.GRASS, 2);
		public static BlockFlowerDandelion FLOWER_DANDELION = new BlockFlowerDandelion("flower_dandelion", Material.plants, Sound.GRASS, 6);
		public static BlockFlowerHoustonia FLOWER_HOUSTONIA = new BlockFlowerHoustonia("flower_houstonia", Material.plants, Sound.GRASS, 4);
		public static BlockFlowerPaeonia FLOWER_PAEONIA = new BlockFlowerPaeonia("flower_paeonia", Material.plants, Sound.GRASS, 3);
		public static BlockFlowerRose FLOWER_ROSE = new BlockFlowerRose("flower_rose", Material.plants, Sound.GRASS, 8);
		public static BlockFlowerTulipBlue FLOWER_TULIP_BLUE = new BlockFlowerTulipBlue("flower_tulip_blue", Material.plants, Sound.GRASS, 7);
		public static BlockFlowerTulipCyan FLOWER_TULIP_CYAN = new BlockFlowerTulipCyan("flower_tulip_cyan", Material.plants, Sound.GRASS, 7);
		public static BlockFlowerTulipMagenta FLOWER_TULIP_MAGENTA = new BlockFlowerTulipMagenta("flower_tulip_magenta", Material.plants, Sound.GRASS, 7);
		public static BlockFlowerTulipPurple FLOWER_TULIP_PURPLE = new BlockFlowerTulipPurple("flower_tulip_purple", Material.plants, Sound.GRASS, 7);
		public static BlockFlowerTulipYellow FLOWER_TULIP_YELLOW = new BlockFlowerTulipYellow("flower_tulip_yellow", Material.plants, Sound.GRASS, 7);
		public static BlockFlowerTulipOrange FLOWER_TULIP_ORANGE = new BlockFlowerTulipOrange("flower_tulip_orange", Material.plants, Sound.GRASS, 7);
		public static BlockFlowerTulipPink FLOWER_TULIP_PINK = new BlockFlowerTulipPink("flower_tulip_pink", Material.plants, Sound.GRASS, 6);
		public static BlockFlowerTulipRed FLOWER_TULIP_RED = new BlockFlowerTulipRed("flower_tulip_red", Material.plants, Sound.GRASS, 7);
		public static BlockFlowerTulipWhite FLOWER_TULIP_WHITE = new BlockFlowerTulipWhite("flower_tulip_white", Material.plants, Sound.GRASS, 5);
		public static BlockLogArid LOG_ARID = new BlockLogArid("log_arid", Material.wood, Sound.WOOD);
		public static BlockLogOak LOG_OAK = new BlockLogOak("log_oak", Material.wood, Sound.WOOD);
		public static BlockLogMossy LOG_MOSSY = new BlockLogMossy("log_mossy", Material.wood, Sound.WOOD);
		public static BlockLogBirch LOG_BIRCH = new BlockLogBirch("log_birch", Material.wood, Sound.WOOD);
		public static BlockLogDarkOak LOG_DARK = new BlockLogDarkOak("log_dark", Material.wood, Sound.WOOD);
		public static BlockLogSpruce LOG_SPRUCE = new BlockLogSpruce("log_spruce", Material.wood, Sound.WOOD);
		public static BlockLogJungle LOG_JUNGLE = new BlockLogJungle("log_jungle", Material.wood, Sound.WOOD);
		public static BlockLogAcacia LOG_ACACIA = new BlockLogAcacia("log_acacia", Material.wood, Sound.WOOD);
		public static BlockLogRedwood LOG_REDWOOD = new BlockLogRedwood("log_redwood", Material.wood, Sound.WOOD);
		public static BlockLogGlacial LOG_GLACIAL = new BlockLogGlacial("log_glacial", Material.wood, Sound.WOOD);
		public static BlockLeavesCherryBlossom LEAVES_CHERRY_BLOSSOM = new BlockLeavesCherryBlossom("leaves_cherry_blossom", Material.leaves, Sound.GRASS);
		public static BlockLeavesOak LEAVES_OAK = new BlockLeavesOak("leaves_oak", Material.leaves, Sound.GRASS);
		public static BlockLeavesDarkOak LEAVES_DARK = new BlockLeavesDarkOak("leaves_dark", Material.leaves, Sound.GRASS);
		public static BlockLeavesSpruce LEAVES_SPRUCE = new BlockLeavesSpruce("leaves_spruce", Material.leaves, Sound.GRASS);
		public static BlockLeavesJungle LEAVES_JUNGLE = new BlockLeavesJungle("leaves_jungle", Material.leaves, Sound.GRASS);
		public static BlockLeavesAcacia LEAVES_ACACIA = new BlockLeavesAcacia("leaves_acacia", Material.leaves, Sound.GRASS);
		public static BlockLeavesRedwood LEAVES_REDWOOD = new BlockLeavesRedwood("leaves_redwood", Material.leaves, Sound.GRASS);
		public static BlockLeavesGlacial LEAVES_GLACIAL = new BlockLeavesGlacial("leaves_glacial", Material.leaves, Sound.GRASS);
		public static BlockAridStone ARID_STONE = new BlockAridStone("arid_stone", Material.rock, Sound.STONE);
		public static BlockAridSand ARID_SAND = new BlockAridSand("arid_sand", Material.sand, Sound.SAND);
		public static BlockGlacialGrass GLACIAL_GRASS = new BlockGlacialGrass("glacial_grass", Material.grass, Sound.SNOW);
		public static BlockGlacialDirt GLACIAL_DIRT = new BlockGlacialDirt("glacial_dirt", Material.sand, Sound.GRAVEL);
		public static BlockGlacialRock GLACIAL_ROCK = new BlockGlacialRock("glacial_rock", Material.sand, Sound.STONE);
		public static BlockPodzol PODZOL = new BlockPodzol("podzol", Material.ground, Sound.GRAVEL);
		public static BlockPodzolMossy PODZOL_MOSSY = new BlockPodzolMossy("podzol_mossy", Material.ground, Sound.GRAVEL);
		public static BlockRoughDirt ROUGH_DIRT = new BlockRoughDirt("rough_dirt", Material.ground, Sound.GRAVEL);
		public static BlockWattleDaub WATTLE_DAUB2 = new BlockWattleDaub("wattledaub2", Material.ground, Sound.GRAVEL);
		public static BlockWattleDaub WATTLE_DAUB = new BlockWattleDaub("wattledaub", Material.ground, Sound.GRAVEL);
		public static BlockLogRotten LOG_ROTTEN = new BlockLogRotten("rotted_log", Material.wood, Sound.WOOD);
		public static BlockPillarPlanks PLANK_PILLAR = new BlockPillarPlanks("plank_pillar2", Material.wood, Sound.WOOD);
		public static BlockPillarPlanks PLANK_PILLAR_BOUND = new BlockPillarPlanks("plank_pillar_bar2", Material.wood, Sound.WOOD);
		public static BlockPlanksOakMoldy PLANK_MOLDY_OAK = new BlockPlanksOakMoldy("plank_oak_moldy", Material.wood, Sound.WOOD);
		public static BlockPlanksRotten PLANK_ROTTEN = new BlockPlanksRotten("plank_oak", Material.wood, Sound.WOOD);
		public static BlockPlanksBamboo PLANK_BAMBOO = new BlockPlanksBamboo("plank_jungle3", Material.wood, Sound.WOOD);
		public static BlockPlanksMoss PLANK_DARK_MOSS = new BlockPlanksMoss("plank_jungle2", Material.wood, Sound.WOOD);
		public static BlockPlanksStained PLANK_STAINED = new BlockPlanksStained("plank_blood2", Material.wood, Sound.WOOD);
		public static BlockWoodNub WOOD_NUB = new BlockWoodNub("log_seeker", Material.wood, Sound.WOOD);
		public static BlockLogGhost LOG_GHOST = new BlockLogGhost("log_ghost1", Material.wood, Sound.WOOD);
		public static BlockLogFainted LOG_FAINTED = new BlockLogFainted("log_ghost", Material.wood, Sound.WOOD);
		public static BlockLogCracked LOG_CRACKED = new BlockLogCracked("log_dark_tileable", Material.wood, Sound.WOOD);
		public static BlockLogDarkJungle LOG_DARK_JUNGLE = new BlockLogDarkJungle("log_dark_jungle", Material.wood, Sound.WOOD);
		public static BlockLogDimmed LOG_DIMMED = new BlockLogDimmed("log_bright", Material.wood, Sound.WOOD);
		public static BlockPillarBoundLogs PLANKS_BAR_BOUND = new BlockPillarBoundLogs("log_bar_pv", Material.wood, Sound.WOOD);
		public static BlockPillarBoundLogs PLANKS_PILLAR_BOUND = new BlockPillarBoundLogs("log_bar_ph", Material.wood, Sound.WOOD);
		public static BlockMoistEarth MOIST_EARTH = new BlockMoistEarth("moist_earth", Material.ground, Sound.GRAVEL);
		public static BlockLeavesDense LEAVES_DENSE = new BlockLeavesDense("leaves_floral_bnw", Material.leaves, Sound.GRASS);
		public static BlockLeavesDense LEAVES_DENSE2 = new BlockLeavesDense("leaves", Material.leaves, Sound.GRASS);
		public static BlockLeavesFloral LEAVES_FLORAL = new BlockLeavesFloral("leaves_floral", Material.leaves, Sound.GRASS);
		
		public static void s()
		{

		}
	}

	public static class Sound
	{
		public static SoundType ANVIL = net.minecraft.block.Block.soundTypeAnvil;
		public static SoundType CLOTH = net.minecraft.block.Block.soundTypeCloth;
		public static SoundType GLASS = net.minecraft.block.Block.soundTypeGlass;
		public static SoundType GRASS = net.minecraft.block.Block.soundTypeGrass;
		public static SoundType GRAVEL = net.minecraft.block.Block.soundTypeGravel;
		public static SoundType LADDER = net.minecraft.block.Block.soundTypeLadder;
		public static SoundType METAL = net.minecraft.block.Block.soundTypeMetal;
		public static SoundType PISTON = net.minecraft.block.Block.soundTypePiston;
		public static SoundType SAND = net.minecraft.block.Block.soundTypeSand;
		public static SoundType SNOW = net.minecraft.block.Block.soundTypeSnow;
		public static SoundType STONE = net.minecraft.block.Block.soundTypeStone;
		public static SoundType WOOD = net.minecraft.block.Block.soundTypeWood;

		public static void s()
		{

		}
	}

	public static class BlockSoundType
	{
		public static BlockSound STONE = new BlockSound(SoundMaterial.STONE_WALK, SoundMaterial.STONE_RUN, SoundMaterial.GRAVEL_LAND, 1f, 1f);
		public static BlockSound WOOD = new BlockSound(SoundMaterial.WOOD_WALK, SoundMaterial.WOOD_WALK, SoundMaterial.SQUEAKYWOOD_WALK, 1f, 1f);
		public static BlockSound SNOW = new BlockSound(SoundMaterial.SNOW_WALK, SoundMaterial.SNOW_RUN, SoundMaterial.SNOW_WALK, 1f, 1f);
		public static BlockSound SAND = new BlockSound(SoundMaterial.SAND_WALK, SoundMaterial.SAND_RUN, SoundMaterial.DIRT_LAND, 1f, 1f);
		public static BlockSound METAL = new BlockSound(SoundMaterial.METALBAR_LAND, SoundMaterial.METALBAR_LAND, SoundMaterial.METALBAR_LAND, 1f, 1f);
		public static BlockSound GRAVEL = new BlockSound(SoundMaterial.GRAVEL_WALK, SoundMaterial.GRAVEL_RUN, SoundMaterial.GRAVEL_LAND, 1f, 1f);
		public static BlockSound DIRT = new BlockSound(SoundMaterial.DIRT_WALK, SoundMaterial.DIRT_RUN, SoundMaterial.DIRT_LAND, 1f, 1f);
		public static BlockSound GRASS = new BlockSound(SoundMaterial.GRASS_WALK, SoundMaterial.GRASS_RUN, SoundMaterial.DIRT_LAND, 1f, 1f);
		public static BlockSound GLASS = new BlockSound(SoundMaterial.STONE_WALK, SoundMaterial.STONE_RUN, SoundMaterial.STONE_WALK, 1f, 1.5f);
		public static BlockSound CLOTH = new BlockSound(SoundMaterial.RUG_WALK, SoundMaterial.RUG_WALK, SoundMaterial.RUG_WALK, 1f, 1f);
		public static BlockSound ICE = new BlockSound(SoundMaterial.MUIFFLEDLEDICE_WALK, SoundMaterial.WEAKICE_WALK, SoundMaterial.WEAKICE_WALK, 1f, 1f);
		public static BlockSound MUD = new BlockSound(SoundMaterial.MUD_WALK, SoundMaterial.MUD_WALK, SoundMaterial.MUD_WALK, 1f, 1f);
		public static BlockSound LEAVES = new BlockSound(SoundMaterial.LEAVES_THROUGH, SoundMaterial.BRUSH_THROUGH, SoundMaterial.LEAVES_THROUGH, 1f, 1f);

		public static void s()
		{

		}
	}

	public static class SoundMaterial
	{
		public static String ARMOR_LEATHER = "fs.armor.leather";
		public static String ARMOR_METAL = "fs.armor.metal";
		public static String BLUNTWOOD_WALK = "fs.bluntwood.bluntwood_walk";
		public static String BLUNTWOOD_WANDER = "fs.bluntwood.bluntwood_wander";
		public static String BRUSH_THROUGH = "fs.brush.brush_through";
		public static String CONCRETE_RUN = "fs.concrete.concrete_run";
		public static String CONCRETE_WALK = "fs.concrete.concrete_walk";
		public static String CONCRETE_WANDER = "fs.concrete.concrete_wander";
		public static String DECKWOOD_RUN = "fs.deckwood.deckwood_run";
		public static String DIRT_LAND = "fs.dirt.dirt_land";
		public static String DIRT_RUN = "fs.dirt.dirt_run";
		public static String DIRT_WALK = "fs.dirt.dirt_walk";
		public static String DIRT_WANDER = "fs.dirt.dirt_wander";
		public static String GLASS_HARD = "fs.glass.glass_hard";
		public static String GLASS_HIT = "fs.glass.glass_hit";
		public static String GRASS_RUN = "fs.grass.grass_run";
		public static String GRASS_WALK = "fs.grass.grass_walk";
		public static String GRASS_WANDER = "fs.grass.grass_wander";
		public static String GRAVEL_LAND = "fs.gravel.gravel_land";
		public static String GRAVEL_RUN = "fs.gravel.gravel_run";
		public static String GRAVEL_WALK = "fs.gravel.gravel_walk";
		public static String GRAVEL_WANDER = "fs.gravel.gravel_wander";
		public static String LEAVES_THROUGH = "fs.leaves.leaves_through";
		public static String LINO_RUN = "fs.lino.lino_run";
		public static String LINO_WALK = "fs.lino.lino_walk";
		public static String MARBLE_RUN = "fs.marble.marble_run";
		public static String MARBLE_WALK = "fs.marble.marble_walk";
		public static String MARBLE_WANDER = "fs.marble.marble_wander";
		public static String METALBAR_LAND = "fs.metalbar.metalbar_land";
		public static String METALBAR_RUN = "fs.metalbar.metalbar_run";
		public static String METALBAR_WALK = "fs.metalbar.metalbar_walk";
		public static String METALBAR_WANDER = "fs.metalbar.metalbar_wander";
		public static String METALBOX_RUN = "fs.metalbox.metalbox_run";
		public static String METALBOX_WALK = "fs.metalbox.metalbox_walk";
		public static String METALBOX_WANDER = "fs.metalbox.metalbox_wander";
		public static String MUD_WALK = "fs.mud.mud_walk";
		public static String MUD_WANDER = "fs.mud.mud_wander";
		public static String MUIFFLEDLEDICE_WALK = "fs.muffledice.muffledice_walk";
		public static String QUICKSAND_WALK = "fs.quicksand.quicksand_walk";
		public static String RUG_WALK = "fs.rug.rug_walk";
		public static String SAND_RUN = "fs.sand.sand_run";
		public static String SAND_WALK = "fs.sand.sand_walk";
		public static String SNOW_RUN = "fs.snow.snow_run";
		public static String SNOW_WALK = "fs.snow.snow_walk";
		public static String SNOW_WANDER = "fs.snow.snow_wander";
		public static String SQUEAKYWOOD_WALK = "fs.squeakywood.squeakywood_walk";
		public static String SQUEAKYWOOD_WANDER = "fs.squeakywood.squeakywood_wander";
		public static String STONE_RUN = "fs.stone.stone_run";
		public static String STONE_WALK = "fs.stone.stone_walk";
		public static String STONE_WANDER = "fs.stone.stone_wander";
		public static String WATER_THROUGH = "fs.water_stereofix.water_through";
		public static String WATER_WANDER = "fs.water_stereofix.water_wander";
		public static String WEAKICE_WALK = "fs.weakice.weakice_walk";
		public static String WOOD_WALK = "fs.wood.wood_walk";
		public static String WEATHER_RAIN = "rain";
		public static String WEATHER_DUST = "dust";
		public static String AMBIENT_ICE = "ice";
		public static String AMBIENT_FROG = "frog";
		public static String AMBIENT_WIND = "wind";
		public static String AMBIENT_CRICKETS = "crickets";
		public static String AMBIENT_FOREST = "forest";
		public static String AMBIENT_BEACH = "beach";
		public static String AMBIENT_RIVER = "river";
		public static String AMBIENT_PLAINS = "plains";
		public static String AMBIENT_JUNGLE = "jungle";
		public static String AMBIENT_SOULSAND = "soulsand";
		public static String AMBIENT_END = "theend";
		public static String AMBIENT_BIRD = "bird";
		public static String AMBIENT_WOODPECKER = "woodpecker";
		public static String AMBIENT_OWL = "owl";
		public static String AMBIENT_UNDERGROUND = "underground";
		public static String AMBIENT_ROCKFALL = "rockfall";
		public static String AMBIENT_BIGROCKFALL = "bigrockfall";
		public static String AMBIENT_MONSTERGROWL = "monstergrowl";
		public static String AMBIENT_WATERDROPS = "waterdrops";
		public static String AMBIENT_SEAGULLS = "seagulls";
		public static String AMBIENT_CYOTE = "coyote";
		public static String AMBIENT_WOLF = "wolf";
		public static String AMBIENT_UNDERDEEPOCEAN = "underdeepocean";
		public static String AMBIENT_UNDEROCEAN = "underocean";
		public static String AMBIENT_UNDERRIVER = "underriver";
		public static String AMBIENT_UNDERWATER = "underwater";
		public static String AMBIENT_WHALE = "whale";
		public static String AMBIENT_HEARTBEAT = "heartbeat";
		public static String AMBIENT_TUMMY = "tummy";
		public static String AMBIENT_FLOORSQUEAK = "floorsqueak";
		public static String AMBIENT_BREATHING = "breathing";
		public static String AMBIENT_JUMP = "jump";
		public static String AMBIENT_BEES = "bees";
		public static String AMBIENT_INSECTCRAWL = "insectcrawl";
		public static String AMBIENT_SWOOSH = "swoosh";
		public static String AMBIENT_INSECTBUZZ = "insectbuzz";
		public static String AMBIENT_GNATT = "gnatt";
		public static String AMBIENT_GRASSHOPPER = "grasshopper";
		public static String AMBIENT_CROCODILE = "crocodile";
		public static String AMBIENT_RATTLESNAKE = "rattlesnake";
		public static String AMBIENT_ELEPHANT = "elephant";
		public static String AMBIENT_HISS = "hiss";
		public static String AMBIENT_NEWSPRINT = "newsprint";
		public static String AMBIENT_PAGEFLIP = "pageflip";
		public static String AMBIENT_PAGEFLIPHEAVY = "pageflipheavy";
		public static String AMBIENT_BOOKSHELF = "bookshelf";
		public static String AMBIENT_CRAFTING = "crafting";
		public static String AMBIENT_BISON = "bison";
		public static String AMBIENT_PRIMATES = "primates";
		public static String AMBIENT_BOWPULL = "bowpull";

		public static void s()
		{

		}
	}

	public static class Item
	{
		public static ItemCrusader SWORD_CRUSADER = new ItemCrusader("crusader");
		public static ItemCrusader SWORD_CRUSADER_HIGH = new ItemCrusader("crusader_high");
		public static ItemCrusader SWORD_CRUSADER_VENERATED = new ItemCrusader("crusader_venerated");
		public static ItemCrusader SWORD_CRUSADER_DAMNED = new ItemCrusader("crusader_damned");
		public static ItemCrusader SWORD_CRUSADER_MALEVOLENT = new ItemCrusader("crusader_malevolent");
		public static ItemGladius SWORD_GLADIUS = new ItemGladius("gladius");
		public static ItemGladius SWORD_GLADIUS_ACCLAIMED = new ItemGladius("gladius_acclaimed");
		public static ItemGladius SWORD_GLADIUS_ATROCIOUS = new ItemGladius("gladius_atrocious");
		public static ItemGladius SWORD_GLADIUS_DISTINGUISHED = new ItemGladius("gladius_distinguished");
		public static ItemGladius SWORD_GLADIUS_NEFARIOUS = new ItemGladius("gladius_nefarious");
		public static ItemSpada SWORD_SPADA = new ItemSpada("spada");
		public static ItemSpada SWORD_SPADA_IMPERIAL = new ItemSpada("spada_imperial");
		public static ItemSpada SWORD_SPADA_NOBLE = new ItemSpada("spada_noble");
		public static ItemSpada SWORD_SPADA_DESPICABLE = new ItemSpada("spada_despicable");
		public static ItemSpada SWORD_SPADA_DASTARDLY = new ItemSpada("spada_dastardly");

		public static void s()
		{

		}
	}

	public static class WorldType
	{
		public static WorldTypeAres ARES = new WorldTypeAres("ares", 1337);

		public static void s()
		{

		}
	}

	public static class Effect
	{
		public static EffectFirefly FIREFLY = new EffectFirefly();
		public static EffectFallingLeaf FALLING_LEAF = new EffectFallingLeaf();
		public static EffectDust DUST = new EffectDust();

		public static void s()
		{

		}
	}

	public static class WorldGenerator
	{
		public static WorldGeneratorEmpty EMPTY = new WorldGeneratorEmpty();

		public static void s()
		{

		}
	}

	public static class Biome
	{
		public static BiomePlains PLAINS = new BiomePlains(0);
		public static BiomeForest FOREST = new BiomeForest(10);
		public static BiomeCanopy CANOPY = new BiomeCanopy(20);
		public static BiomeDesert DESERT = new BiomeDesert(30);
		public static BiomeMountains MOUNTAINS = new BiomeMountains(40);
		public static BiomeRiver RIVER = new BiomeRiver(40);

		public static void s()
		{

		}
	}

	public static class BiomeLevel
	{
		public static LeveledBiome BIOME_A = new LeveledBiome(Biome.PLAINS, 5); 
		public static LeveledBiome BIOME_B = new LeveledBiome(Biome.PLAINS, 7);
		public static LeveledBiome BIOME_C = new LeveledBiome(Biome.FOREST, 7);
		public static LeveledBiome BIOME_D = new LeveledBiome(Biome.FOREST, 10);
		public static LeveledBiome BIOME_E = new LeveledBiome(Biome.FOREST, 15);
		public static LeveledBiome BIOME_F = new LeveledBiome(Biome.CANOPY, 15);
		public static LeveledBiome BIOME_G = new LeveledBiome(Biome.CANOPY, 20);
		public static LeveledBiome BIOME_H = new LeveledBiome(Biome.CANOPY, 27);
		public static LeveledBiome BIOME_I = new LeveledBiome(Biome.PLAINS, 27);
		public static LeveledBiome BIOME_J = new LeveledBiome(Biome.PLAINS, 30);
		public static LeveledBiome BIOME_K = new LeveledBiome(Biome.DESERT, 30);
		public static LeveledBiome BIOME_L = new LeveledBiome(Biome.DESERT, 33);

		public static void s()
		{
			try
			{
				for(Field i : BiomeLevel.class.getDeclaredFields())
				{
					biomeLevels.add((LeveledBiome) i.get(null));
				}
			}

			catch(Exception e)
			{

			}
		}
	}

	public static class BiomeEffect
	{
		public static BiomeSimplexEffect RIVER = new BiomeSimplexEffect(Biome.RIVER, 1000, 3, 20, BiomeEffectType.STREAK, 0.011, 35, 2);
		public static BiomeSimplexEffect POND = new BiomeSimplexEffect(Biome.RIVER, 2000, 6, 20, BiomeEffectType.BLOB, 0.019, 10, 2);
		public static BiomeSimplexEffect RIDGE = new BiomeSimplexEffect(Biome.MOUNTAINS, 3000, 9, 20, BiomeEffectType.STREAK, 0.025, 15, 4);
		public static BiomeSimplexEffect MOUNTAIN_RIDGE = new BiomeSimplexEffect(Biome.MOUNTAINS, 4000,11, 20, BiomeEffectType.BLOB, 0.025, 15, 4);

		public static void s()
		{
			try
			{
				for(Field i : BiomeEffect.class.getDeclaredFields())
				{
					BiomeSimplexEffect f = (BiomeSimplexEffect) i.get(null);
					biomeEffects.add(f);
					Random r = new Random(400 * f.getUid());
					GEN.addGenerator(new SimplexProperties("terrain-streakset-" + f.getUid(), 125 * f.getUid() + r.nextLong(), 280));
					GEN.addGenerator(new SimplexProperties(f.getBiome().getName().toUpperCase() + "-" + f.getUid(), f.getUid() - r.nextLong(), 200));
				}
			}

			catch(Exception e)
			{

			}
		}
	}

	public static class Brushes
	{
		public static IBrush AIR = new IBrushBuilder()
		{
			@Override
			public IBrush buildBrush()
			{
				IBrush brush = new Brush();
				brush.getPalette().add(new Brushable(Blocks.air, 1));

				return brush;
			}
		}.buildBrush();

		public static IBrush RUBBLE_ROCK = new IBrushBuilder()
		{
			@Override
			public IBrush buildBrush()
			{
				IBrush brush = new Brush();
				brush.getPalette().add(new Brushable(Block.PATH_STONE, 5));
				brush.getPalette().add(new Brushable(Block.PATH_STONE_BRICK_CRACKED, 10));
				brush.getPalette().add(new Brushable(Block.PATH_STONE_BRICK_CRUSHED, 8));
				brush.getPalette().add(new Brushable(Blocks.air, 8));

				return brush;
			}
		}.buildBrush();

		public static IBrush RUBBLE_SANDSTONE = new IBrushBuilder()
		{
			@Override
			public IBrush buildBrush()
			{
				IBrush brush = new Brush();
				brush.getPalette().add(new Brushable(Block.COLD_SANDSTONE, 5));
				brush.getPalette().add(new Brushable(Block.COLD_SANDSTONE_BRICK, 10));
				brush.getPalette().add(new Brushable(Block.ARID_STONE, 8));
				brush.getPalette().add(new Brushable(Blocks.air, 8));

				return brush;
			}
		}.buildBrush();

		public static IBrush RUBBLE_DIRT = new IBrushBuilder()
		{
			@Override
			public IBrush buildBrush()
			{
				IBrush brush = new Brush();
				brush.getPalette().add(new Brushable(Block.PODZOL, 7));
				brush.getPalette().add(new Brushable(Block.PODZOL_MOSSY, 5));
				brush.getPalette().add(new Brushable(Block.COLD_DIRT, 15));
				brush.getPalette().add(new Brushable(Block.SMOOTH_DIRT, 8));
				brush.getPalette().add(new Brushable(Block.ROUGH_DIRT, 7));
				brush.getPalette().add(new Brushable(Blocks.air, 8));

				return brush;
			}
		}.buildBrush();
	}

	public static class Models
	{
		public static IModel RUBBLE_ROCK = new IModelBuilder()
		{
			@Override
			public IModel buildModel()
			{
				IModelMaterial rubble = new ModelMaterial("rubble", Brushes.RUBBLE_ROCK);
				IModel model = new Model();
				model.put(0, 0, 0, rubble);
				model.scale(3, 6, 3);
				model.smooth(1, SmoothingMode.EDGES);
				model.center();

				return model;
			}
		}.buildModel();

		public static IModel RUBBLE_SANDSTONE = new IModelBuilder()
		{
			@Override
			public IModel buildModel()
			{
				IModelMaterial rubble = new ModelMaterial("rubble", Brushes.RUBBLE_SANDSTONE);
				IModel model = new Model();
				model.put(0, 0, 0, rubble);
				model.scale(3, 6, 3);
				model.smooth(1, SmoothingMode.EDGES);
				model.center();

				return model;
			}
		}.buildModel();

		public static IModel RUBBLE_DIRT = new IModelBuilder()
		{
			@Override
			public IModel buildModel()
			{
				IModelMaterial rubble = new ModelMaterial("rubble", Brushes.RUBBLE_DIRT);
				IModel model = new Model();
				model.put(0, 0, 0, rubble);
				model.scale(3, 6, 3);
				model.smooth(1, SmoothingMode.EDGES);
				model.center();

				return model;
			}
		}.buildModel();
	}

	public static class BlockEffects
	{
		public static BlockEffect LOGS_NIGHT = new SimpleBlockEffect()
		{
			@Override
			public void onPlay(World w, int x, int y, int z)
			{
				SFX.play(new DSound("sharedcms:" + Content.SoundMaterial.AMBIENT_WOODPECKER, 2f, 1f, 0.2f), new Location(x, y, z));
			}

			@Override
			public boolean meetsConditions(World w, int x, int y, int z)
			{
				if(!w.isRaining() && w.getWorldTime() > 12966 && w.getWorldTime() < 22916)
				{
					return true;
				}

				return false;
			}

			@Override
			public double getChance()
			{
				return 0.0001;
			}

			@Override
			public boolean qualifies(net.minecraft.block.Block block)
			{
				return block instanceof BlockLogBase;
			}
		};

		public static BlockEffect LEAVES_NIGHT = new SimpleBlockEffect()
		{
			@Override
			public void onPlay(World w, int x, int y, int z)
			{
				SFX.play(new DSound("sharedcms:" + Content.SoundMaterial.AMBIENT_OWL, 2f, 1f, 0.2f), new Location(x, y, z));
			}

			@Override
			public boolean meetsConditions(World w, int x, int y, int z)
			{
				if(!w.isRaining() && w.getWorldTime() > 12966 && w.getWorldTime() < 22916)
				{
					return true;
				}

				return false;
			}

			@Override
			public double getChance()
			{
				return 0.0001;
			}

			@Override
			public boolean qualifies(net.minecraft.block.Block block)
			{
				return block instanceof AresNaturalLeavesBlock;
			}
		};

		public static BlockEffect LEAVES_DAY = new SimpleBlockEffect()
		{
			long ms = M.ms();

			@Override
			public void onPlay(World w, int x, int y, int z)
			{
				SFX.play(new DSound("sharedcms:" + Content.SoundMaterial.AMBIENT_FOREST, 2f, 1f, 0.2f), new Location(x, y, z));
			}

			@Override
			public boolean meetsConditions(World w, int x, int y, int z)
			{
				if(M.ms() - ms < 5000)
				{
					return false;
				}

				if(!w.isRaining() && !(w.getWorldTime() > 12966 && w.getWorldTime() < 22916))
				{
					ms = M.ms();
					return true;
				}

				return false;
			}

			@Override
			public double getChance()
			{
				return 0.01;
			}

			@Override
			public boolean qualifies(net.minecraft.block.Block block)
			{
				return block instanceof AresNaturalLeavesBlock;
			}
		};

		public static BlockEffect SHRUB_NIGHT = new SimpleBlockEffect()
		{
			long ms = M.ms();

			@Override
			public void onPlay(World w, int x, int y, int z)
			{
				SFX.play(new DSound("sharedcms:" + Content.SoundMaterial.AMBIENT_CRICKETS, 2f, 1f, 0.2f), new Location(x, y, z));
			}

			@Override
			public boolean meetsConditions(World w, int x, int y, int z)
			{
				if(M.ms() - ms < 5000)
				{
					return false;
				}

				if(!w.isRaining() && w.getWorldTime() > 12966 && w.getWorldTime() < 22916)
				{
					ms = M.ms();
					return true;
				}

				return false;
			}

			@Override
			public double getChance()
			{
				return 0.01;
			}

			@Override
			public boolean qualifies(net.minecraft.block.Block block)
			{
				return block instanceof AresBlockShrub;
			}
		};

		public static BlockEffect SHRUB_DAY = new SimpleBlockEffect()
		{
			long ms = M.ms();

			@Override
			public void onPlay(World w, int x, int y, int z)
			{
				SFX.play(new DSound("sharedcms:" + Content.SoundMaterial.AMBIENT_PLAINS, 2f, 1f, 0.2f), new Location(x, y, z));
			}

			@Override
			public boolean meetsConditions(World w, int x, int y, int z)
			{
				if(M.ms() - ms < 5000)
				{
					return false;
				}

				if(!w.isRaining() && !(w.getWorldTime() > 12966 && w.getWorldTime() < 22916))
				{
					ms = M.ms();
					return true;
				}

				return false;
			}

			@Override
			public double getChance()
			{
				return 0.01;
			}

			@Override
			public boolean qualifies(net.minecraft.block.Block block)
			{
				return block instanceof AresBlockShrub;
			}
		};

		public static BlockEffect SHRUB_FIREFLY = new SimpleBlockEffect()
		{
			@Override
			public void onPlay(World w, int x, int y, int z)
			{
				Content.Effect.FIREFLY.play(w, x, y, z, ((AresBlockShrub) w.getBlock(x, y, z)).getRadiantColor());
			}

			@Override
			public boolean meetsConditions(World w, int x, int y, int z)
			{
				return !w.isRaining();
			}

			@Override
			public double getChance()
			{
				return 0.02;
			}

			@Override
			public boolean qualifies(net.minecraft.block.Block block)
			{
				return block instanceof AresBlockShrub;
			}
		};

		public static BlockEffect REDWOODS_HOWL = new SimpleBlockEffect()
		{
			@Override
			public void onPlay(World w, int x, int y, int z)
			{
				SFX.play(new DSound("sharedcms:" + Content.SoundMaterial.AMBIENT_WOLF, 0.3f, 1f, 0.2f), new Location(x, y, z));
			}

			@Override
			public boolean meetsConditions(World w, int x, int y, int z)
			{
				return w.getWorldTime() > 12966 && w.getWorldTime() < 22916;
			}

			@Override
			public double getChance()
			{
				return 0.0001;
			}

			@Override
			public boolean qualifies(net.minecraft.block.Block block)
			{
				return block instanceof BlockPodzol;
			}
		};

		public static BlockEffect DUST_SNOW = new SimpleBlockEffect()
		{
			@Override
			public void onPlay(World w, int x, int y, int z)
			{
				Content.Effect.DUST.play(w, x, y, z, new Color(240, 240, 255));
			}

			@Override
			public boolean meetsConditions(World w, int x, int y, int z)
			{
				return true;
			}

			@Override
			public double getChance()
			{
				return 0.01;
			}

			@Override
			public boolean qualifies(net.minecraft.block.Block block)
			{
				return block instanceof BlockGlacialGrass;
			}
		};

		public static BlockEffect DUST_SAND = new SimpleBlockEffect()
		{
			@Override
			public void onPlay(World w, int x, int y, int z)
			{
				Content.Effect.DUST.play(w, x, y, z, new Color(255, 227, 137));
			}

			@Override
			public boolean meetsConditions(World w, int x, int y, int z)
			{
				return true;
			}

			@Override
			public double getChance()
			{
				return 0.01;
			}

			@Override
			public boolean qualifies(net.minecraft.block.Block block)
			{
				return block instanceof BlockAridSand;
			}
		};

		public static BlockEffect LEAVES = new SimpleBlockEffect()
		{
			@Override
			public void onPlay(World w, int x, int y, int z)
			{
				if(w.getBlock(x, y, z) instanceof BlockLeavesCherryBlossom)
				{
					Content.Effect.FALLING_LEAF.play(w, x, y, z, new Color(255, 145, 249));
				}

				else
				{
					Content.Effect.FALLING_LEAF.play(w, x, y, z, new Color(w.getBlock(x, y, z).getBlockColor()));
				}
			}

			@Override
			public boolean meetsConditions(World w, int x, int y, int z)
			{
				return true;
			}

			@Override
			public double getChance()
			{
				return 0.005;
			}

			@Override
			public boolean qualifies(net.minecraft.block.Block block)
			{
				return block instanceof AresNaturalLeavesBlock || block instanceof BlockLeavesCherryBlossom;
			}
		};

		public static void s()
		{

		}
	}

	public static List<AresBlockShrub> flowers()
	{
		List<AresBlockShrub> b = new ArrayList<AresBlockShrub>();

		for(Field i : Block.class.getDeclaredFields())
		{
			try
			{
				b.add((AresBlockShrub) i.get(null));
			}

			catch(Exception e)
			{

			}
		}

		return b;
	}

	public static List<LudicrousBiome> biomes()
	{
		List<LudicrousBiome> b = new ArrayList<LudicrousBiome>();

		if(hasExclusiveBiomes())
		{
			for(Field i : Biome.class.getDeclaredFields())
			{
				if(isExclusive(i))
				{
					try
					{
						b.add((LudicrousBiome) i.get(null));
					}

					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}

		else
		{
			for(Field i : Biome.class.getDeclaredFields())
			{
				try
				{
					b.add((LudicrousBiome) i.get(null));
				}

				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}

		return b;
	}

	private static boolean isExclusive(Field f)
	{
		return f.isAnnotationPresent(DebugExclusive.class);
	}

	private static boolean hasExclusiveBiomes()
	{
		for(Field i : Biome.class.getDeclaredFields())
		{
			if(isExclusive(i))
			{
				return true;
			}
		}

		return false;
	}

	@Override
	public void onPreRegister(ContentController cms, Side side)
	{
		VoxelRegistry.registerForTessellator(Blocks.cobblestone);
		VoxelRegistry.registerForTessellator(Blocks.mossy_cobblestone);
		GEN.addGenerator(new SimplexProperties("biomeset", 25 * biomes().size(), 120));
		GEN.addGenerator(new SimplexProperties("biomeset-tilt", 19 * biomes().size(), 120));
		GEN.addGenerator(new SimplexProperties("biomeset-spread", 23 * biomes().size(), 120));
		GEN.addGenerator(new SimplexProperties("terrain-variation", 175, 120));
		GEN.addGenerator(new SimplexProperties("terrain-streakset", 125, 280));

		try
		{
			for(Field i : Block.class.getDeclaredFields())
			{
				cms.register(i.get(null));
			}

			for(Field i : Item.class.getDeclaredFields())
			{
				cms.register(i.get(null));
			}

			for(Field i : Tab.class.getDeclaredFields())
			{
				cms.register(i.get(null));
			}

			for(Field i : WorldType.class.getDeclaredFields())
			{
				cms.register(i.get(null));
			}

			for(Field i : Biome.class.getDeclaredFields())
			{
				cms.register(i.get(null));
			}

			for(Field i : WorldGenerator.class.getDeclaredFields())
			{
				cms.register(i.get(null));
			}

			for(Field i : Effect.class.getDeclaredFields())
			{
				cms.register(i.get(null));
			}

			if(side.equals(Side.CLIENT))
			{
				for(Field i : BlockEffects.class.getDeclaredFields())
				{
					cms.register(i.get(null));
				}
			}
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static GList<LeveledBiome> biomeLevels()
	{
		biomeLevels.clear();
		BiomeLevel.s();

		return biomeLevels;
	}

	public static GList<BiomeSimplexEffect> biomeEffects()
	{
		biomeEffects.clear();
		BiomeEffect.s();

		return biomeEffects;
	}

	public static void init()
	{
		Content.Tab.s();
		Content.Sound.s();
		Content.Block.s();
		Content.Item.s();
		Content.WorldType.s();
		Content.Biome.s();
		Content.BiomeLevel.s();
		Content.BiomeEffect.s();
		Content.Effect.s();
		Content.SoundMaterial.s();
	}
}
