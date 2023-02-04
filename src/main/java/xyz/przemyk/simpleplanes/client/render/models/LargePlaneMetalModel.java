package xyz.przemyk.simpleplanes.client.render.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import xyz.przemyk.simpleplanes.entities.PlaneEntity;

public class LargePlaneMetalModel extends EntityModel<PlaneEntity> {
	private final ModelPart Parts;

	public LargePlaneMetalModel(ModelPart root) {
		this.Parts = root.getChild("Parts");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Parts = partdefinition.addOrReplaceChild("Parts", CubeListBuilder.create().texOffs(227, 35).addBox(25.0F, -31.0147F, 26.4F, 4.0F, 4.0F, 24.0F, new CubeDeformation(0.0F))
				.texOffs(227, 35).addBox(-29.0F, -31.0147F, 26.4F, 4.0F, 4.0F, 24.0F, new CubeDeformation(0.0F))
				.texOffs(106, 26).addBox(-6.0F, -30.4F, -5.0F, 12.0F, 0.0F, 27.0F, new CubeDeformation(0.005F))
				.texOffs(185, 37).addBox(-1.0F, -2.25F, -0.5F, 2.0F, 7.0F, 7.0F, new CubeDeformation(0.05F))
				.texOffs(260, 7).addBox(8.5F, -34.5999F, 29.0264F, 0.0F, 14.0F, 19.0F, new CubeDeformation(0.0F))
				.texOffs(260, 7).addBox(-8.5F, -35.5999F, 27.0264F, 0.0F, 14.0F, 19.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-59.0F, -37.0F, 23.0F, 118.0F, 2.0F, 24.0F, new CubeDeformation(0.0F))
				.texOffs(3, 26).addBox(-53.0F, -15.0F, 30.0F, 44.0F, 2.0F, 21.0F, new CubeDeformation(0.0F))
				.texOffs(3, 26).addBox(9.0F, -15.0F, 30.0F, 44.0F, 2.0F, 21.0F, new CubeDeformation(0.0F))
				.texOffs(111, 49).addBox(22.0F, -37.0F, 94.0F, 2.0F, 23.0F, 18.0F, new CubeDeformation(0.0F))
				.texOffs(8, 49).addBox(-22.0F, -27.0F, 94.0F, 44.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(8, 49).addBox(-22.0F, -17.0F, 94.0F, 44.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(111, 49).addBox(-24.0F, -37.0F, 94.0F, 2.0F, 23.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = Parts.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 71).addBox(-5.0F, -2.0F, -45.0F, 10.0F, 14.0F, 47.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, -26.7594F, 108.4627F, 0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r2 = Parts.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(260, 0).addBox(-5.0F, -20.75F, -1.0F, 25.0F, 21.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(260, 0).addBox(49.0F, -20.75F, -1.0F, 25.0F, 21.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-35.0F, -15.0F, 48.0F, 0.2007F, 0.0F, 0.0F));

		PartDefinition cube_r3 = Parts.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(260, 0).addBox(-5.0F, -20.75F, -1.0F, 25.0F, 21.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(260, 0).addBox(49.0F, -20.75F, -1.0F, 25.0F, 21.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-35.0F, -15.0F, 33.0F, 0.2007F, 0.0F, 0.0F));

		PartDefinition octagon_r1 = Parts.addOrReplaceChild("octagon_r1", CubeListBuilder.create().texOffs(219, 26).addBox(-10.0F, -6.068F, 0.0F, 12.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(19.95F, -1.268F, 45.5F, 0.0F, 1.5708F, 0.0F));

		PartDefinition octagon_r2 = Parts.addOrReplaceChild("octagon_r2", CubeListBuilder.create().texOffs(157, 33).addBox(-1.0F, -1.0F, -17.0F, 2.0F, 2.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(19.95F, -1.536F, 49.5F, 0.0F, 1.5708F, 0.3927F));

		PartDefinition octagon_r3 = Parts.addOrReplaceChild("octagon_r3", CubeListBuilder.create().texOffs(157, 33).addBox(-1.0F, -1.0F, -17.0F, 2.0F, 2.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-19.95F, -1.536F, 49.5F, 0.0F, -1.5708F, -0.3927F));

		PartDefinition octagon_r4 = Parts.addOrReplaceChild("octagon_r4", CubeListBuilder.create().texOffs(219, 26).addBox(-6.0F, -5.868F, 0.0F, 12.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-19.95F, -1.468F, 49.5F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r4 = Parts.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(133, 18).addBox(-1.25F, -10.0F, -34.5F, 0.0F, 10.0F, 35.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, -21.75F, 21.5F, 0.0F, 0.0F, -0.3054F));

		PartDefinition cube_r5 = Parts.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(133, 18).addBox(1.25F, -10.0F, -34.5F, 0.0F, 10.0F, 35.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, -21.75F, 21.5F, 0.0F, 0.0F, 0.3054F));

		PartDefinition cube_r6 = Parts.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(157, 26).addBox(-2.75F, 15.625F, 24.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.002F))
				.texOffs(157, 34).addBox(-1.75F, 8.875F, 27.75F, 1.0F, 8.0F, 1.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(1.25F, -26.7711F, -34.9362F, -0.0873F, 0.0F, 0.0F));

		PartDefinition Seat_r1 = Parts.addOrReplaceChild("Seat_r1", CubeListBuilder.create().texOffs(171, 26).addBox(-7.0F, -1.25F, -1.0F, 14.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -21.6F, -11.1F, 0.6545F, 0.0F, 0.0F));

		PartDefinition bone3 = Parts.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(188, 59).addBox(-5.0F, -7.1F, -12.1F, 10.0F, 2.0F, 15.0F, new CubeDeformation(0.0F))
				.texOffs(219, 42).addBox(-8.0F, -21.1F, 2.9F, 16.0F, 16.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(203, 26).addBox(-3.0F, -29.1F, 1.9F, 6.0F, 30.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(203, 26).addBox(-3.0F, -29.1F, 18.9F, 6.0F, 24.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(219, 42).addBox(-8.0F, -21.1F, 19.9F, 16.0F, 16.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(188, 59).addBox(-5.0F, -7.1F, 4.9F, 10.0F, 2.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 512, 512);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Parts.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(PlaneEntity p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {}
}