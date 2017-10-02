package sharedcms.renderer.animation.anim.player;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.player.EntityPlayer;
import sharedcms.renderer.animation.client.model.ModelRendererBends;
import sharedcms.renderer.animation.client.model.entity.ModelBendsPlayer;
import sharedcms.renderer.animation.data.Data_Player;
import sharedcms.renderer.animation.util.SmoothVector3f;

import org.lwjgl.util.vector.Vector3f;

public class Animation_Attack_PunchStance {
    public static void animate(EntityPlayer player, ModelBendsPlayer model, Data_Player data) {
        if (!(data.motion.x == 0.0f & data.motion.z == 0.0f)) {
            return;
        }
        model.renderRotation.setSmoothY(20.0f);
        model.renderOffset.setSmoothY(-2.0f);
        ((ModelRendererBends)model.bipedRightArm).rotation.setSmoothX(-90.0f, 0.3f);
        ((ModelRendererBends)model.bipedRightForeArm).rotation.setSmoothX(-80.0f, 0.3f);
        ((ModelRendererBends)model.bipedLeftArm).rotation.setSmoothX(-90.0f, 0.3f);
        ((ModelRendererBends)model.bipedLeftForeArm).rotation.setSmoothX(-80.0f, 0.3f);
        ((ModelRendererBends)model.bipedRightArm).rotation.setSmoothZ(20.0f, 0.3f);
        ((ModelRendererBends)model.bipedLeftArm).rotation.setSmoothZ(-20.0f, 0.3f);
        ((ModelRendererBends)model.bipedBody).rotation.setSmoothX(10.0f, 0.3f);
        ((ModelRendererBends)model.bipedRightLeg).rotation.setSmoothX(-30.0f, 0.3f);
        ((ModelRendererBends)model.bipedLeftLeg).rotation.setSmoothX(-30.0f, 0.3f);
        ((ModelRendererBends)model.bipedLeftLeg).rotation.setSmoothY(-25.0f, 0.3f);
        ((ModelRendererBends)model.bipedRightLeg).rotation.setSmoothZ(10.0f);
        ((ModelRendererBends)model.bipedLeftLeg).rotation.setSmoothZ(-10.0f);
        ((ModelRendererBends)model.bipedRightForeLeg).rotation.setSmoothX(30.0f, 0.3f);
        ((ModelRendererBends)model.bipedLeftForeLeg).rotation.setSmoothX(30.0f, 0.3f);
        ((ModelRendererBends)model.bipedHead).rotation.setSmoothY(model.headRotationY - 20.0f, 0.3f);
        ((ModelRendererBends)model.bipedHead).rotation.setSmoothX(model.headRotationX - 10.0f, 0.3f);
    }
}

