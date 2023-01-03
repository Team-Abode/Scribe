package net.teamabode.scribe.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.teamabode.scribe.core.Scribe;
import net.teamabode.scribe.core.api.animation.AnimationParser;
import net.teamabode.scribe.core.registry.quebe.Quebe;

public class QuebeModel extends HierarchicalModel<Quebe> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Scribe.MOD_ID, "quebe"), "main");
	private static final ResourceLocation WALK = new ResourceLocation(Scribe.MOD_ID, "quebe/walk");
	private static final ResourceLocation JIG = new ResourceLocation(Scribe.MOD_ID, "quebe/jig");

	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;

	public QuebeModel(ModelPart root) {
		this.root = root.getChild("root");
		this.head = this.root.getChild("head");
		this.leftLeg = this.root.getChild("left_leg");
		this.rightLeg = this.root.getChild("right_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();

		PartDefinition root = partDefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.ZERO);
		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -9.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.0F, -6.0F, -6.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.0F, 0.0F));
		PartDefinition leftLeg = root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.5F, 20.0F, 0.0F));
		PartDefinition rightLeg = root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 20).addBox(-2.25F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.25F, 20.0F, 0.0F));

		return LayerDefinition.create(meshDefinition, 64, 64);
	}

	@Override
	public void setupAnim(Quebe entity, float f, float g, float ageInTicks, float i, float j) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		float walkSpeed = Math.min((float)entity.getDeltaMovement().lengthSqr() * 200.0F, 8.0F);
		this.animate(entity.walkingAnimationState, AnimationParser.createAnimation(WALK), ageInTicks, walkSpeed);
		this.animate(entity.jigAnimationState, AnimationParser.createAnimation(JIG), ageInTicks);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}