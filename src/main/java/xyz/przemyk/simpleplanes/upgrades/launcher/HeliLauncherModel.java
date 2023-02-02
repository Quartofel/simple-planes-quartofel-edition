package xyz.przemyk.simpleplanes.upgrades.launcher;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import xyz.przemyk.simpleplanes.entities.PlaneEntity;

public class HeliLauncherModel extends EntityModel<PlaneEntity> {

    private final ModelPart Launcher;

    public HeliLauncherModel(ModelPart root) {
        this.Launcher = root.getChild("Launcher");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Launcher = partdefinition.addOrReplaceChild("Launcher", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 6.0F));

        PartDefinition cube_r1 = Launcher.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(28, 0).addBox(-8.8902F, -0.5F, -5.2477F, 18.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.3902F, -23.4F, -1.1523F, 0.0F, 0.0F, 0.0F));

        PartDefinition cube_r2 = Launcher.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 12.0F, new CubeDeformation(2.0F)), PartPose.offsetAndRotation(-20.5F, -16.9F, -2.9F, 0.0F, 3.1416F, 0.0F));

        PartDefinition cube_r3 = Launcher.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 12.0F, new CubeDeformation(2.0F)), PartPose.offsetAndRotation(20.5F, -16.9F, -2.9F, 0.0F, 3.1416F, 0.0F));

        PartDefinition cube_r4 = Launcher.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(28, 0).addBox(-9.0F, -1.0F, -3.5F, 18.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.5F, -22.9F, -2.9F, 0.0F, 0.0F, -3.1416F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(PlaneEntity p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {}

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Launcher.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}