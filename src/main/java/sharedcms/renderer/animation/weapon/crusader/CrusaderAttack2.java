package sharedcms.renderer.animation.weapon.crusader;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import sharedcms.renderer.animation.client.model.ModelRendererBends;
import sharedcms.renderer.animation.client.model.entity.ModelBendsPlayer;
import sharedcms.renderer.animation.client.renderer.SwordTrail;
import sharedcms.renderer.animation.data.Data_Player;
import sharedcms.renderer.animation.util.GUtil;
import sharedcms.renderer.animation.util.SmoothVector3f;

import org.lwjgl.util.vector.Vector3f;

public class CrusaderAttack2
{
	public static void animate(EntityPlayer player, ModelBendsPlayer model, Data_Player data)
	{
		if(data.ticksAfterPunch < 0.5f)
		{
			model.swordTrail.reset();
		}
		if(player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemSword)
		{
			model.swordTrail.add(model);
		}
		float attackState = data.ticksAfterPunch / 10.0f;
		float armSwing = attackState * 3.0f;
		armSwing = GUtil.max(armSwing, 1.0f);
		if(!player.isRiding())
		{
			model.renderRotation.setSmoothY(30.0f, 0.7f);
		}
		Vector3f bodyRot = new Vector3f(0.0f, 0.0f, 0.0f);
		bodyRot.x = 20.0f - attackState * 20.0f;
		bodyRot.y = -40.0f * attackState + 50.0f * attackState;
		((ModelRendererBends) model.bipedBody).rotation.setSmooth(bodyRot, 0.9f);
		((ModelRendererBends) model.bipedHead).rotation.setSmoothY(model.headRotationY - 30.0f);
		((ModelRendererBends) model.bipedHead).rotation.setSmoothX(model.headRotationX);
		((ModelRendererBends) model.bipedHead).pre_rotation.setSmoothX(-bodyRot.x, 0.9f);
		((ModelRendererBends) model.bipedHead).pre_rotation.setSmoothY(-bodyRot.y, 0.9f);
		((ModelRendererBends) model.bipedRightArm).pre_rotation.setSmoothZ(60.0f, 0.3f);
		((ModelRendererBends) model.bipedRightArm).rotation.setSmoothX(-20.0f + armSwing * 100.0f, 3.0f);
		((ModelRendererBends) model.bipedRightArm).rotation.setSmoothY(0.0f, 0.3f);
		((ModelRendererBends) model.bipedRightArm).rotation.setSmoothZ(0.0f, 0.9f);
		((ModelRendererBends) model.bipedLeftArm).rotation.setSmoothZ(20.0f, 0.3f);
		((ModelRendererBends) model.bipedLeftArm).pre_rotation.setSmoothZ(-80.0f, 0.3f);
		((ModelRendererBends) model.bipedLeftArm).rotation.setSmoothY(0.0f, 0.3f);
		((ModelRendererBends) model.bipedRightForeArm).rotation.setSmoothX(-20.0f, 0.3f);
		((ModelRendererBends) model.bipedLeftForeArm).rotation.setSmoothX(-60.0f, 0.3f);
		if(data.motion.x == 0.0f & data.motion.z == 0.0f)
		{
			((ModelRendererBends) model.bipedRightLeg).rotation.setSmoothX(-30.0f, 0.3f);
			((ModelRendererBends) model.bipedLeftLeg).rotation.setSmoothX(-30.0f, 0.3f);
			((ModelRendererBends) model.bipedLeftLeg).rotation.setSmoothY(-25.0f, 0.3f);
			((ModelRendererBends) model.bipedRightLeg).rotation.setSmoothZ(10.0f);
			((ModelRendererBends) model.bipedLeftLeg).rotation.setSmoothZ(-10.0f);
			((ModelRendererBends) model.bipedRightForeLeg).rotation.setSmoothX(30.0f, 0.3f);
			((ModelRendererBends) model.bipedLeftForeLeg).rotation.setSmoothX(30.0f, 0.3f);
			if(!player.isRiding())
			{
				model.renderOffset.setSmoothY(-2.0f);
			}
		}
		model.renderItemRotation.setSmoothX(90.0f, 0.9f);
	}
}