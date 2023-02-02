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

        PartDefinition bone_propeller = IronPropeller.addOrReplaceChild("bone_propeller", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, -22.3406F, -1.0003F, 2.0F, 29.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(-10, 0).mirror().addBox(-50.0F, -20.3406F, -5.0003F, 100.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 10).addBox(-2.0F, -20.8406F, -2.0003F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -30.6594F, 3.0003F, 0.0F, 0.0F, 0.2182F));

        PartDefinition bone_propeller2 = IronPropeller.addOrReplaceChild("bone_propeller2", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, -22.5073F, -1.0003F, 2.0F, 29.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(-10, 0).addBox(-50.0F, -20.5073F, -5.0003F, 100.0F, 0.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 10).addBox(-2.0F, -21.0073F, -2.0003F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -30.4927F, 3.0003F, 0.0F, -0.6109F, -0.2182F));

        return LayerDefinition.create(meshdefinition, 256, 256);
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