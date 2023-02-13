package xyz.przemyk.simpleplanes.upgrades.armor;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import xyz.przemyk.simpleplanes.entities.PlaneEntity;

public class HeliArmorModel extends EntityModel<PlaneEntity> {
	private final ModelPart Armor;

	public HeliArmorModel(ModelPart root) {
		this.Armor = root.getChild("Armor");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Armor = partdefinition.addOrReplaceChild("Armor", CubeListBuilder.create(), PartPose.offset(0.0F, 15.0F, -18.5F));

		PartDefinition cube_r1 = Armor.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(66, 49).addBox(-10.0F, -20.35F, -7.25F, 2.0F, 20.0F, 3.0F, new CubeDeformation(0.006F))
				.texOffs(66, 49).addBox(8.002F, -20.35F, -7.25F, 2.0F, 20.0F, 3.0F, new CubeDeformation(0.006F)), PartPose.offsetAndRotation(-0.001F, -5.8913F, -18.7697F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r2 = Armor.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(66, 49).addBox(-10.0F, -36.25F, -18.9F, 2.0F, 27.0F, 3.0F, new CubeDeformation(0.002F))
				.texOffs(66, 49).addBox(7.998F, -36.25F, -18.9F, 2.0F, 27.0F, 3.0F, new CubeDeformation(0.002F)), PartPose.offsetAndRotation(0.001F, -6.599F, -18.801F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r3 = Armor.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 27).addBox(-10.0F, -10.2F, -14.5F, 1.0F, 36.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(0, 27).addBox(9.012F, -10.2F, -14.5F, 1.0F, 36.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.006F, -0.994F, 7.256F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r4 = Armor.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(34, 45).addBox(-8.0F, -6.45F, -6.25F, 16.0F, 6.0F, 1.0F, new CubeDeformation(-0.003F)), PartPose.offsetAndRotation(0.0F, -6.55F, -19.55F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r5 = Armor.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(34, 52).addBox(-8.0F, -37.0F, -18.9F, 16.0F, 27.0F, 0.0F, new CubeDeformation(0.006F)), PartPose.offsetAndRotation(-0.001F, -6.599F, -19.501F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r6 = Armor.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(6, 0).addBox(-8.0F, -36.25F, -20.0F, 16.0F, 0.0F, 26.0F, new CubeDeformation(0.006F)), PartPose.offsetAndRotation(0.0F, -6.5F, -18.8F, -1.5708F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(PlaneEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Armor.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}