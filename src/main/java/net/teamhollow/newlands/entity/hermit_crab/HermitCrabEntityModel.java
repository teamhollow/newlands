package net.teamhollow.newlands.entity.hermit_crab;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.util.math.MathHelper;

public class HermitCrabEntityModel extends CompositeEntityModel<HermitCrabEntity> {
    private final ModelPart body;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart eyes;

    public HermitCrabEntityModel() {
        textureWidth = 16;
        textureHeight = 16;

        body = new ModelPart(this);
        body.setPivot(0.0F, 24.0F, 0.0F);
        body.setTextureOffset(0, 0).addCuboid(-2.0F, -3.5F, -1.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);

        rightLeg = new ModelPart(this);
        rightLeg.setPivot(2.0F, -1.0F, 1.0F);
        body.addChild(rightLeg);
        setRotationAngle(rightLeg, 0.0F, 0.0F, 0.8727F);
        rightLeg.setTextureOffset(7, 12).addCuboid(-1.2F, -0.4096F, -2.0F, 2.0F, 0.0F, 4.0F, 0.0F, true);

        leftLeg = new ModelPart(this);
        leftLeg.setPivot(-2.0F, -1.0F, 1.0F);
        body.addChild(leftLeg);
        setRotationAngle(leftLeg, 0.0F, 0.0F, -0.8727F);
        leftLeg.setTextureOffset(7, 12).addCuboid(-1.5F, -0.4096F, -2.0F, 2.0F, 0.0F, 4.0F, 0.0F, false);

        rightArm = new ModelPart(this);
        rightArm.setPivot(-0.7F, -2.25F, -0.991F);
        body.addChild(rightArm);
        setRotationAngle(rightArm, 1.2236F, 0.0F, -1.5708F);
        rightArm.setTextureOffset(0, 11).addCuboid(-1.2F, -1.75F, -2.799F, 1.0F, 2.0F, 3.0F, 0.0F, false);

        leftArm = new ModelPart(this);
        leftArm.setPivot(2.0825F, -2.25F, -0.991F);
        body.addChild(leftArm);
        setRotationAngle(leftArm, 1.2236F, 0.0F, 1.5708F);
        leftArm.setTextureOffset(0, 11).addCuboid(-1.2F, -1.75F, -2.799F, 1.0F, 2.0F, 3.0F, 0.0F, true);

        eyes = new ModelPart(this);
        eyes.setPivot(0.0F, -1.0F, -1.0F);
        body.addChild(eyes);
        setRotationAngle(eyes, -0.6981F, 0.0F, 0.0F);
        eyes.setTextureOffset(2, 6).addCuboid(-1.0F, -2.0F, -3.5F, 2.0F, 1.0F, 3.0F, 0.0F, false);
    }

    @Override
    public void setAngles(HermitCrabEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.rightLeg.pitch = MathHelper.cos(limbSwing * 0.6662F * 0.6F) * 1.05F * limbSwingAmount;
        this.leftLeg.pitch = MathHelper.cos(limbSwing * 0.6662F * 0.6F + 3.1415927F) * 1.05F * limbSwingAmount;
        this.rightArm.roll = MathHelper.cos(limbSwing * 0.6662F * 0.6F + 3.1415927F) * 1.05F * limbSwingAmount;
        this.leftArm.roll = MathHelper.cos(limbSwing * 0.6662F * 0.6F) * 1.05F * limbSwingAmount;
        this.rightArm.yaw = 0.0F;
        this.leftArm.yaw = 0.0F;
        this.rightLeg.yaw = 0.0F;
        this.leftLeg.yaw = 0.0F;
    }

    public void setRotationAngle(ModelPart bone, float x, float y, float z) {
        bone.pitch = x;
        bone.yaw = y;
        bone.roll = z;
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.body);
    }
}
