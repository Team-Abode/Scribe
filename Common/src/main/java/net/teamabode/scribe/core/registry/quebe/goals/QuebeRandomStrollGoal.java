package net.teamabode.scribe.core.registry.quebe.goals;

import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;
import net.teamabode.scribe.core.registry.quebe.Quebe;
import org.jetbrains.annotations.Nullable;

public class QuebeRandomStrollGoal extends RandomStrollGoal {

    private final Quebe quebe;

    public QuebeRandomStrollGoal(Quebe quebe) {
        super(quebe, 1.0D, 80);
        this.quebe = quebe;
    }

    @Override
    public boolean canUse() {
        return super.canUse() && !quebe.isDancing();
    }

    @Nullable
    @Override
    protected Vec3 getPosition() {
        return DefaultRandomPos.getPos(quebe, 20, 7);
    }

    @Override
    public boolean canContinueToUse() {
        return super.canContinueToUse() && !quebe.isDancing();
    }
}
