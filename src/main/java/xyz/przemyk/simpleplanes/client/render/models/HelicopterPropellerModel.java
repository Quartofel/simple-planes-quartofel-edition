package xyz.przemyk.simpleplanes.client.render.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import xyz.przemyk.simpleplanes.entities.PlaneEntity;

import static xyz.przemyk.simpleplanes.client.render.PlaneRenderer.getPropellerRotation;

public class HelicopterPropellerModel extends EntityModel<PlaneEntity> {

    private final ModelPart IronPropeller;
    private final ModelPart bone_propeller;
    private final ModelPart bone_propeller2;

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition IronPropeller = partdefinition.addOrReplaceChild("IronPropeller", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone_propeller = IronPropeller.addOrReplaceChild("bone_propeller", CubeListBuilder.create().texOffs(17, 28).addBox(-1.0F, -22.3406F, -1.0003F, 2.0F, 29.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 24).addBox(-5.0F, -20.3406F, -1.0003F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 28).addBox(-2.0F, -20.8406F, -2.0003F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -30.6594F, 3.0003F, 0.0F, 0.0F, 0.2182F));

        PartDefinition cube_r1 = bone_propeller.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 20).addBox(-5.0F, -0.5F, -1.0F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -19.8406F, -0.0003F, 0.0F, -1.5708F, 0.0F));

        PartDefinition A_r1 = bone_propeller.addOrReplaceChild("A_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-25.0F, -0.5F, -1.5F, 50.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-30.0F, -19.8406F, 0.4997F, 0.0873F, 0.0F, 3.1416F));

        PartDefinition A_r2 = bone_propeller.addOrReplaceChild("A_r2", CubeListBuilder.create().texOffs(0, 0).addBox(5.0F, -0.5F, -1.0F, 50.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -19.8406F, -0.0003F, -3.0543F, 0.0F, 0.0F));

        PartDefinition bone_propeller2 = IronPropeller.addOrReplaceChild("bone_propeller2", CubeListBuilder.create().texOffs(17, 28).addBox(-1.0F, -22.5073F, -1.0003F, 2.0F, 29.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 24).addBox(-5.0F, -20.5073F, -1.0003F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 28).addBox(-2.0F, -21.0073F, -2.0003F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -30.4927F, 3.0003F, 0.0F, -0.6109F, -0.2182F));

        PartDefinition cube_r2 = bone_propeller2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 20).addBox(-5.0F, -0.5F, -1.0F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -20.0073F, -0.0003F, 0.0F, -1.5708F, 0.0F));

        PartDefinition A_r3 = bone_propeller2.addOrReplaceChild("A_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-25.0F, -0.5F, -1.5F, 50.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-30.0F, -20.0073F, -0.5003F, 3.0543F, 0.0F, 3.1416F));

        PartDefinition A_r4 = bone_propeller2.addOrReplaceChild("A_r4", CubeListBuilder.create().texOffs(0, 0).addBox(-25.0F, -0.5F, -1.5F, 50.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(30.0F, -20.0073F, 0.4997F, 3.0543F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    public HelicopterPropellerModel(ModelPart root) {
        this.IronPropeller = root.getChild("IronPropeller");
        this.bone_propeller = this.IronPropeller.getChild("bone_propeller");
        this.bone_propeller2 = this.IronPropeller.getChild("bone_propeller2");
    }

    @Override
    public void setupAnim(PlaneEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        bone_propeller.yRot =
            getPropellerRotation(entity, limbSwing);
        bone_propeller2.yRot =
            1-getPropellerRotation(entity, limbSwing);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        IronPropeller.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

}