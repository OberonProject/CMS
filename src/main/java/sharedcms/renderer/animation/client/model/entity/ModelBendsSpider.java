/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.model.ModelSpider
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package sharedcms.renderer.animation.client.model.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import sharedcms.renderer.animation.client.model.ModelRendererBends;
import sharedcms.renderer.animation.data.Data_Spider;
import sharedcms.renderer.animation.util.SmoothVector3f;

import org.lwjgl.opengl.GL11;

public class ModelBendsSpider
extends ModelSpider {
    public ModelRenderer spiderHead;
    public ModelRenderer spiderNeck;
    public ModelRenderer spiderBody;
    public ModelRenderer spiderLeg1;
    public ModelRenderer spiderLeg2;
    public ModelRenderer spiderLeg3;
    public ModelRenderer spiderLeg4;
    public ModelRenderer spiderLeg5;
    public ModelRenderer spiderLeg6;
    public ModelRenderer spiderLeg7;
    public ModelRenderer spiderLeg8;
    public ModelRendererBends spiderForeLeg1;
    public ModelRendererBends spiderForeLeg2;
    public ModelRendererBends spiderForeLeg3;
    public ModelRendererBends spiderForeLeg4;
    public ModelRendererBends spiderForeLeg5;
    public ModelRendererBends spiderForeLeg6;
    public ModelRendererBends spiderForeLeg7;
    public ModelRendererBends spiderForeLeg8;
    public SmoothVector3f renderOffset = new SmoothVector3f();
    public SmoothVector3f renderRotation = new SmoothVector3f();
    public float headRotationX;
    public float headRotationY;

    public ModelBendsSpider() {
        float f = 0.0f;
        int b0 = 15;
        float legLength = 12.0f;
        float foreLegLength = 15.0f;
        float legRatio = 1.0f;
        float foreLegRatio = 1.0f;
        this.spiderHead = new ModelRendererBends((ModelBase)this, 32, 4);
        this.spiderHead.addBox(-4.0f, -4.0f, -8.0f, 8, 8, 8, f);
        this.spiderHead.setRotationPoint(0.0f, (float)b0, -3.0f);
        this.spiderNeck = new ModelRendererBends((ModelBase)this, 0, 0);
        this.spiderNeck.addBox(-3.0f, -3.0f, -3.0f, 6, 6, 6, f);
        this.spiderNeck.setRotationPoint(0.0f, (float)b0, 0.0f);
        this.spiderBody = new ModelRendererBends((ModelBase)this, 0, 12);
        this.spiderBody.addBox(-5.0f, -4.0f, -6.0f, 10, 8, 12, f);
        this.spiderBody.setRotationPoint(0.0f, (float)b0, 9.0f);
        this.spiderLeg1 = new ModelRendererBends((ModelBase)this, 18, 0);
        this.spiderLeg1.addBox(-7.0f, -1.0f, -1.0f, 8, 2, 2, f);
        this.spiderLeg1.setRotationPoint(-4.0f, (float)b0, 2.0f);
        ((ModelRendererBends)this.spiderLeg1).offsetBox_Add(8.0f - legLength, -0.01f, -0.01f).resizeBox(legLength, 2.02f, 2.02f).updateVertices();
        this.spiderLeg2 = new ModelRendererBends((ModelBase)this, 18, 0);
        this.spiderLeg2.addBox(-1.0f, -1.0f, -1.0f, 8, 2, 2, f);
        this.spiderLeg2.setRotationPoint(4.0f, (float)b0, 2.0f);
        ((ModelRendererBends)this.spiderLeg2).offsetBox_Add(0.0f, -0.01f, -0.01f).resizeBox(legLength, 2.02f, 2.02f).updateVertices();
        this.spiderLeg3 = new ModelRendererBends((ModelBase)this, 18, 0);
        this.spiderLeg3.addBox(-7.0f, -1.0f, -1.0f, 8, 2, 2, f);
        this.spiderLeg3.setRotationPoint(-4.0f, (float)b0, 1.0f);
        ((ModelRendererBends)this.spiderLeg3).offsetBox_Add(8.0f - legLength * legRatio, -0.01f, -0.01f).resizeBox(legLength * legRatio, 2.02f, 2.02f).updateVertices();
        this.spiderLeg4 = new ModelRendererBends((ModelBase)this, 18, 0);
        this.spiderLeg4.addBox(-1.0f, -1.0f, -1.0f, 8, 2, 2, f);
        this.spiderLeg4.setRotationPoint(4.0f, (float)b0, 1.0f);
        ((ModelRendererBends)this.spiderLeg4).offsetBox_Add(0.0f, -0.01f, -0.01f).resizeBox(legLength * legRatio, 2.02f, 2.02f).updateVertices();
        this.spiderLeg5 = new ModelRendererBends((ModelBase)this, 18, 0);
        this.spiderLeg5.addBox(-7.0f, -1.0f, -1.0f, 8, 2, 2, f);
        this.spiderLeg5.setRotationPoint(-4.0f, (float)b0, 0.0f);
        ((ModelRendererBends)this.spiderLeg5).offsetBox_Add(8.0f - legLength * legRatio, -0.01f, -0.01f).resizeBox(legLength * legRatio, 2.02f, 2.02f).updateVertices();
        this.spiderLeg6 = new ModelRendererBends((ModelBase)this, 18, 0);
        this.spiderLeg6.addBox(-1.0f, -1.0f, -1.0f, 8, 2, 2, f);
        this.spiderLeg6.setRotationPoint(4.0f, (float)b0, 0.0f);
        ((ModelRendererBends)this.spiderLeg6).offsetBox_Add(0.0f, -0.01f, -0.01f).resizeBox(legLength * legRatio, 2.02f, 2.02f).updateVertices();
        this.spiderLeg7 = new ModelRendererBends((ModelBase)this, 18, 0);
        this.spiderLeg7.addBox(-7.0f, -1.0f, -1.0f, 8, 2, 2, f);
        this.spiderLeg7.setRotationPoint(-4.0f, (float)b0, -1.0f);
        ((ModelRendererBends)this.spiderLeg7).offsetBox_Add(8.0f - legLength, -0.01f, -0.01f).resizeBox(legLength, 2.02f, 2.02f).updateVertices();
        this.spiderLeg8 = new ModelRendererBends((ModelBase)this, 18, 0);
        this.spiderLeg8.addBox(-1.0f, -1.0f, -1.0f, 8, 2, 2, f);
        this.spiderLeg8.setRotationPoint(4.0f, (float)b0, -1.0f);
        ((ModelRendererBends)this.spiderLeg8).offsetBox_Add(0.0f, -0.01f, -0.01f).resizeBox(legLength, 2.02f, 2.02f).updateVertices();
        this.spiderForeLeg1 = new ModelRendererBends((ModelBase)this, 18, 0);
        this.spiderForeLeg1.addBox(- foreLegLength, 0.0f, -1.0f, 8, 2, 2, f);
        this.spiderForeLeg1.setRotationPoint(- legLength + 1.0f, -1.0f, 0.0f);
        this.spiderForeLeg1.resizeBox(foreLegLength, 2.0f, 2.0f).updateVertices();
        this.spiderForeLeg2 = new ModelRendererBends((ModelBase)this, 18, 0);
        this.spiderForeLeg2.addBox(0.0f, 0.0f, -1.0f, 8, 2, 2, f);
        this.spiderForeLeg2.setRotationPoint(legLength - 1.0f, -1.0f, 0.0f);
        this.spiderForeLeg2.resizeBox(foreLegLength, 2.0f, 2.0f).updateVertices();
        this.spiderForeLeg3 = new ModelRendererBends((ModelBase)this, 18, 0);
        this.spiderForeLeg3.addBox((- foreLegLength) * foreLegRatio, 0.0f, -1.0f, 8, 2, 2, f);
        this.spiderForeLeg3.setRotationPoint((- legLength) * legRatio + 1.0f, -1.0f, 0.0f);
        this.spiderForeLeg3.resizeBox(foreLegLength * foreLegRatio, 2.0f, 2.0f).updateVertices();
        this.spiderForeLeg4 = new ModelRendererBends((ModelBase)this, 18, 0);
        this.spiderForeLeg4.addBox(0.0f, 0.0f, -1.0f, 8, 2, 2, f);
        this.spiderForeLeg4.setRotationPoint(legLength * legRatio - 1.0f, -1.0f, 0.0f);
        this.spiderForeLeg4.resizeBox(foreLegLength * foreLegRatio, 2.0f, 2.0f).updateVertices();
        this.spiderForeLeg5 = new ModelRendererBends((ModelBase)this, 18, 0);
        this.spiderForeLeg5.addBox((- foreLegLength) * foreLegRatio, 0.0f, -1.0f, 8, 2, 2, f);
        this.spiderForeLeg5.setRotationPoint((- legLength) * legRatio + 1.0f, -1.0f, 0.0f);
        this.spiderForeLeg5.resizeBox(foreLegLength * foreLegRatio, 2.0f, 2.0f).updateVertices();
        this.spiderForeLeg6 = new ModelRendererBends((ModelBase)this, 18, 0);
        this.spiderForeLeg6.addBox(0.0f, 0.0f, -1.0f, 8, 2, 2, f);
        this.spiderForeLeg6.setRotationPoint(legLength * legRatio - 1.0f, -1.0f, 0.0f);
        this.spiderForeLeg6.resizeBox(foreLegLength * foreLegRatio, 2.0f, 2.0f).updateVertices();
        this.spiderForeLeg7 = new ModelRendererBends((ModelBase)this, 18, 0);
        this.spiderForeLeg7.addBox(- foreLegLength, 0.0f, -1.0f, 8, 2, 2, f);
        this.spiderForeLeg7.setRotationPoint(- legLength + 1.0f, -1.0f, 0.0f);
        this.spiderForeLeg7.resizeBox(foreLegLength, 2.0f, 2.0f).updateVertices();
        this.spiderForeLeg8 = new ModelRendererBends((ModelBase)this, 18, 0);
        this.spiderForeLeg8.addBox(0.0f, 0.0f, -1.0f, 8, 2, 2, f);
        this.spiderForeLeg8.setRotationPoint(legLength - 1.0f, -1.0f, 0.0f);
        this.spiderForeLeg8.resizeBox(foreLegLength, 2.0f, 2.0f).updateVertices();
        this.spiderLeg1.addChild((ModelRenderer)this.spiderForeLeg1);
        this.spiderLeg2.addChild((ModelRenderer)this.spiderForeLeg2);
        this.spiderLeg3.addChild((ModelRenderer)this.spiderForeLeg3);
        this.spiderLeg4.addChild((ModelRenderer)this.spiderForeLeg4);
        this.spiderLeg5.addChild((ModelRenderer)this.spiderForeLeg5);
        this.spiderLeg6.addChild((ModelRenderer)this.spiderForeLeg6);
        this.spiderLeg7.addChild((ModelRenderer)this.spiderForeLeg7);
        this.spiderLeg8.addChild((ModelRenderer)this.spiderForeLeg8);
    }

    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.2f, (float)0.0f);
        this.spiderHead.render(p_78088_7_);
        this.spiderNeck.render(p_78088_7_);
        this.spiderBody.render(p_78088_7_);
        this.spiderLeg1.render(p_78088_7_);
        this.spiderLeg2.render(p_78088_7_);
        this.spiderLeg3.render(p_78088_7_);
        this.spiderLeg4.render(p_78088_7_);
        this.spiderLeg5.render(p_78088_7_);
        this.spiderLeg6.render(p_78088_7_);
        this.spiderLeg7.render(p_78088_7_);
        this.spiderLeg8.render(p_78088_7_);
        GL11.glPopMatrix();
    }

    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity argEntity) {
        if (Minecraft.getMinecraft().theWorld == null) {
            return;
        }
        if (Minecraft.getMinecraft().theWorld.isRemote && Minecraft.getMinecraft().isGamePaused()) {
            return;
        }
        Data_Spider data = Data_Spider.get(argEntity.getEntityId());
        this.headRotationX = p_78087_5_;
        this.headRotationY = p_78087_4_;
        ((ModelRendererBends)this.spiderHead).sync(data.spiderHead);
        ((ModelRendererBends)this.spiderNeck).sync(data.spiderNeck);
        ((ModelRendererBends)this.spiderBody).sync(data.spiderBody);
        ((ModelRendererBends)this.spiderLeg1).sync(data.spiderLeg1);
        ((ModelRendererBends)this.spiderLeg2).sync(data.spiderLeg2);
        ((ModelRendererBends)this.spiderLeg3).sync(data.spiderLeg3);
        ((ModelRendererBends)this.spiderLeg4).sync(data.spiderLeg4);
        ((ModelRendererBends)this.spiderLeg5).sync(data.spiderLeg5);
        ((ModelRendererBends)this.spiderLeg6).sync(data.spiderLeg6);
        ((ModelRendererBends)this.spiderLeg7).sync(data.spiderLeg7);
        ((ModelRendererBends)this.spiderLeg8).sync(data.spiderLeg8);
        this.spiderForeLeg1.sync(data.spiderForeLeg1);
        this.spiderForeLeg2.sync(data.spiderForeLeg2);
        this.spiderForeLeg3.sync(data.spiderForeLeg3);
        this.spiderForeLeg4.sync(data.spiderForeLeg4);
        this.spiderForeLeg5.sync(data.spiderForeLeg5);
        this.spiderForeLeg6.sync(data.spiderForeLeg6);
        this.spiderForeLeg7.sync(data.spiderForeLeg7);
        this.spiderForeLeg8.sync(data.spiderForeLeg8);
        this.renderOffset.set(data.renderOffset);
        this.renderRotation.set(data.renderRotation);
        if (Data_Spider.get(argEntity.getEntityId()).canBeUpdated()) {
            ((ModelRendererBends)this.spiderHead).resetScale();
            ((ModelRendererBends)this.spiderNeck).resetScale();
            ((ModelRendererBends)this.spiderBody).resetScale();
            ((ModelRendererBends)this.spiderLeg1).resetScale();
            ((ModelRendererBends)this.spiderLeg2).resetScale();
            ((ModelRendererBends)this.spiderLeg3).resetScale();
            ((ModelRendererBends)this.spiderLeg4).resetScale();
            ((ModelRendererBends)this.spiderLeg5).resetScale();
            ((ModelRendererBends)this.spiderLeg6).resetScale();
            ((ModelRendererBends)this.spiderLeg7).resetScale();
            ((ModelRendererBends)this.spiderLeg8).resetScale();
            this.spiderForeLeg1.resetScale();
            this.spiderForeLeg2.resetScale();
            this.spiderForeLeg3.resetScale();
            this.spiderForeLeg4.resetScale();
            this.spiderForeLeg5.resetScale();
            this.spiderForeLeg6.resetScale();
            this.spiderForeLeg7.resetScale();
            this.spiderForeLeg8.resetScale();
            float f9 = (- MathHelper.cos((float)(p_78087_1_ * 0.6662f * 2.0f + 0.0f)) * 0.4f) * p_78087_2_;
            float f10 = (- MathHelper.cos((float)(p_78087_1_ * 0.6662f * 2.0f + 3.1415927f)) * 0.4f) * p_78087_2_;
            float f11 = (- MathHelper.cos((float)(p_78087_1_ * 0.6662f * 2.0f + 1.5707964f)) * 0.4f) * p_78087_2_;
            float f12 = (- MathHelper.cos((float)(p_78087_1_ * 0.6662f * 2.0f + 4.712389f)) * 0.4f) * p_78087_2_;
            float f13 = Math.abs(MathHelper.sin((float)(p_78087_1_ * 0.6662f + 0.0f)) * 0.4f) * p_78087_2_;
            float f14 = Math.abs(MathHelper.sin((float)(p_78087_1_ * 0.6662f + 3.1415927f)) * 0.4f) * p_78087_2_;
            float f15 = Math.abs(MathHelper.sin((float)(p_78087_1_ * 0.6662f + 1.5707964f)) * 0.4f) * p_78087_2_;
            float f16 = Math.abs(MathHelper.sin((float)(p_78087_1_ * 0.6662f + 4.712389f)) * 0.4f) * p_78087_2_;
            ((ModelRendererBends)this.spiderHead).rotation.setY(p_78087_4_);
            ((ModelRendererBends)this.spiderHead).rotation.setX(p_78087_5_);
            float f6 = 0.7853982f;
            float sm = 40.0f;
            ((ModelRendererBends)this.spiderLeg1).rotation.setZ(sm + f13 / 3.1415927f * 180.0f);
            ((ModelRendererBends)this.spiderLeg2).rotation.setZ(- sm - f13 / 3.1415927f * 180.0f);
            ((ModelRendererBends)this.spiderLeg3).rotation.setZ(sm + f14 / 3.1415927f * 180.0f);
            ((ModelRendererBends)this.spiderLeg4).rotation.setZ(- sm - f14 / 3.1415927f * 180.0f);
            ((ModelRendererBends)this.spiderLeg5).rotation.setZ(sm + f15 / 3.1415927f * 180.0f);
            ((ModelRendererBends)this.spiderLeg6).rotation.setZ(- sm - f15 / 3.1415927f * 180.0f);
            ((ModelRendererBends)this.spiderLeg7).rotation.setZ(sm + f16 / 3.1415927f * 180.0f);
            ((ModelRendererBends)this.spiderLeg8).rotation.setZ(- sm - f16 / 3.1415927f * 180.0f);
            float f7 = -0.0f;
            float f8 = 0.3926991f;
            ((ModelRendererBends)this.spiderLeg1).pre_rotation.setY(-70.0f + f9 / 3.1415927f * 180.0f);
            ((ModelRendererBends)this.spiderLeg2).pre_rotation.setY(70.0f - f9 / 3.1415927f * 180.0f);
            ((ModelRendererBends)this.spiderLeg3).pre_rotation.setY(-40.0f + f10 / 3.1415927f * 180.0f);
            ((ModelRendererBends)this.spiderLeg4).pre_rotation.setY(40.0f - f10 / 3.1415927f * 180.0f);
            ((ModelRendererBends)this.spiderLeg5).pre_rotation.setY(40.0f + f11 / 3.1415927f * 180.0f);
            ((ModelRendererBends)this.spiderLeg6).pre_rotation.setY(-40.0f - f11 / 3.1415927f * 180.0f);
            ((ModelRendererBends)this.spiderLeg7).pre_rotation.setY(70.0f + f12 / 3.1415927f * 180.0f);
            ((ModelRendererBends)this.spiderLeg8).pre_rotation.setY(-70.0f - f12 / 3.1415927f * 180.0f);
            float foreBend = 89.0f;
            this.spiderForeLeg1.rotation.setSmoothZ(- foreBend);
            this.spiderForeLeg2.rotation.setSmoothZ(foreBend);
            this.spiderForeLeg3.rotation.setSmoothZ(- foreBend);
            this.spiderForeLeg4.rotation.setSmoothZ(foreBend);
            this.spiderForeLeg5.rotation.setSmoothZ(- foreBend);
            this.spiderForeLeg6.rotation.setSmoothZ(foreBend);
            this.spiderForeLeg7.rotation.setSmoothZ(- foreBend);
            this.spiderForeLeg8.rotation.setSmoothZ(foreBend);
            ((ModelRendererBends)this.spiderBody).rotation.setX(-30.0f);
            ((ModelRendererBends)this.spiderHead).update(p_78087_6_);
            ((ModelRendererBends)this.spiderNeck).update(p_78087_6_);
            ((ModelRendererBends)this.spiderBody).update(p_78087_6_);
            ((ModelRendererBends)this.spiderLeg1).update(p_78087_6_);
            ((ModelRendererBends)this.spiderLeg2).update(p_78087_6_);
            ((ModelRendererBends)this.spiderLeg3).update(p_78087_6_);
            ((ModelRendererBends)this.spiderLeg4).update(p_78087_6_);
            ((ModelRendererBends)this.spiderLeg5).update(p_78087_6_);
            ((ModelRendererBends)this.spiderLeg6).update(p_78087_6_);
            ((ModelRendererBends)this.spiderLeg7).update(p_78087_6_);
            ((ModelRendererBends)this.spiderLeg8).update(p_78087_6_);
            this.spiderForeLeg1.update(p_78087_6_);
            this.spiderForeLeg2.update(p_78087_6_);
            this.spiderForeLeg3.update(p_78087_6_);
            this.spiderForeLeg4.update(p_78087_6_);
            this.spiderForeLeg5.update(p_78087_6_);
            this.spiderForeLeg6.update(p_78087_6_);
            this.spiderForeLeg7.update(p_78087_6_);
            this.spiderForeLeg8.update(p_78087_6_);
            data.updatedThisFrame = true;
        }
        Data_Spider.get(argEntity.getEntityId()).syncModelInfo(this);
    }
}

