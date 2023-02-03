package xyz.przemyk.simpleplanes.upgrades.shooter;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import xyz.przemyk.simpleplanes.entities.PlaneEntity;

public class HeliShooterModel extends EntityModel<PlaneEntity> {

	private final ModelPart Shooter;

	public HeliShooterModel(ModelPart root) {
		this.Shooter = root.getChild("Shooter");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Shooter = partdefinition.addOrReplaceChild("Shooter", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 6.0F));

		PartDefinition cube_r1 = Shooter.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(28, 0).addBox(-8.8902F, -0.5F, -5.2477F, 18.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.3902F, -23.4F, -1.1523F, 0.0F, 0.0F, 0.0F));

		PartDefinition cube_r2 = Shooter.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(28, 0).addBox(-9.0F, -1.0F, -3.5F, 18.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.5F, -22.9F, -2.9F, 0.0F, 0.0F, -3.1416F));

		PartDefinition weapon = Shooter.addOrReplaceChild("weapon", CubeListBuilder.create().texOffs(44, 12).addBox(34.0F, -0.75F, 5.25F, 4.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(116, 17).addBox(31.0F, -5.0F, -16.75F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(122, 17).addBox(10.5F, -9.0F, -31.75F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(78, 0).addBox(31.5F, -4.5F, -15.75F, 1.0F, 2.0F, 18.0F, new CubeDeformation(0.0F))
				.texOffs(98, 0).addBox(31.0F, -4.75F, -8.75F, 2.0F, 3.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(57, 11).addBox(-10.5F, -4.5F, 4.25F, 5.0F, 5.0F, 11.0F, new CubeDeformation(0.0F))
				.texOffs(57, 11).mirror().addBox(29.5F, -4.5F, 4.25F, 5.0F, 5.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(116, 17).addBox(-9.0F, -5.0F, -16.75F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(78, 0).addBox(-8.5F, -4.5F, -15.75F, 1.0F, 2.0F, 18.0F, new CubeDeformation(0.0F))
				.texOffs(98, 0).addBox(-9.0F, -4.75F, -8.75F, 2.0F, 3.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(-12.0F, -19.0F, -11.5F));

		PartDefinition cube_r3 = weapon.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(44, 12).addBox(-6.0F, -1.0F, 4.25F, 4.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.0F, 0.25F, 14.5F, -3.1416F, 0.0F, 3.1416F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(PlaneEntity p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Shooter.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}