package sharedcms.controller.shared;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import sharedcms.Ares;
import sharedcms.Info;
import sharedcms.L;
import sharedcms.base.AresBiome;
import sharedcms.base.AresBiomeDecorator;
import sharedcms.base.AresEffect;
import sharedcms.base.AresEntitySet;
import sharedcms.base.AresGenLayerBiome;
import sharedcms.base.AresWorldGenerator;
import sharedcms.base.AresWorldType;
import sharedcms.base.TabIconChange;
import sharedcms.content.Content;
import sharedcms.controllable.Controller;
import sharedcms.controllable.ControllerIncapableSideException;
import sharedcms.controller.client.FXController;
import sharedcms.fx.BlockEffect;
import sharedcms.registry.BaseRegistrar;
import sharedcms.registry.IRegistrant;
import sharedcms.registry.IRegistrar;
import sharedcms.registry.RegistryPhase;
import sharedcns.api.biome.LudicrousBiome;

public class ContentController extends Controller
{
	private static List<IRegistrant> registrants = new ArrayList<IRegistrant>();
	private Map<IRegistrar, List<Object>> registrarQueue;

	public ContentController()
	{
		registrarQueue = new HashMap<IRegistrar, List<Object>>();
		createDefaultRegistrars();
		Content.init();
		addRegistrant(new Content());
	}

	@Override
	public void onPreInitialization()
	{
		for(IRegistrant i : getRegistrants())
		{
			i.onPreRegister(this, getControllerSide());
		}
		
		registerFor(RegistryPhase.PRE_INIT);
	}

	@Override
	public void onInitialization()
	{
		registerFor(RegistryPhase.INIT);
	}

	@Override
	public void onPostInitialization()
	{
		registerFor(RegistryPhase.POST_INIT);
	}

	public void createRegistrar(IRegistrar r)
	{
		L.l(this, "Adding Registrar " + r.getRegistryClassType().getSimpleName() + " target:" + r.getRegistryClassType().getSimpleName() + " phase:" + r.getPhase());
		registrarQueue.put(r, new ArrayList<Object>());
	}

	public void register(Object o)
	{
		for(IRegistrar i : registrarQueue.keySet())
		{
			if(i.getRegistryClassType().isAssignableFrom(o.getClass()))
			{
				L.l(this, "Queued " + o.getClass().getSimpleName() + "<" + i.getRegistryClassType().getSimpleName() + "> to the " + i.getRegistryClassType().getSimpleName() + " registry.");
				registrarQueue.get(i).add(o);
			}
		}
	}

	private void createDefaultRegistrars()
	{
		createRegistrar(new BaseRegistrar<Block>(Block.class, RegistryPhase.PRE_INIT)
		{
			@Override
			public void register(Block o)
			{
				GameRegistry.registerBlock(o, o.getUnlocalizedName().substring(5));
			}
		});

		createRegistrar(new BaseRegistrar<Item>(Item.class, RegistryPhase.PRE_INIT)
		{
			@Override
			public void register(Item t)
			{
				GameRegistry.registerItem(t, t.getUnlocalizedName().substring(5));
				t.setTextureName(Info.ID + ":" + t.getUnlocalizedName().substring(5));
			}
		});

		createRegistrar(new BaseRegistrar<AresEntitySet>(AresEntitySet.class, RegistryPhase.PRE_INIT)
		{
			@Override
			public void register(AresEntitySet t)
			{
				int colorBackground = Integer.parseInt(t.getBackgroundColor(), 16);
				int colorSpot = Integer.parseInt(t.getSpotColor(), 16);
				int rid = EntityRegistry.findGlobalUniqueEntityId();
				EntityRegistry.registerGlobalEntityID(t.getE(), t.getName(), rid);
				EntityRegistry.registerModEntity(t.getE(), t.getName(), rid, Ares.instance, 24, 1, true);
				EntityList.entityEggs.put(Integer.valueOf(rid), new EntityList.EntityEggInfo(rid, colorBackground, colorSpot));
			}
		});

		createRegistrar(new BaseRegistrar<TabIconChange>(TabIconChange.class, RegistryPhase.PRE_INIT)
		{
			@Override
			public void register(TabIconChange o)
			{
				o.apply();
			}
		});

		createRegistrar(new BaseRegistrar<LudicrousBiome>(LudicrousBiome.class, RegistryPhase.PRE_INIT)
		{
			@Override
			public void register(LudicrousBiome o)
			{
				o.postInit();
			}
		});

		createRegistrar(new BaseRegistrar<AresWorldType>(AresWorldType.class, RegistryPhase.PRE_INIT)
		{
			@Override
			public void register(AresWorldType o)
			{

			}
		});

		createRegistrar(new BaseRegistrar<AresGenLayerBiome>(AresGenLayerBiome.class, RegistryPhase.PRE_INIT)
		{
			@Override
			public void register(AresGenLayerBiome o)
			{

			}
		});

		createRegistrar(new BaseRegistrar<AresBiomeDecorator>(AresBiomeDecorator.class, RegistryPhase.PRE_INIT)
		{
			@Override
			public void register(AresBiomeDecorator o)
			{

			}
		});

		createRegistrar(new BaseRegistrar<AresWorldGenerator>(AresWorldGenerator.class, RegistryPhase.PRE_INIT)
		{
			@Override
			public void register(AresWorldGenerator o)
			{

			}
		});

		createRegistrar(new BaseRegistrar<AresEffect>(AresEffect.class, RegistryPhase.INIT)
		{
			@Override
			public void register(AresEffect o)
			{

			}
		});
		
		createRegistrar(new BaseRegistrar<BlockEffect>(BlockEffect.class, RegistryPhase.PRE_INIT)
		{
			@Override
			public void register(BlockEffect o)
			{
				FXController.addEffect(o);
			}
		});
	}

	private void registerFor(RegistryPhase phase)
	{
		L.l(this, "Begin Registration Phase: " + phase);
		for(IRegistrar i : registrarQueue.keySet())
		{
			if(i.getPhase().equals(phase))
			{
				List<Object> oms = new ArrayList<Object>(registrarQueue.get(i));

				L.l("Registering " + oms.size() + " queued " + i.getRegistryClassType().getSimpleName() + "s...");
				registrarQueue.get(i).clear();

				for(Object j : oms)
				{
					i.register(j);
					L.l(this, "Registered " + j.getClass().getSimpleName() + "<" + i.getRegistryClassType().getSimpleName() + "> to the " + i.getRegistryClassType().getSimpleName() + " registry.");
				}
			}
		}
	}

	public static void addRegistrant(IRegistrant r)
	{
		L.l("Adding " + r.getClass().getSimpleName() + " to ProxyCMS registry bus");
		registrants.add(r);
	}

	public static List<IRegistrant> getRegistrants()
	{
		return registrants;
	}

}
