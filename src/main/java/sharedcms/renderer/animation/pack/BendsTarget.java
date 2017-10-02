/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.lwjgl.util.vector.Vector3f
 */
package sharedcms.renderer.animation.pack;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import sharedcms.renderer.animation.client.model.ModelRendererBends;
import sharedcms.renderer.animation.pack.BendsAction;
import sharedcms.renderer.animation.util.EnumAxis;
import sharedcms.renderer.animation.util.SmoothVector3f;

public class BendsTarget {
    public String mob;
    public List<BendsAction> actions = new ArrayList<BendsAction>();
    public float visual_DeletePopUp;

    public BendsTarget(String argMob) {
        this.mob = argMob;
        this.visual_DeletePopUp = 0.0f;
    }

    public void applyToModel(ModelRendererBends box, String anim, String model) {
        for (int i = 0; i < this.actions.size(); ++i) {
            if (!((this.actions.get((int)i).anim.equalsIgnoreCase(anim) | this.actions.get((int)i).anim.equalsIgnoreCase("all")) & this.actions.get((int)i).model.equalsIgnoreCase(model))) continue;
            if (this.actions.get((int)i).prop == BendsAction.EnumBoxProperty.ROT) {
                box.rotation.setSmooth(this.actions.get((int)i).axis, this.actions.get(i).getNumber(this.actions.get((int)i).axis == EnumAxis.X ? box.rotation.vFinal.x : (this.actions.get((int)i).axis == EnumAxis.Y ? box.rotation.vFinal.y : box.rotation.vFinal.z)), this.actions.get((int)i).smooth);
                continue;
            }
            if (this.actions.get((int)i).prop != BendsAction.EnumBoxProperty.SCALE) continue;
            if (this.actions.get((int)i).axis == null | this.actions.get((int)i).axis == EnumAxis.X) {
                box.scaleX = this.actions.get(i).getNumber(box.scaleX);
            }
            if (this.actions.get((int)i).axis == null | this.actions.get((int)i).axis == EnumAxis.Y) {
                box.scaleY = this.actions.get(i).getNumber(box.scaleY);
            }
            if (!(this.actions.get((int)i).axis == null | this.actions.get((int)i).axis == EnumAxis.Z)) continue;
            box.scaleZ = this.actions.get(i).getNumber(box.scaleZ);
        }
    }
}

