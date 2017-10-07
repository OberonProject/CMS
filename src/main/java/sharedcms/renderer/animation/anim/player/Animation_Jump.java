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
import sharedcms.renderer.animation.util.SmoothVector3f;

import org.lwjgl.util.vector.Vector3f;

public class Animation_Jump extends Animation
{
	@Override
	public String getName()
	{
		return "jump";
	}

	@Override
	public void animate(EntityLivingBase argEntity, ModelBase argModel, EntityData argData)
	{
		ModelBendsPlayer model = (ModelBendsPlayer) argModel;
		Data_Player data = (Data_Player) argData;
		((ModelRendererBends) model.bipedBody).rotation.setSmoothX(0.0f, 0.3f);
		((ModelRendererBends) model.bipedBody).rotation.setSmoothY(0.0f, 0.1f);
		((ModelRendererBends) model.bipedBody).rotation.setSmoothZ(0.0f, 0.3f);
		((ModelRendererBends) model.bipedRightArm).rotation.setSmoothZ(45.0f, 0.05f);
		((ModelRendererBends) model.bipedLeftArm).rotation.setSmoothZ(-45.0f, 0.05f);
		((ModelRendererBends) model.bipedRightArm).rotation.setSmoothX(0.0f, 0.5f);
		((ModelRendererBends) model.bipedLeftArm).rotation.setSmoothX(0.0f, 0.5f);
		((ModelRendererBends) model.bipedRightLeg).rotation.setSmoothZ(10.0f, 0.1f);
		((ModelRendererBends) model.bipedLeftLeg).rotation.setSmoothZ(-10.0f, 0.1f);
		((ModelRendererBends) model.bipedRightLeg).rotation.setSmoothX(-20.0f, 0.1f);
		((ModelRendererBends) model.bipedLeftLeg).rotation.setSmoothX(-20.0f, 0.1f);
		((ModelRendererBends) model.bipedRightLeg).rotation.setSmoothX(-45.0f, 0.1f);
		((ModelRendererBends) model.bipedLeftLeg).rotation.setSmoothX(-45.0f, 0.1f);
		((ModelRendererBends) model.bipedRightForeLeg).rotation.setSmoothX(50.0f, 0.1f);
		((ModelRendererBends) model.bipedLeftForeLeg).rotation.setSmoothX(50.0f, 0.1f);
		((ModelRendererBends) model.bipedRightForeArm).rotation.setSmoothX(0.0f, 0.3f);
		((ModelRendererBends) model.bipedLeftForeArm).rotation.setSmoothX(0.0f, 0.3f);
		((ModelRendererBends) model.bipedHead).rotation.setSmoothY(model.headRotationY, 0.3f);
		((ModelRendererBends) model.bipedHead).rotation.setSmoothX(model.headRotationX - model.bipedBody.rotateAngleX, 0.3f);
		if(data.ticksAfterLiftoff < 2.0f)
		{
			((ModelRendererBends) model.bipedBody).rotation.setSmoothX(20.0f, 2.0f);
			((ModelRendererBends) model.bipedRightLeg).rotation.setSmoothX(0.0f, 2.0f);
			((ModelRendererBends) model.bipedLeftLeg).rotation.setSmoothX(0.0f, 2.0f);
			((ModelRendererBends) model.bipedRightForeLeg).rotation.setSmoothX(0.0f, 2.0f);
			((ModelRendererBends) model.bipedLeftForeLeg).rotation.setSmoothX(0.0f, 2.0f);
			((ModelRendererBends) model.bipedRightArm).rotation.setSmoothZ(2.0f, 2.0f);
			((ModelRendererBends) model.bipedLeftArm).rotation.setSmoothZ(-2.0f, 2.0f);
			((ModelRendererBends) model.bipedRightForeArm).rotation.setSmoothX(-20.0f, 2.0f);
			((ModelRendererBends) model.bipedLeftForeArm).rotation.setSmoothX(-20.0f, 2.0f);
		}

		if(argData.motion.x != 0.0f | argData.motion.z != 0.0f)
		{
			if(argEntity.isSprinting())
			{
				float bodyRot = 0.0f;
				float bodyLean = argData.motion.y;
				if(bodyLean < -0.2f)
				{
					bodyLean = -0.2f;
				}
				if(bodyLean > 0.2f)
				{
					bodyLean = 0.2f;
				}
				bodyLean = bodyLean * -100.0f + 20.0f;
				((ModelRendererBends) model.bipedBody).rotation.setSmoothX(bodyLean, 0.3f);
				((ModelRendererBends) model.bipedRightLeg).rotation.setSmoothZ(5.0f, 0.3f);
				((ModelRendererBends) model.bipedLeftLeg).rotation.setSmoothZ(-5.0f, 0.3f);
				((ModelRendererBends) model.bipedRightArm).rotation.setSmoothZ(10.0f, 0.3f);
				((ModelRendererBends) model.bipedLeftArm).rotation.setSmoothZ(-10.0f, 0.3f);
				if(data.sprintJumpLeg)
				{
					((ModelRendererBends) model.bipedRightLeg).rotation.setSmoothX(-45.0f, 0.4f);
					((ModelRendererBends) model.bipedLeftLeg).rotation.setSmoothX(45.0f, 0.4f);
					((ModelRendererBends) model.bipedRightArm).rotation.setSmoothX(50.0f, 0.3f);
					((ModelRendererBends) model.bipedLeftArm).rotation.setSmoothX(-50.0f, 0.3f);
					bodyRot = 20.0f;
				}
				else
				{
					((ModelRendererBends) model.bipedRightLeg).rotation.setSmoothX(45.0f, 0.4f);
					((ModelRendererBends) model.bipedLeftLeg).rotation.setSmoothX(-45.0f, 0.4f);
					((ModelRendererBends) model.bipedRightArm).rotation.setSmoothX(-50.0f, 0.3f);
					((ModelRendererBends) model.bipedLeftArm).rotation.setSmoothX(50.0f, 0.3f);
					bodyRot = -20.0f;
				}
				((ModelRendererBends) model.bipedBody).rotation.setSmoothY(bodyRot, 0.3f);
				((ModelRendererBends) model.bipedHead).rotation.setSmoothY(model.headRotationY - bodyRot, 0.3f);
				((ModelRendererBends) model.bipedHead).rotation.setSmoothX(model.headRotationX - 20.0f, 0.3f);
				float var = model.bipedRightLeg.rotateAngleX;
				((ModelRendererBends) model.bipedLeftForeLeg).rotation.setSmoothX(var < 0.0f ? 45 : 2, 0.3f);
				((ModelRendererBends) model.bipedRightForeLeg).rotation.setSmoothX(var < 0.0f ? 2 : 45, 0.3f);
			}
			else
			{
				((ModelRendererBends) model.bipedRightLeg).rotation.setSmoothX(-5.0f + 0.5f * (float) ((double) (MathHelper.cos((float) (model.armSwing * 0.6662f)) * 1.4f * model.armSwingAmount) / 3.141592653589793 * 180.0), 1.0f);
				((ModelRendererBends) model.bipedLeftLeg).rotation.setSmoothX(-5.0f + 0.5f * (float) ((double) (MathHelper.cos((float) (model.armSwing * 0.6662f + 3.1415927f)) * 1.4f * model.armSwingAmount) / 3.141592653589793 * 180.0), 1.0f);
				float var = (float) ((double) (model.armSwing * 0.6662f) / 3.141592653589793) % 2.0f;
				((ModelRendererBends) model.bipedLeftForeLeg).rotation.setSmoothX(var > 1.0f ? 45 : 0, 0.3f);
				((ModelRendererBends) model.bipedRightForeLeg).rotation.setSmoothX(var > 1.0f ? 0 : 45, 0.3f);
				((ModelRendererBends) model.bipedLeftForeArm).rotation.setSmoothX((float) (Math.cos((double) (model.armSwing * 0.6662f) + 1.5707963267948966) + 1.0) / 2.0f * -20.0f, 1.0f);
				((ModelRendererBends) model.bipedRightForeArm).rotation.setSmoothX((float) (Math.cos(model.armSwing * 0.6662f) + 1.0) / 2.0f * -20.0f, 0.3f);
			}
		}
	}
}
