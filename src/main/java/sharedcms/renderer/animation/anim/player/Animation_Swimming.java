package sharedcms.renderer.animation.anim.player;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import sharedcms.audio.SFX;
import sharedcms.renderer.animation.anim.Animation;
import sharedcms.renderer.animation.client.model.ModelRendererBends;
import sharedcms.renderer.animation.client.model.entity.ModelBendsPlayer;
import sharedcms.renderer.animation.data.Data_Player;
import sharedcms.renderer.animation.data.EntityData;
import sharedcms.renderer.animation.util.GUtil;
import sharedcms.renderer.animation.util.SmoothVector3f;

import org.lwjgl.util.vector.Vector3f;

public class Animation_Swimming
extends Animation {
    @Override
    public String getName() {
        return "swimming";
    }

    @Override
    public void animate(EntityLivingBase argEntity, ModelBase argModel, EntityData argData) {
        ModelBendsPlayer model = (ModelBendsPlayer)argModel;
        Data_Player data = (Data_Player)argData;
        float armSway = (MathHelper.cos((float)(data.ticks * 0.1625f)) + 1.0f) / 2.0f;
        float armSway2 = (- MathHelper.sin((float)(data.ticks * 0.1625f)) + 1.0f) / 2.0f;
        float legFlap = MathHelper.cos((float)(data.ticks * 0.4625f));
        float foreArmSway = (float)((double)(data.ticks * 0.1625f) % 6.283185307179586) / 6.2831855f;
        float foreArmStretch = armSway * 2.0f;
        foreArmStretch -= 1.0f;
        foreArmStretch = GUtil.min(foreArmStretch, 0.0f);

        if (data.motion.x == 0.0f & data.motion.z == 0.0f) {
            armSway = (MathHelper.cos((float)(data.ticks * 0.0825f)) + 1.0f) / 2.0f;
            armSway2 = (- MathHelper.sin((float)(data.ticks * 0.0825f)) + 1.0f) / 2.0f;
            legFlap = MathHelper.cos((float)(data.ticks * 0.2625f));
            ((ModelRendererBends)model.bipedHead).pre_rotation.setSmoothX(0.0f, 0.3f);
            ((ModelRendererBends)model.bipedLeftArm).rotation.setSmoothX(armSway2 * 30.0f - 15.0f, 0.3f);
            ((ModelRendererBends)model.bipedRightArm).rotation.setSmoothX(armSway2 * 30.0f - 15.0f, 0.3f);
            ((ModelRendererBends)model.bipedLeftArm).rotation.setSmoothZ((- armSway) * 30.0f);
            ((ModelRendererBends)model.bipedRightArm).rotation.setSmoothZ(armSway * 30.0f);
            ((ModelRendererBends)model.bipedLeftForeArm).rotation.setSmoothX(armSway2 * -40.0f, 0.3f);
            ((ModelRendererBends)model.bipedRightForeArm).rotation.setSmoothX(armSway2 * -40.0f, 0.3f);
            ((ModelRendererBends)model.bipedLeftLeg).rotation.setSmoothX(legFlap * 40.0f, 0.3f);
            ((ModelRendererBends)model.bipedRightLeg).rotation.setSmoothX((- legFlap) * 40.0f, 0.3f);
            ((ModelRendererBends)model.bipedLeftForeLeg).rotation.setSmoothX(5.0f, 0.4f);
            ((ModelRendererBends)model.bipedRightForeLeg).rotation.setSmoothX(5.0f, 0.4f);
            ((ModelRendererBends)model.bipedBody).rotation.setSmoothX(armSway * 10.0f);
            ((ModelRendererBends)model.bipedHead).rotation.setSmoothX(model.headRotationX);
            ((ModelRendererBends)model.bipedHead).rotation.setSmoothY(model.headRotationY);
        } else {
            ((ModelRendererBends)model.bipedHead).pre_rotation.setSmoothX(-70.0f - armSway * -20.0f, 0.3f);
            model.renderRotation.setSmoothX(70.0f, 0.3f);
            model.renderOffset.setSmoothZ(10.0f, 0.3f);
            ((ModelRendererBends)model.bipedLeftArm).pre_rotation.setSmoothY(90.0f, 0.3f);
            ((ModelRendererBends)model.bipedRightArm).pre_rotation.setSmoothY(-90.0f, 0.3f);
            ((ModelRendererBends)model.bipedLeftArm).rotation.setSmoothX(armSway * -120.0f - 45.0f, 0.3f);
            ((ModelRendererBends)model.bipedRightArm).rotation.setSmoothX(armSway * -120.0f - 45.0f, 0.3f);
            ((ModelRendererBends)model.bipedLeftArm).pre_rotation.setSmoothZ(armSway * -20.0f);
            ((ModelRendererBends)model.bipedRightArm).pre_rotation.setSmoothZ(- armSway * -20.0f);
            ((ModelRendererBends)model.bipedLeftForeArm).rotation.setSmoothX(foreArmSway < 0.55f | (double)foreArmSway > 0.9 ? foreArmStretch * -60.0f : -60.0f, 0.3f);
            ((ModelRendererBends)model.bipedRightForeArm).rotation.setSmoothX(foreArmSway < 0.55f | (double)foreArmSway > 0.9 ? foreArmStretch * -60.0f : -60.0f, 0.3f);
            ((ModelRendererBends)model.bipedLeftLeg).rotation.setSmoothX(legFlap * 40.0f, 0.3f);
            ((ModelRendererBends)model.bipedRightLeg).rotation.setSmoothX((- legFlap) * 40.0f, 0.3f);
            ((ModelRendererBends)model.bipedLeftForeLeg).rotation.setSmoothX(5.0f, 0.4f);
            ((ModelRendererBends)model.bipedRightForeLeg).rotation.setSmoothX(5.0f, 0.4f);
            ((ModelRendererBends)model.bipedBody).rotation.setSmoothX(armSway * -20.0f);
            ((ModelRendererBends)model.bipedHead).rotation.setSmoothX(model.headRotationX);
            ((ModelRendererBends)model.bipedHead).rotation.setSmoothY(model.headRotationY);
            model.renderItemRotation.setSmoothX(armSway * 120.0f, 0.3f);
        }
    }
}

