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
import sharedcms.renderer.animation.data.EntityData;
import sharedcms.renderer.animation.util.GUtil;
import sharedcms.renderer.animation.util.SmoothVector3f;

public class Animation_Sprint extends Animation
{
	@Override
	public String getName()
	{
		return "sprint";
	}

	@Override
	public void animate(EntityLivingBase argEntity, ModelBase argModel, EntityData argData)
	{
		ModelBendsPlayer model = (ModelBendsPlayer) argModel;
		((ModelRendererBends) model.bipedRightArm).rotation.setSmoothX(1.1f * (float) ((double) (MathHelper.cos((float) (model.armSwing * 0.6662f + 3.1415927f)) * 2.0f * model.armSwingAmount * 0.5f) / 3.141592653589793 * 180.0));
		((ModelRendererBends) model.bipedLeftArm).rotation.setSmoothX(1.1f * (float) ((double) (MathHelper.cos((float) (model.armSwing * 0.6662f)) * 2.0f * model.armSwingAmount * 0.5f) / 3.141592653589793 * 180.0));
		((ModelRendererBends) model.bipedRightArm).rotation.setSmoothZ(5.0f, 0.3f);
		((ModelRendererBends) model.bipedLeftArm).rotation.setSmoothZ(-5.0f, 0.3f);
		((ModelRendererBends) model.bipedRightLeg).rotation.setSmoothX(-5.0f + 0.9f * (float) ((double) (MathHelper.cos((float) (model.armSwing * 0.6662f)) * 1.4f * model.armSwingAmount) / 3.141592653589793 * 180.0), 1.0f);
		((ModelRendererBends) model.bipedLeftLeg).rotation.setSmoothX(-5.0f + 0.9f * (float) ((double) (MathHelper.cos((float) (model.armSwing * 0.6662f + 3.1415927f)) * 1.4f * model.armSwingAmount) / 3.141592653589793 * 180.0), 1.0f);
		((ModelRendererBends) model.bipedRightLeg).rotation.setSmoothY(0.0f);
		((ModelRendererBends) model.bipedLeftLeg).rotation.setSmoothY(0.0f);
		((ModelRendererBends) model.bipedRightLeg).rotation.setSmoothZ(2.0f, 0.2f);
		((ModelRendererBends) model.bipedLeftLeg).rotation.setSmoothZ(-2.0f, 0.2f);
		float var = (float) ((double) (model.armSwing * 0.6662f) / 3.141592653589793) % 2.0f;
		((ModelRendererBends) model.bipedLeftForeLeg).rotation.setSmoothX(var > 1.0f ? 45 : 0, 0.3f);
		((ModelRendererBends) model.bipedRightForeLeg).rotation.setSmoothX(var > 1.0f ? 0 : 45, 0.3f);
		((ModelRendererBends) model.bipedLeftForeArm).rotation.setSmoothX(var > 1.0f ? -10 : -45, 0.3f);
		((ModelRendererBends) model.bipedRightForeArm).rotation.setSmoothX(var > 1.0f ? -45 : -10, 0.3f);
		float var2 = (float) Math.cos(model.armSwing * 0.6662f) * -40.0f;
		float var3 = (float) (Math.cos(model.armSwing * 0.6662f * 2.0f) * 0.5 + 0.5) * 20.0f;
		((ModelRendererBends) model.bipedBody).rotation.setSmoothY(var2, 0.5f);
		((ModelRendererBends) model.bipedBody).rotation.setSmoothX(var3);
		((ModelRendererBends) model.bipedHead).rotation.setSmoothY(model.headRotationY - var2, 0.5f);
		((ModelRendererBends) model.bipedHead).rotation.setSmoothX(model.headRotationX - var3);
		float var10 = model.headRotationY * 0.3f;
		var10 = GUtil.max(var10, 20.0f);
		var10 = GUtil.min(var10, -20.0f);
		((ModelRendererBends) model.bipedBody).rotation.setSmoothZ(-var10, 0.3f);
		model.renderOffset.setSmoothY(var3 * 0.15f, 0.9f);
	}
}
