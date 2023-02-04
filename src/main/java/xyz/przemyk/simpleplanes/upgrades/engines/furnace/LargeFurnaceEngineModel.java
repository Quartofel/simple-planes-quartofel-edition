package xyz.przemyk.simpleplanes.upgrades.engines.furnace;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import xyz.przemyk.simpleplanes.entities.PlaneEntity;

public class LargeFurnaceEngineModel extends EntityModel<PlaneEntity> {
	private final ModelPart FurnaceEngine;

	public LargeFurnaceEngineModel(ModelPart root) {
		this.FurnaceEngine = root.getChild("FurnaceEngine");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition FurnaceEngine = partdefinition.addOrReplaceChild("FurnaceEngine", CubeListBuilder.create().texOffs(0, 0).addBox(-57.0F, 20.9853F, 99.0F, 8.0F, 9.0F, 23.0F, new CubeDeformation(0.001F))
				.texOffs(0, 0).mirror().addBox(-3.0F, 20.9853F, 99.0F, 8.0F, 9.0F, 23.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offset(26.0F, -28.0F, -73.5F));

		PartDefinition octagon_r1 = FurnaceEngine.addOrReplaceChild("octagon_r1", CubeListBuilder.create().texOffs(0, 64).mirror().addBox(-0.01F, -0.86F, -11.5F, 4.0F, 2.0F, 23.0F, new CubeDeformation(0.0002F)).mirror(false), PartPose.offsetAndRotation(-2.73F, 21.8053F, 110.5F, 0.0F, 0.0F, -0.2967F));

		PartDefinition octagon_r2 = FurnaceEngine.addOrReplaceChild("octagon_r2", CubeListBuilder.create().texOffs(31, 66).mirror().addBox(-3.99F, -0.86F, -11.5F, 4.0F, 2.0F, 23.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.73F, 21.8053F, 110.5F, 0.0F, 0.0F, 0.2967F));

		PartDefinition octagon_r3 = FurnaceEngine.addOrReplaceChild("octagon_r3", CubeListBuilder.create().texOffs(44, 14).addBox(-65.25F, 1.5F, -7.0F, 14.0F, 3.0F, 18.0F, new CubeDeformation(0.001F))
				.texOffs(44, 14).addBox(-11.25F, 1.5F, -7.0F, 14.0F, 3.0F, 18.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(5.1912F, 30.4979F, 114.0F, -3.1416F, 0.0F, 0.0F));

		PartDefinition octagon_r4 = FurnaceEngine.addOrReplaceChild("octagon_r4", CubeListBuilder.create().texOffs(0, 64).addBox(-3.99F, -0.86F, -11.5F, 4.0F, 2.0F, 23.0F, new CubeDeformation(0.0002F)), PartPose.offsetAndRotation(-49.27F, 21.8053F, 110.5F, 0.0F, 0.0F, 0.2967F));

		PartDefinition octagon_r5 = FurnaceEngine.addOrReplaceChild("octagon_r5", CubeListBuilder.create().texOffs(31, 66).addBox(-0.01F, -0.86F, -11.5F, 4.0F, 2.0F, 23.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-56.73F, 21.8053F, 110.5F, 0.0F, 0.0F, -0.2967F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		FurnaceEngine.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(PlaneEntity p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {}
}