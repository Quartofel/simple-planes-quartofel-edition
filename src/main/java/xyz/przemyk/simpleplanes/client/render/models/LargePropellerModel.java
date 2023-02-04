package xyz.przemyk.simpleplanes.client.render.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import xyz.przemyk.simpleplanes.entities.PlaneEntity;

import static xyz.przemyk.simpleplanes.client.render.PlaneRenderer.getPropellerRotation;

public class LargePropellerModel extends EntityModel<PlaneEntity> {
	private final ModelPart IronPropeller;
	private final ModelPart bone;

	public LargePropellerModel(ModelPart root) {
		this.IronPropeller = root.getChild("IronPropeller");
		this.bone = root.getChild("bone");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition IronPropeller = partdefinition.addOrReplaceChild("IronPropeller", CubeListBuilder.create().texOffs(50, 0).addBox(-0.4759F, -0.8454F, 7.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.001F))
				.texOffs(0, 0).addBox(-12.4759F, -12.8454F, 11.5F, 25.0F, 25.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offset(27.0F, -5.0F, 42.0F));

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(50, 0).addBox(-0.5241F, -0.8454F, -2.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.001F))
				.texOffs(0, 0).mirror().addBox(-12.5241F, -12.8454F, 1.5F, 25.0F, 25.0F, 0.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offset(-27.0F, -5.0F, 52.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		IronPropeller.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(PlaneEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		IronPropeller.zRot = getPropellerRotation(entity, limbSwing);
		bone.zRot = -IronPropeller.zRot;
	}
}