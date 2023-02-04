package xyz.przemyk.simpleplanes.client.render.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import xyz.przemyk.simpleplanes.entities.LargePlaneEntity;

public class LargePlaneModel extends EntityModel<LargePlaneEntity> {

    private final ModelPart Plane;

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Plane = partdefinition.addOrReplaceChild("Plane", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = Plane.addOrReplaceChild("body", CubeListBuilder.create().texOffs(82, 152).addBox(8.0F, -12.0F, -1.0F, 1.0F, 16.0F, 66.0F, new CubeDeformation(0.0F))
                .texOffs(45, 168).addBox(-9.0F, -12.0F, -8.25F, 18.0F, 17.0F, 8.0F, new CubeDeformation(-0.001F))
                .texOffs(27, 25).addBox(-5.0F, -13.0F, 60.8F, 10.0F, 18.0F, 16.0F, new CubeDeformation(0.01F))
                .texOffs(0, 26).addBox(-9.0F, 4.0F, -1.0F, 18.0F, 1.0F, 66.0F, new CubeDeformation(0.0F))
                .texOffs(156, 148).addBox(-9.0F, -12.0F, -1.0F, 1.0F, 16.0F, 66.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, -11.0F));

        PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(121, 156).addBox(0.0F, -9.0F, 0.0F, 9.0F, 18.0F, 13.0F, new CubeDeformation(-0.02F)), PartPose.offsetAndRotation(-9.0F, -4.0F, 65.0F, 0.0F, 0.4538F, 0.0F));

        PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 172).addBox(-9.0F, -9.0F, 0.0F, 9.0F, 18.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, -4.0F, 65.0F, 0.0F, -0.4538F, 0.0F));

        PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(45, 168).addBox(-3.0F, -8.75F, -9.0F, 12.0F, 17.0F, 12.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, -3.5F, -4.0F, 0.0F, 0.7854F, 0.0F));

        return LayerDefinition.create(meshdefinition, 16, 16);
    }

    public LargePlaneModel(ModelPart root) {
        this.Plane = root.getChild("Plane");
    }

    @Override
    public void setupAnim(LargePlaneEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Plane.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
