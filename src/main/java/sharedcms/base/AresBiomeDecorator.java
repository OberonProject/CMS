package sharedcms.base;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.BIG_SHROOM;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CACTUS;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CLAY;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.DEAD_BUSH;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.LAKE;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.LILYPAD;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.PUMPKIN;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.REED;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SAND;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SAND_PASS2;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SHROOM;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.COAL;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIAMOND;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIRT;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GOLD;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GRAVEL;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.IRON;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.LAPIS;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.REDSTONE;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenSand;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import sharedcms.content.Content;
import sharedcms.content.world.decorator.DecorateScrambler;
import sharedcms.content.world.generator.AresWorldGenFlowers;
import sharedcms.content.world.generator.WorldGenTallAresGrass;
import sharedcms.util.GList;

public class AresBiomeDecorator extends BiomeDecorator
{
	protected Block[] tallGrassTypes;
	private GList<AresDecorator> decorators;

	public AresBiomeDecorator(AresBiome biome)
	{
		this.sandGen = new WorldGenSand(Blocks.sand, 7);
		this.gravelAsSandGen = new WorldGenSand(Blocks.gravel, 6);
		this.dirtGen = new WorldGenMinable(Blocks.dirt, 32);
		this.gravelGen = new WorldGenMinable(Blocks.gravel, 32);
		this.coalGen = new WorldGenMinable(Blocks.coal_ore, 16);
		this.ironGen = new WorldGenMinable(Blocks.iron_ore, 8);
		this.goldGen = new WorldGenMinable(Blocks.gold_ore, 8);
		this.redstoneGen = new WorldGenMinable(Blocks.redstone_ore, 7);
		this.diamondGen = new WorldGenMinable(Blocks.diamond_ore, 7);
		this.lapisGen = new WorldGenMinable(Blocks.lapis_ore, 6);
		this.yellowFlowerGen = new AresWorldGenFlowers(biome.getProcessedFlowers(), biome.getFlowersPerChunk());
		this.mushroomBrownGen = new WorldGenFlowers(Blocks.brown_mushroom);
		this.mushroomRedGen = new WorldGenFlowers(Blocks.red_mushroom);
		this.bigMushroomGen = new WorldGenBigMushroom();
		this.reedGen = new WorldGenReed();
		this.cactusGen = new WorldGenCactus();
		this.waterlilyGen = new WorldGenWaterlily();
		this.tallGrassTypes = new Block[] {Content.Block.THIN_GRASS, Content.Block.SHORT_GRASS, Content.Block.THIN_GRASS, Content.Block.SHORT_GRASS, Content.Block.THIN_GRASS, Content.Block.THIN_GRASS, Content.Block.THIN_GRASS, Content.Block.SHORT_GRASS, Content.Block.TALL_GRASS, Content.Block.FERN, Content.Block.SHORT_GRASS, Content.Block.SPOKED_GRASS, Content.Block.STUBBED_GRASS};
		this.flowersPerChunk = 2;
		this.grassPerChunk = 1;
		this.sandPerChunk = 1;
		this.sandPerChunk2 = 3;
		this.clayPerChunk = 1;
		this.waterlilyPerChunk = 12;
		this.generateLakes = true;
		decorators = new GList<AresDecorator>();
	}
	
	public void addDecorator(AresDecorator g)
	{
		decorators.add(g);
	}

	public void decorateChunk(World p_150512_1_, Random p_150512_2_, BiomeGenBase p_150512_3_, int p_150512_4_, int p_150512_5_)
	{
		if(currentWorld == null)
		{
			this.currentWorld = p_150512_1_;
			this.randomGenerator = p_150512_2_;
			this.chunk_X = p_150512_4_;
			this.chunk_Z = p_150512_5_;
			this.genDecorations(p_150512_3_);
			this.currentWorld = null;
			this.randomGenerator = null;
		}
	}

	protected void genDecorations(BiomeGenBase p_150513_1_)
	{
		MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(currentWorld, randomGenerator, chunk_X, chunk_Z));
		this.generateOres();
		int i;
		int j;
		int k;

		boolean doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, SAND);
		for(i = 0; doGen && i < this.sandPerChunk2; ++i)
		{
			j = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.sandGen.generate(this.currentWorld, this.randomGenerator, j, this.currentWorld.getTopSolidOrLiquidBlock(j, k), k);
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, CLAY);
		for(i = 0; doGen && i < this.clayPerChunk; ++i)
		{
			j = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.clayGen.generate(this.currentWorld, this.randomGenerator, j, this.currentWorld.getTopSolidOrLiquidBlock(j, k), k);
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, SAND_PASS2);
		for(i = 0; doGen && i < this.sandPerChunk; ++i)
		{
			j = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.gravelAsSandGen.generate(this.currentWorld, this.randomGenerator, j, this.currentWorld.getTopSolidOrLiquidBlock(j, k), k);
		}

		i = this.treesPerChunk;

		if(this.randomGenerator.nextInt(10) == 0)
		{
			++i;
		}

		int l;
		int i1;

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, TREE);
		for(j = 0; doGen && j < i; ++j)
		{
			k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			i1 = this.currentWorld.getHeightValue(k, l);
			WorldGenAbstractTree worldgenabstracttree = p_150513_1_.func_150567_a(this.randomGenerator);
			worldgenabstracttree.setScale(1.0D, 1.0D, 1.0D);

			if(worldgenabstracttree.generate(this.currentWorld, this.randomGenerator, k, i1, l))
			{
				worldgenabstracttree.func_150524_b(this.currentWorld, this.randomGenerator, k, i1, l);
			}
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, BIG_SHROOM);
		for(j = 0; doGen && j < this.bigMushroomsPerChunk; ++j)
		{
			k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.bigMushroomGen.generate(this.currentWorld, this.randomGenerator, k, this.currentWorld.getHeightValue(k, l), l);
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, FLOWERS);
		for(j = 0; doGen && j < this.flowersPerChunk; ++j)
		{
			k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			i1 = nextInt(this.currentWorld.getHeightValue(k, l) + 32);
			String s = p_150513_1_.func_150572_a(this.randomGenerator, k, i1, l);
			BlockFlower blockflower = BlockFlower.func_149857_e(s);

			if(blockflower.getMaterial() != Material.air)
			{
				this.yellowFlowerGen.func_150550_a(blockflower, BlockFlower.func_149856_f(s));
				this.yellowFlowerGen.generate(this.currentWorld, this.randomGenerator, k, i1, l);
			}
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, GRASS);
		for(j = 0; doGen && j < this.grassPerChunk; ++j)
		{
			k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			i1 = nextInt(this.currentWorld.getHeightValue(k, l) * 2);
			WorldGenerator worldgenerator = new WorldGenTallAresGrass(tallGrassTypes);
			worldgenerator.generate(this.currentWorld, this.randomGenerator, k, i1, l);
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, DEAD_BUSH);
		for(j = 0; doGen && j < this.deadBushPerChunk; ++j)
		{
			k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			i1 = nextInt(this.currentWorld.getHeightValue(k, l) * 2);
			(new WorldGenDeadBush(Blocks.deadbush)).generate(this.currentWorld, this.randomGenerator, k, i1, l);
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, LILYPAD);
		for(j = 0; doGen && j < this.waterlilyPerChunk; ++j)
		{
			k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;

			for(i1 = nextInt(this.currentWorld.getHeightValue(k, l) * 2); i1 > 0 && this.currentWorld.isAirBlock(k, i1 - 1, l); --i1)
			{
				;
			}

			this.waterlilyGen.generate(this.currentWorld, this.randomGenerator, k, i1, l);
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, SHROOM);
		for(j = 0; doGen && j < this.mushroomsPerChunk; ++j)
		{
			if(this.randomGenerator.nextInt(4) == 0)
			{
				k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				i1 = this.currentWorld.getHeightValue(k, l);
				this.mushroomBrownGen.generate(this.currentWorld, this.randomGenerator, k, i1, l);
			}

			if(this.randomGenerator.nextInt(8) == 0)
			{
				k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				i1 = nextInt(this.currentWorld.getHeightValue(k, l) * 2);
				this.mushroomRedGen.generate(this.currentWorld, this.randomGenerator, k, i1, l);
			}
		}

		if(doGen && this.randomGenerator.nextInt(4) == 0)
		{
			j = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			l = nextInt(this.currentWorld.getHeightValue(j, k) * 2);
			this.mushroomBrownGen.generate(this.currentWorld, this.randomGenerator, j, l, k);
		}

		if(doGen && this.randomGenerator.nextInt(8) == 0)
		{
			j = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			l = nextInt(this.currentWorld.getHeightValue(j, k) * 2);
			this.mushroomRedGen.generate(this.currentWorld, this.randomGenerator, j, l, k);
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, REED);
		for(j = 0; doGen && j < this.reedsPerChunk; ++j)
		{
			k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			i1 = nextInt(this.currentWorld.getHeightValue(k, l) * 2);
			this.reedGen.generate(this.currentWorld, this.randomGenerator, k, i1, l);
		}

		for(j = 0; doGen && j < 10; ++j)
		{
			k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			i1 = nextInt(this.currentWorld.getHeightValue(k, l) * 2);
			this.reedGen.generate(this.currentWorld, this.randomGenerator, k, i1, l);
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, PUMPKIN);
		if(doGen && this.randomGenerator.nextInt(32) == 0)
		{
			j = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			l = nextInt(this.currentWorld.getHeightValue(j, k) * 2);
			(new WorldGenPumpkin()).generate(this.currentWorld, this.randomGenerator, j, l, k);
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, CACTUS);
		for(j = 0; doGen && j < this.cactiPerChunk; ++j)
		{
			k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			i1 = nextInt(this.currentWorld.getHeightValue(k, l) * 2);
			this.cactusGen.generate(this.currentWorld, this.randomGenerator, k, i1, l);
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, LAKE);
		if(doGen && this.generateLakes)
		{
			for(j = 0; j < 50; ++j)
			{
				k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				l = this.randomGenerator.nextInt(this.randomGenerator.nextInt(248) + 8);
				i1 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				(new WorldGenLiquids(Blocks.flowing_water)).generate(this.currentWorld, this.randomGenerator, k, l, i1);
			}

			for(j = 0; j < 20; ++j)
			{
				k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				l = this.randomGenerator.nextInt(this.randomGenerator.nextInt(this.randomGenerator.nextInt(240) + 8) + 8);
				i1 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				(new WorldGenLiquids(Blocks.flowing_lava)).generate(this.currentWorld, this.randomGenerator, k, l, i1);
			}
		}
		
		for(AresDecorator m : decorators)
		{
			for(j = 0; j < m.getTries(); j++)
			{
				k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				i1 = nextInt(this.currentWorld.getHeightValue(k, l) * 2);
				m.generate(this.currentWorld, this.randomGenerator, k, i1, l);
			}
		}

		MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(currentWorld, randomGenerator, chunk_X, chunk_Z));
	}

	protected void genStandardOre1(int p_76795_1_, WorldGenerator p_76795_2_, int p_76795_3_, int p_76795_4_)
	{
		for(int l = 0; l < p_76795_1_; ++l)
		{
			int i1 = this.chunk_X + this.randomGenerator.nextInt(16);
			int j1 = this.randomGenerator.nextInt(p_76795_4_ - p_76795_3_) + p_76795_3_;
			int k1 = this.chunk_Z + this.randomGenerator.nextInt(16);
			p_76795_2_.generate(this.currentWorld, this.randomGenerator, i1, j1, k1);
		}
	}

	protected void genStandardOre2(int p_76793_1_, WorldGenerator p_76793_2_, int p_76793_3_, int p_76793_4_)
	{
		for(int l = 0; l < p_76793_1_; ++l)
		{
			int i1 = this.chunk_X + this.randomGenerator.nextInt(16);
			int j1 = this.randomGenerator.nextInt(p_76793_4_) + this.randomGenerator.nextInt(p_76793_4_) + (p_76793_3_ - p_76793_4_);
			int k1 = this.chunk_Z + this.randomGenerator.nextInt(16);
			p_76793_2_.generate(this.currentWorld, this.randomGenerator, i1, j1, k1);
		}
	}

	protected void generateOres()
	{
		MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(currentWorld, randomGenerator, chunk_X, chunk_Z));
		if(TerrainGen.generateOre(currentWorld, randomGenerator, dirtGen, chunk_X, chunk_Z, DIRT))
		{
			this.genStandardOre1(20, this.dirtGen, 0, 256);
		}
		if(TerrainGen.generateOre(currentWorld, randomGenerator, gravelGen, chunk_X, chunk_Z, GRAVEL))
		{
			this.genStandardOre1(10, this.gravelGen, 0, 256);
		}
		if(TerrainGen.generateOre(currentWorld, randomGenerator, coalGen, chunk_X, chunk_Z, COAL))
		{
			this.genStandardOre1(20, this.coalGen, 0, 128);
		}
		if(TerrainGen.generateOre(currentWorld, randomGenerator, ironGen, chunk_X, chunk_Z, IRON))
		{
			this.genStandardOre1(20, this.ironGen, 0, 64);
		}
		if(TerrainGen.generateOre(currentWorld, randomGenerator, goldGen, chunk_X, chunk_Z, GOLD))
		{
			this.genStandardOre1(2, this.goldGen, 0, 32);
		}
		if(TerrainGen.generateOre(currentWorld, randomGenerator, redstoneGen, chunk_X, chunk_Z, REDSTONE))
		{
			this.genStandardOre1(8, this.redstoneGen, 0, 16);
		}
		if(TerrainGen.generateOre(currentWorld, randomGenerator, diamondGen, chunk_X, chunk_Z, DIAMOND))
		{
			this.genStandardOre1(1, this.diamondGen, 0, 16);
		}
		if(TerrainGen.generateOre(currentWorld, randomGenerator, lapisGen, chunk_X, chunk_Z, LAPIS))
		{
			this.genStandardOre2(1, this.lapisGen, 16, 16);
		}
		MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(currentWorld, randomGenerator, chunk_X, chunk_Z));
	}

	private int nextInt(int i)
	{
		if(i <= 1)
		{
			return 0;
		}
		return this.randomGenerator.nextInt(i);
	}
}
