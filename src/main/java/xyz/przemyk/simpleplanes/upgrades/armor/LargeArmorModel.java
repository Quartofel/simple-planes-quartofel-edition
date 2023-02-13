package xyz.przemyk.simpleplanes.upgrades.armor;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import xyz.przemyk.simpleplanes.entities.PlaneEntity;

public class LargeArmorModel extends EntityModel<PlaneEntity> {
	private final ModelPart Armor;

	public LargeArmorModel(ModelPart root) {
		this.Armor = root.getChild("Armor");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Armor = partdefinition.addOrReplaceChild("Armor", CubeListBuilder.create().texOffs(72, 6).addBox(-10.0F, -12.0F, 1.5F, 1.0F, 15.0F, 21.0F, new CubeDeformation(0.0F))
				.texOffs(72, 6).addBox(9.0F, -12.0F, 1.5F, 1.0F, 15.0F, 21.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.0F, -3.0F, 28.5F, 1.0F, 7.0F, 35.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-10.0F, -3.0F, 28.5F, 1.0F, 7.0F, 35.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -18.5F));

		PartDefinition cube_r1 = Armor.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(73, 0).addBox(1.5F, -13.0F, -4.0F, 1.0F, 18.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.5F, -1.0F, 76.5F, 0.0F, 0.4538F, 0.0F));

		PartDefinition cube_r2 = Armor.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -8.5F, -12.25F, 1.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.8431F, -4.5F, -5.3431F, 0.0F, -2.3562F, 0.0F));

		PartDefinition cube_r3 = Armor.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(7.5F, -8.5F, -5.25F, 1.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.5F, -4.5F, -11.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r4 = Armor.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(73, 0).addBox(-2.5F, -13.0F, -4.0F, 1.0F, 18.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.5F, -1.0F, 76.5F, 0.0F, -0.4538F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(PlaneEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Armor.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}