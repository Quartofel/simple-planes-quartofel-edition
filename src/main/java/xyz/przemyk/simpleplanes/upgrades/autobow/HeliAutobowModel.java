package xyz.przemyk.simpleplanes.upgrades.autobow;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import xyz.przemyk.simpleplanes.entities.PlaneEntity;

public class HeliAutobowModel extends EntityModel<PlaneEntity> {

	private final ModelPart Autobow;

	public HeliAutobowModel(ModelPart root) {
		this.Autobow = root.getChild("autobow");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition autobow = partdefinition.addOrReplaceChild("autobow", CubeListBuilder.create().texOffs(15, 10).addBox(-21.25F, -21.75F, -9.25F, 3.0F, 3.0F, 13.0F, new CubeDeformation(0.0F))
				.texOffs(40, 0).addBox(-25.75F, -20.25F, -8.25F, 12.0F, 0.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(0, 8).addBox(17.25F, -22.75F, -7.25F, 5.0F, 5.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(15, 10).addBox(18.25F, -21.75F, -9.25F, 3.0F, 3.0F, 13.0F, new CubeDeformation(0.0F))
				.texOffs(40, 0).addBox(13.75F, -20.25F, -8.25F, 12.0F, 0.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(0, 8).addBox(-22.25F, -22.75F, -7.25F, 5.0F, 5.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.5F, -28.75F, -42.75F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 6.0F));

		PartDefinition cube_r1 = autobow.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-8.8902F, -0.5F, -5.2477F, 18.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.3902F, -22.65F, -1.1523F, 0.0F, 0.0F, 0.0F));

		PartDefinition cube_r2 = autobow.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(34, 13).addBox(-9.0F, -9.0F, 2.25F, 2.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.75F, -17.75F, 3.0F, -3.1416F, 0.0F, 3.1416F));

		PartDefinition cube_r3 = autobow.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(34, 13).addBox(-9.0F, -9.0F, 2.25F, 2.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-27.75F, -17.75F, 3.0F, -3.1416F, 0.0F, 3.1416F));

		PartDefinition cube_r4 = autobow.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(19, 8).addBox(-2.25F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-23.75F, -20.25F, -8.25F, 0.0F, 0.1745F, 0.0F));

		PartDefinition cube_r5 = autobow.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(19, 8).addBox(-2.75F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(23.75F, -20.25F, -8.25F, 0.0F, -0.1745F, 0.0F));

		PartDefinition cube_r6 = autobow.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(19, 8).addBox(-2.25F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.75F, -20.25F, -8.25F, 0.0F, 0.1745F, 0.0F));

		PartDefinition cube_r7 = autobow.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(19, 8).addBox(-2.75F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.75F, -20.25F, -8.25F, 0.0F, -0.1745F, 0.0F));

		PartDefinition cube_r8 = autobow.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, 0.0F, -3.5F, 18.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.5F, -22.15F, -2.9F, 0.0F, 0.0F, -3.1416F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(PlaneEntity p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Autobow.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}