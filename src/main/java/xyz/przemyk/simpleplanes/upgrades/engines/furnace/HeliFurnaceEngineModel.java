package xyz.przemyk.simpleplanes.upgrades.engines.furnace;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import xyz.przemyk.simpleplanes.entities.PlaneEntity;

public class HeliFurnaceEngineModel extends EntityModel<PlaneEntity> {
	private final ModelPart FurnaceEngine;

	public HeliFurnaceEngineModel(ModelPart root) {
		this.FurnaceEngine = root.getChild("FurnaceEngine");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition FurnaceEngine = partdefinition.addOrReplaceChild("FurnaceEngine", CubeListBuilder.create(), PartPose.offset(0.0F, -17.0F, -47.5F));

		PartDefinition cube_r1 = FurnaceEngine.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(33, 0).addBox(-3.6841F, -3.5F, -19.5143F, 7.0F, 7.0F, 25.0F, new CubeDeformation(-0.18F)), PartPose.offsetAndRotation(1.8841F, 21.0F, 78.6143F, 0.0F, 0.0F, 0.0F));

		PartDefinition cube_r2 = FurnaceEngine.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(12, 0).addBox(-3.4341F, -4.0F, -13.5143F, 7.0F, 8.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1159F, 21.0F, 78.6143F, 0.0F, 0.0F, 0.0F));

		PartDefinition cube_r3 = FurnaceEngine.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -3.5F, -3.5F, 7.0F, 7.0F, 7.0F, new CubeDeformation(-0.179F)), PartPose.offsetAndRotation(3.0683F, 21.0F, 84.1285F, 2.3126F, 0.0F, 1.5708F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		FurnaceEngine.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(PlaneEntity p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {}
}