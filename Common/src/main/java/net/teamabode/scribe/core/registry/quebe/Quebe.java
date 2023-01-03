package net.teamabode.scribe.core.registry.quebe;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.teamabode.scribe.core.registry.quebe.goals.QuebeRandomStrollGoal;

public class Quebe extends PathfinderMob {

    private static final EntityDataAccessor<Integer> DANCE_TIME = SynchedEntityData.defineId(Quebe.class, EntityDataSerializers.INT);
    public final AnimationState walkingAnimationState = new AnimationState();
    public final AnimationState jigAnimationState = new AnimationState();

    public Quebe(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createQuebeAttributes() {
        return Mob.createMobAttributes().add(Attributes.FOLLOW_RANGE, 16.0).add(Attributes.ATTACK_KNOCKBACK).add(Attributes.MOVEMENT_SPEED, 0.2D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new QuebeRandomStrollGoal(this));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DANCE_TIME, 0);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        setDanceTime(compoundTag.getInt("DanceTime"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putInt("DanceTime", getDanceTime());
    }

    public boolean isDancing() {
        return this.entityData.get(DANCE_TIME) > 0;
    }

    public int getDanceTime() {
        return this.entityData.get(DANCE_TIME);
    }

    public void setDanceTime(int value) {
        this.entityData.set(DANCE_TIME, value);
    }

    private boolean isMovingOnLand() {
        return this.onGround && this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6;
    }

    @Override
    public void tick() {
        if (this.isDancing()) {
            this.setDanceTime(this.getDanceTime() - 1);
        }
        if (this.level.isClientSide()) {
            if (this.isMovingOnLand()) {
                walkingAnimationState.startIfStopped(this.tickCount);
            } else {
                walkingAnimationState.stop();
            }
            if (this.isDancing()) {
                jigAnimationState.startIfStopped(this.tickCount);
                if (this.tickCount % 10 == 0) {
                    var position = this.position();
                    level.addParticle(ParticleTypes.NOTE, position.x + (random.nextGaussian() * 0.4), position.y + 1, position.z + (random.nextGaussian() * 0.4), (double)this.getDanceTime() / 24.0, 0.0, 0.0);
                }
            } else {
                jigAnimationState.stop();
            }
        }
        super.tick();
    }

    @Override
    public InteractionResult interactAt(Player player, Vec3 vec3, InteractionHand interactionHand) {
        if (!this.isDancing()) {
            this.setDanceTime(84);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
