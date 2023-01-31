package xyz.przemyk.simpleplanes.upgrades.shooter;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import xyz.przemyk.simpleplanes.entities.PlaneEntity;


public class HeliFireworkModel extends EntityModel<PlaneEntity> {
	private final ModelPart Firework_Shooter;

	public HeliFireworkModel(ModelPart root) {
		this.Firework_Shooter = root.getChild("Shooter");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Shooter = partdefinition.addOrReplaceChild("Shooter", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 6.0F));

		PartDefinition cube_r1 = Shooter.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(43, 10).addBox(-8.8902F, -0.5F, -5.2477F, 18.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.3902F, -23.4F, -1.1523F, 0.0F, 0.0F, 0.0F));

		PartDefinition cube_r2 = Shooter.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 22).addBox(-0.8902F, 2.5F, -7.2477F, 4.0F, 4.0F, 12.0F, new CubeDeformation(2.0F)), PartPose.offsetAndRotation(20.3902F, -23.4F, -1.6523F, 0.0F, 0.0F, 0.0F));

		PartDefinition cube_r3 = Shooter.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 22).addBox(-3.1098F, 2.5F, -7.2477F, 4.0F, 4.0F, 12.0F, new CubeDeformation(2.0F)), PartPose.offsetAndRotation(-20.3902F, -23.4F, -1.6523F, 0.0F, 0.0F, 0.0F));

		PartDefinition cube_r4 = Shooter.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(43, 10).addBox(-9.0F, -1.0F, -3.5F, 18.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.5F, -22.9F, -2.9F, 0.0F, 0.0F, -3.1416F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Firework_Shooter.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(PlaneEntity p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {}

}