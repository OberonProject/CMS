package sharedcms.content.world.biome.decorator;

import sharedcms.base.AresBiome;
import sharedcms.base.AresBiomeDecorator;
import sharedcms.content.Content;
import sharedcms.content.Content.Block;
import sharedcms.content.structure.artifact.Artifact;
import sharedcms.content.structure.artifact.IArtifact;
import sharedcms.content.structure.brush.Brush;
import sharedcms.content.structure.brush.Brushable;
import sharedcms.content.structure.brush.IBrush;
import sharedcms.content.structure.model.IModel;
import sharedcms.content.structure.model.IModelMaterial;
import sharedcms.content.structure.model.Model;
import sharedcms.content.structure.model.ModelMaterial;
import sharedcms.content.structure.model.SmoothingMode;
import sharedcms.content.world.decorator.DecorateArtifact;
import sharedcms.content.world.decorator.DecoratePodzolSludge;
import sharedcms.util.Location;

public class BiomeDecoratorRoofedForest extends AresBiomeDecorator
{
	public BiomeDecoratorRoofedForest(AresBiome biome)
	{
		super(biome);
		this.coalGen = Content.WorldGenerator.EMPTY;
		this.ironGen = Content.WorldGenerator.EMPTY;
		this.goldGen = Content.WorldGenerator.EMPTY;
		this.redstoneGen = Content.WorldGenerator.EMPTY;
		this.diamondGen = Content.WorldGenerator.EMPTY;
		this.lapisGen = Content.WorldGenerator.EMPTY;
		this.mushroomBrownGen = Content.WorldGenerator.EMPTY;
		this.mushroomRedGen = Content.WorldGenerator.EMPTY;
		this.bigMushroomGen = Content.WorldGenerator.EMPTY;
		this.reedGen = Content.WorldGenerator.EMPTY;
		this.cactusGen = Content.WorldGenerator.EMPTY;
		this.waterlilyGen = Content.WorldGenerator.EMPTY;
		this.flowersPerChunk = 12;
		this.grassPerChunk = 12;
		this.sandPerChunk = 0;
		this.sandPerChunk2 = 0;
		this.clayPerChunk = 0;
		this.generateLakes = false;
		this.treesPerChunk = 6;
		
		addDecorator(new DecoratePodzolSludge(12));
		addDecorator(new DecorateArtifact(1, 0.07, Content.Models.RUBBLE_ROCK, Content.Block.PODZOL_MOSSY));
		addDecorator(new DecorateArtifact(1, 0.19, Content.Models.RUBBLE_DIRT, Content.Block.PODZOL_MOSSY));
		addDecorator(new DecorateArtifact(1, 0.19, Content.Models.RUBBLE_DIRT, Content.Block.COLD_STONE));
	}
}
