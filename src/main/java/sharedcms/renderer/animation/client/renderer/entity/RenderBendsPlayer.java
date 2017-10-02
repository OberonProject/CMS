/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.common.eventhandler.EventBus
 *  net.minecraft.block.Block
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.entity.RenderBiped
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.projectile.EntityFishHook
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTUtil
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StringUtils
 *  net.minecraftforge.client.ForgeHooksClient
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  net.minecraftforge.client.MinecraftForgeClient
 *  net.minecraftforge.client.event.RenderPlayerEvent
 *  net.minecraftforge.client.event.RenderPlayerEvent$SetArmorModel
 *  net.minecraftforge.client.event.RenderPlayerEvent$Specials
 *  net.minecraftforge.client.event.RenderPlayerEvent$Specials$Post
 *  net.minecraftforge.client.event.RenderPlayerEvent$Specials$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.util.vector.Vector3f
 */
package sharedcms.renderer.animation.client.renderer.entity;

import com.mojang.authlib.GameProfile;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventBus;
import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import sharedcms.Info;
import sharedcms.renderer.animation.MoBends;
import sharedcms.renderer.animation.client.model.ModelCustomArmor;
import sharedcms.renderer.animation.client.model.entity.ModelBendsPlayer;
import sharedcms.renderer.animation.client.renderer.SwordTrail;
import sharedcms.renderer.animation.customarmor.CustomArmor;
import sharedcms.renderer.animation.data.Data_Player;
import sharedcms.renderer.animation.settings.SettingsBoolean;
import sharedcms.renderer.animation.settings.SettingsNode;
import sharedcms.renderer.animation.util.SmoothVector3f;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

public class RenderBendsPlayer
extends RenderPlayer {
    public int refreshModel = 0;

    public RenderBendsPlayer() {
        this.mainModel = new ModelBendsPlayer(0.0f);
        this.modelBipedMain = (ModelBendsPlayer)this.mainModel;
        this.modelArmorChestplate = new ModelBendsPlayer(1.0f);
        this.modelArmor = new ModelBendsPlayer(0.5f);
    }

    protected int shouldRenderPass(AbstractClientPlayer p_77032_1_, int p_77032_2_, float p_77032_3_) {
        Item item;
        ItemStack itemstack = p_77032_1_.inventory.armorItemInSlot(3 - p_77032_2_);
        RenderPlayerEvent.SetArmorModel event = new RenderPlayerEvent.SetArmorModel((EntityPlayer)p_77032_1_, (RenderPlayer)this, 3 - p_77032_2_, p_77032_3_, itemstack);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (event.result != -1) {
            return event.result;
        }
        if (itemstack != null && (item = itemstack.getItem()) instanceof ItemArmor) {
            ItemArmor itemarmor = (ItemArmor)item;
            ResourceLocation texture = RenderBiped.getArmorResource((Entity)p_77032_1_, (ItemStack)itemstack, (int)p_77032_2_, (String)null);
            this.bindTexture(texture);
            ModelBiped modelbiped = p_77032_2_ == 2 ? this.modelArmor : this.modelArmorChestplate;
            modelbiped.bipedHead.showModel = p_77032_2_ == 0;
            modelbiped.bipedHeadwear.showModel = p_77032_2_ == 0;
            modelbiped.bipedBody.showModel = p_77032_2_ == 1 || p_77032_2_ == 2;
            modelbiped.bipedRightArm.showModel = p_77032_2_ == 1;
            modelbiped.bipedLeftArm.showModel = p_77032_2_ == 1;
            modelbiped.bipedRightLeg.showModel = p_77032_2_ == 2 || p_77032_2_ == 3;
            modelbiped.bipedLeftLeg.showModel = p_77032_2_ == 2 || p_77032_2_ == 3;
            modelbiped = ForgeHooksClient.getArmorModel((EntityLivingBase)p_77032_1_, (ItemStack)itemstack, (int)p_77032_2_, (ModelBiped)modelbiped);
            this.setRenderPassModel((ModelBase)modelbiped);
            modelbiped.onGround = this.mainModel.onGround;
            modelbiped.isRiding = this.mainModel.isRiding;
            modelbiped.isChild = this.mainModel.isChild;
            modelbiped = CustomArmor.get((ModelBiped)modelbiped, (String)texture.getResourcePath(), (float)(p_77032_2_ == 2 ? 0.5f : 1.0f)).armorModel;
            int j = itemarmor.getColor(itemstack);
            if (j != -1) {
                float f1 = (float)(j >> 16 & 255) / 255.0f;
                float f2 = (float)(j >> 8 & 255) / 255.0f;
                float f3 = (float)(j & 255) / 255.0f;
                GL11.glColor3f((float)f1, (float)f2, (float)f3);
                if (itemstack.isItemEnchanted()) {
                    return 31;
                }
                return 16;
            }
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            if (itemstack.isItemEnchanted()) {
                return 15;
            }
            return 1;
        }
        return -1;
    }

    public void renderFirstPersonArm(EntityPlayer p_82441_1_) {
        float f = 1.0f;
        GL11.glColor3f((float)f, (float)f, (float)f);
        ModelBiped model = new ModelBiped();
        model.onGround = 0.0f;
        model.setRotationAngles(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)p_82441_1_);
        model.bipedRightArm.render(0.0625f);
    }

    private float interpolateRotation(float p_77034_1_, float p_77034_2_, float p_77034_3_) {
        float f3;
        for (f3 = p_77034_2_ - p_77034_1_; f3 < -180.0f; f3 += 360.0f) {
        }
        while (f3 >= 180.0f) {
            f3 -= 360.0f;
        }
        return p_77034_1_ + p_77034_3_ * f3;
    }

    protected void rotateSuperCorpse(EntityLivingBase p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
        GL11.glRotatef((float)(180.0f - p_77043_3_), (float)0.0f, (float)1.0f, (float)0.0f);
        if (p_77043_1_.deathTime > 0) {
            float f3 = ((float)p_77043_1_.deathTime + p_77043_4_ - 1.0f) / 20.0f * 1.6f;
            if ((f3 = MathHelper.sqrt_float((float)f3)) > 1.0f) {
                f3 = 1.0f;
            }
            GL11.glRotatef((float)(f3 * this.getDeathMaxRotation(p_77043_1_)), (float)0.0f, (float)0.0f, (float)1.0f);
        } else {
            String s = EnumChatFormatting.getTextWithoutFormattingCodes((String)p_77043_1_.getCommandSenderName());
            if (!(!s.equals("Dinnerbone") && !s.equals("Grumm") || p_77043_1_ instanceof EntityPlayer && ((EntityPlayer)p_77043_1_).getHideCape())) {
                GL11.glTranslatef((float)0.0f, (float)(p_77043_1_.height + 0.1f), (float)0.0f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            }
        }
    }

    @SuppressWarnings("unused")
	public void doRender(AbstractClientPlayer p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        if (this.refreshModel != MoBends.refreshModel) {
            this.mainModel = new ModelBendsPlayer(0.0f);
            this.modelBipedMain = (ModelBendsPlayer)this.mainModel;
            this.modelArmorChestplate = new ModelBendsPlayer(1.0f);
            this.modelArmor = new ModelBendsPlayer(0.5f);
            this.refreshModel = MoBends.refreshModel;
        }
        Data_Player data = Data_Player.get(p_76986_1_.getEntityId());
        float f2 = this.interpolateRotation(p_76986_1_.prevRenderYawOffset, p_76986_1_.renderYawOffset, p_76986_9_);
        float f3 = this.interpolateRotation(p_76986_1_.prevRotationYawHead, p_76986_1_.rotationYawHead, p_76986_9_);
        if (((SettingsBoolean)SettingsNode.getSetting((String)"swordTrail")).data && Info.WEAPON_TRAIL) {
            GL11.glPushMatrix();
            float f5 = 0.0625f;
            float f4 = this.handleRotationFloat((EntityLivingBase)p_76986_1_, p_76986_9_);
            this.rotateSuperCorpse((EntityLivingBase)p_76986_1_, f4, f2, p_76986_9_);
            GL11.glTranslatef((float)0.0f, (float)(-24.0f * f5 - 0.0078125f - 2.0f * f5), (float)0.0f);
            GL11.glScalef((float)f5, (float)f5, (float)f5);
            data.swordTrail.render((ModelBendsPlayer)this.mainModel);
            GL11.glPopMatrix();
        }
        super.doRender(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    protected void rotateCorpse(AbstractClientPlayer argPlayer, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
        if (argPlayer.isEntityAlive() && argPlayer.isPlayerSleeping()) {
            GL11.glRotatef((float)argPlayer.getBedOrientationInDegrees(), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)this.getDeathMaxRotation((EntityLivingBase)argPlayer), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)270.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        } else {
            super.rotateCorpse(argPlayer, p_77043_2_, p_77043_3_, p_77043_4_);
            ((ModelBendsPlayer)this.modelBipedMain).updateWithEntityData(argPlayer);
            ((ModelBendsPlayer)this.modelBipedMain).postRender(0.0625f);
        }
    }

    protected void renderEquippedItems(AbstractClientPlayer argPlayer, float argPartialTicks) {
        ItemStack itemstack1;
        float f2;
        RenderPlayerEvent.Specials.Pre event = new RenderPlayerEvent.Specials.Pre((EntityPlayer)argPlayer, (RenderPlayer)this, argPartialTicks);
        if (MinecraftForge.EVENT_BUS.post((Event)event)) {
            return;
        }
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        ItemStack itemstack = argPlayer.inventory.armorItemInSlot(3);
        if (itemstack != null && event.renderHelmet) {
            float f1;
            GL11.glPushMatrix();
            this.modelBipedMain.bipedBody.postRender(0.0625f);
            this.modelBipedMain.bipedHead.postRender(0.0625f);
            if (itemstack.getItem() instanceof ItemBlock) {
                boolean is3D;
                IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer((ItemStack)itemstack, (IItemRenderer.ItemRenderType)IItemRenderer.ItemRenderType.EQUIPPED);
                boolean bl = is3D = customRenderer != null && customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack, IItemRenderer.ItemRendererHelper.BLOCK_3D);
                if (is3D || RenderBlocks.renderItemIn3d((int)Block.getBlockFromItem((Item)itemstack.getItem()).getRenderType())) {
                    f1 = 0.625f;
                    GL11.glTranslatef((float)0.0f, (float)-0.25f, (float)0.0f);
                    GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glScalef((float)f1, (float)(- f1), (float)(- f1));
                }
                this.renderManager.itemRenderer.renderItem((EntityLivingBase)argPlayer, itemstack, 0);
            } else if (itemstack.getItem() == Items.skull) {
                f1 = 1.0625f;
                GL11.glScalef((float)f1, (float)(- f1), (float)(- f1));
                GameProfile gameprofile = null;
                if (itemstack.hasTagCompound()) {
                    NBTTagCompound nbttagcompound = itemstack.getTagCompound();
                    if (nbttagcompound.hasKey("SkullOwner", 10)) {
                        gameprofile = NBTUtil.func_152459_a((NBTTagCompound)nbttagcompound.getCompoundTag("SkullOwner"));
                    } else if (nbttagcompound.hasKey("SkullOwner", 8) && !StringUtils.isNullOrEmpty((String)nbttagcompound.getString("SkullOwner"))) {
                        gameprofile = new GameProfile((UUID)null, nbttagcompound.getString("SkullOwner"));
                    }
                }
                TileEntitySkullRenderer.field_147536_b.func_152674_a(-0.5f, 0.0f, -0.5f, 1, 180.0f, itemstack.getItemDamage(), gameprofile);
            }
            GL11.glPopMatrix();
        }
        if (argPlayer.getCommandSenderName().equals("deadmau5") && argPlayer.func_152123_o()) {
            this.bindTexture(argPlayer.getLocationSkin());
            for (int j = 0; j < 2; ++j) {
                float f9 = argPlayer.prevRotationYaw + (argPlayer.rotationYaw - argPlayer.prevRotationYaw) * argPartialTicks - (argPlayer.prevRenderYawOffset + (argPlayer.renderYawOffset - argPlayer.prevRenderYawOffset) * argPartialTicks);
                float f10 = argPlayer.prevRotationPitch + (argPlayer.rotationPitch - argPlayer.prevRotationPitch) * argPartialTicks;
                GL11.glPushMatrix();
                GL11.glRotatef((float)f9, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)f10, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glTranslatef((float)(0.375f * (float)(j * 2 - 1)), (float)0.0f, (float)0.0f);
                GL11.glTranslatef((float)0.0f, (float)-0.375f, (float)0.0f);
                GL11.glRotatef((float)(- f10), (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)(- f9), (float)0.0f, (float)1.0f, (float)0.0f);
                f2 = 1.3333334f;
                GL11.glScalef((float)f2, (float)f2, (float)f2);
                this.modelBipedMain.renderEars(0.0625f);
                GL11.glPopMatrix();
            }
        }
        boolean flag = argPlayer.func_152122_n();
        boolean bl = flag = event.renderCape && flag;
        if (flag && !argPlayer.isInvisible() && !argPlayer.getHideCape()) {
            this.bindTexture(argPlayer.getLocationCape());
            GL11.glPushMatrix();
            this.modelBipedMain.bipedBody.postRender(0.0625f);
            GL11.glTranslatef((float)0.0f, (float)-0.75f, (float)0.125f);
            double d3 = argPlayer.field_71091_bM + (argPlayer.field_71094_bP - argPlayer.field_71091_bM) * (double)argPartialTicks - (argPlayer.prevPosX + (argPlayer.posX - argPlayer.prevPosX) * (double)argPartialTicks);
            double d4 = argPlayer.field_71096_bN + (argPlayer.field_71095_bQ - argPlayer.field_71096_bN) * (double)argPartialTicks - (argPlayer.prevPosY + (argPlayer.posY - argPlayer.prevPosY) * (double)argPartialTicks);
            double d0 = argPlayer.field_71097_bO + (argPlayer.field_71085_bR - argPlayer.field_71097_bO) * (double)argPartialTicks - (argPlayer.prevPosZ + (argPlayer.posZ - argPlayer.prevPosZ) * (double)argPartialTicks);
            float f4 = argPlayer.prevRenderYawOffset + (argPlayer.renderYawOffset - argPlayer.prevRenderYawOffset) * argPartialTicks;
            double d1 = MathHelper.sin((float)(f4 * 3.1415927f / 180.0f));
            double d2 = - MathHelper.cos((float)(f4 * 3.1415927f / 180.0f));
            float f5 = (float)d4 * 10.0f;
            if (f5 < -6.0f) {
                f5 = -6.0f;
            }
            if (f5 > 32.0f) {
                f5 = 32.0f;
            }
            float f6 = (float)(d3 * d1 + d0 * d2) * 100.0f;
            float f7 = (float)(d3 * d2 - d0 * d1) * 100.0f;
            if (f6 < 0.0f) {
                f6 = 0.0f;
            }
            float f8 = argPlayer.prevCameraYaw + (argPlayer.cameraYaw - argPlayer.prevCameraYaw) * argPartialTicks;
            f5 += MathHelper.sin((float)((argPlayer.prevDistanceWalkedModified + (argPlayer.distanceWalkedModified - argPlayer.prevDistanceWalkedModified) * argPartialTicks) * 6.0f)) * 32.0f * f8;
            if (argPlayer.isSneaking()) {
                f5 += 25.0f;
            }
            GL11.glRotatef((float)(6.0f + f6 / 2.0f + f5), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)(f7 / 2.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)((- f7) / 2.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            this.modelBipedMain.renderCloak(0.0625f);
            GL11.glPopMatrix();
        }
        if ((itemstack1 = argPlayer.inventory.getCurrentItem()) != null && event.renderItem) {
            IItemRenderer customRenderer;
            boolean is3D;
            GL11.glPushMatrix();
            this.modelBipedMain.bipedRightArm.postRender(0.0625f);
            GL11.glTranslatef((float)0.0f, (float)0.5625f, (float)0.0f);
            if (this.modelBipedMain instanceof ModelBendsPlayer) {
                GL11.glRotatef((float)((ModelBendsPlayer)this.modelBipedMain).renderItemRotation.vSmooth.x, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)((ModelBendsPlayer)this.modelBipedMain).renderItemRotation.vSmooth.y, (float)0.0f, (float)-1.0f, (float)0.0f);
                GL11.glRotatef((float)((ModelBendsPlayer)this.modelBipedMain).renderItemRotation.vSmooth.z, (float)0.0f, (float)0.0f, (float)1.0f);
            }
            GL11.glTranslatef((float)-0.0625f, (float)-0.125f, (float)0.0625f);
            if (argPlayer.fishEntity != null) {
                itemstack1 = new ItemStack(Items.stick);
            }
            EnumAction enumaction = null;
            if (argPlayer.getItemInUseCount() > 0) {
                enumaction = itemstack1.getItemUseAction();
            }
            boolean bl2 = is3D = (customRenderer = MinecraftForgeClient.getItemRenderer((ItemStack)itemstack1, (IItemRenderer.ItemRenderType)IItemRenderer.ItemRenderType.EQUIPPED)) != null && customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack1, IItemRenderer.ItemRendererHelper.BLOCK_3D);
            if (is3D || itemstack1.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d((int)Block.getBlockFromItem((Item)itemstack1.getItem()).getRenderType())) {
                f2 = 0.5f;
                GL11.glTranslatef((float)0.0f, (float)0.1875f, (float)-0.3125f);
                GL11.glRotatef((float)20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glScalef((float)(- f2), (float)(- f2), (float)(f2 *= 0.75f));
            } else if (itemstack1.getItem() == Items.bow) {
                f2 = 0.625f;
                GL11.glTranslatef((float)0.0f, (float)0.125f, (float)0.3125f);
                GL11.glRotatef((float)-20.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glScalef((float)f2, (float)(- f2), (float)f2);
                GL11.glRotatef((float)-100.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            } else if (itemstack1.getItem().isFull3D()) {
                f2 = 0.625f;
                if (itemstack1.getItem().shouldRotateAroundWhenRendering()) {
                    GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glTranslatef((float)0.0f, (float)-0.125f, (float)0.0f);
                }
                if (argPlayer.getItemInUseCount() > 0 && enumaction == EnumAction.block) {
                    GL11.glTranslatef((float)0.05f, (float)0.0f, (float)-0.1f);
                    GL11.glRotatef((float)-50.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)-10.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GL11.glRotatef((float)-60.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                }
                GL11.glTranslatef((float)0.0f, (float)0.1875f, (float)0.0f);
                GL11.glScalef((float)f2, (float)(- f2), (float)f2);
                GL11.glRotatef((float)-100.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            } else {
                f2 = 0.375f;
                GL11.glTranslatef((float)0.25f, (float)0.1875f, (float)-0.1875f);
                GL11.glScalef((float)f2, (float)f2, (float)f2);
                GL11.glRotatef((float)60.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            }
            if (itemstack1.getItem().requiresMultipleRenderPasses()) {
                for (int k = 0; k < itemstack1.getItem().getRenderPasses(itemstack1.getItemDamage()); ++k) {
                    int i = itemstack1.getItem().getColorFromItemStack(itemstack1, k);
                    float f12 = (float)(i >> 16 & 255) / 255.0f;
                    float f3 = (float)(i >> 8 & 255) / 255.0f;
                    float f4 = (float)(i & 255) / 255.0f;
                    GL11.glColor4f((float)f12, (float)f3, (float)f4, (float)1.0f);
                    this.renderManager.itemRenderer.renderItem((EntityLivingBase)argPlayer, itemstack1, k);
                }
            } else {
                int k = itemstack1.getItem().getColorFromItemStack(itemstack1, 0);
                float f11 = (float)(k >> 16 & 255) / 255.0f;
                float f12 = (float)(k >> 8 & 255) / 255.0f;
                float f3 = (float)(k & 255) / 255.0f;
                GL11.glColor4f((float)f11, (float)f12, (float)f3, (float)1.0f);
                this.renderManager.itemRenderer.renderItem((EntityLivingBase)argPlayer, itemstack1, 0);
            }
            GL11.glPopMatrix();
        }
        MinecraftForge.EVENT_BUS.post((Event)new RenderPlayerEvent.Specials.Post((EntityPlayer)argPlayer, (RenderPlayer)this, argPartialTicks));
    }
}

