package net.minecraft.entity.ai;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.profiler.Profiler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityAITasks
{
    private static final Logger logger = LogManager.getLogger();
    /** A list of EntityAITaskEntrys in EntityAITasks. */
    public List taskEntries = Lists.newArrayList();
    /** A list of EntityAITaskEntrys that are currently being executed. */
    private List executingTaskEntries = Lists.newArrayList();
    /** Instance of Profiler. */
    private final Profiler theProfiler;
    private int tickCount;
    private int tickRate = 3;
    private static final String __OBFID = "CL_00001588";

    public EntityAITasks(Profiler p_i1628_1_)
    {
        this.theProfiler = p_i1628_1_;
    }

    /**
     * Add a now AITask. Args : priority, task
     */
    public void addTask(int p_75776_1_, EntityAIBase p_75776_2_)
    {
        this.taskEntries.add(new EntityAITasks.EntityAITaskEntry(p_75776_1_, p_75776_2_));
    }

    /**
     * removes the indicated task from the entity's AI tasks.
     */
    public void removeTask(EntityAIBase p_85156_1_)
    {
        Iterator iterator = this.taskEntries.iterator();

        while (iterator.hasNext())
        {
            EntityAITasks.EntityAITaskEntry entityaitaskentry = (EntityAITasks.EntityAITaskEntry)iterator.next();
            EntityAIBase entityaibase1 = entityaitaskentry.action;

            if (entityaibase1 == p_85156_1_)
            {
                if (this.executingTaskEntries.contains(entityaitaskentry))
                {
                    entityaibase1.resetTask();
                    this.executingTaskEntries.remove(entityaitaskentry);
                }

                iterator.remove();
            }
        }
    }

    public void onUpdateTasks()
    {
        this.theProfiler.startSection("goalSetup");
        Iterator iterator;
        EntityAITasks.EntityAITaskEntry entityaitaskentry;

        if (this.tickCount++ % this.tickRate == 0)
        {
            iterator = this.taskEntries.iterator();

            while (iterator.hasNext())
            {
                entityaitaskentry = (EntityAITasks.EntityAITaskEntry)iterator.next();
                boolean flag = this.executingTaskEntries.contains(entityaitaskentry);

                if (flag)
                {
                    if (this.canUse(entityaitaskentry) && this.canContinue(entityaitaskentry))
                    {
                        continue;
                    }

                    entityaitaskentry.action.resetTask();
                    this.executingTaskEntries.remove(entityaitaskentry);
                }

                if (this.canUse(entityaitaskentry) && entityaitaskentry.action.shouldExecute())
                {
                    entityaitaskentry.action.startExecuting();
                    this.executingTaskEntries.add(entityaitaskentry);
                }
            }
        }
        else
        {
            iterator = this.executingTaskEntries.iterator();

            while (iterator.hasNext())
            {
                entityaitaskentry = (EntityAITasks.EntityAITaskEntry)iterator.next();

                if (!this.canContinue(entityaitaskentry))
                {
                    entityaitaskentry.action.resetTask();
                    iterator.remove();
                }
            }
        }

        this.theProfiler.endSection();
        this.theProfiler.startSection("goalTick");
        iterator = this.executingTaskEntries.iterator();

        while (iterator.hasNext())
        {
            entityaitaskentry = (EntityAITasks.EntityAITaskEntry)iterator.next();
            entityaitaskentry.action.updateTask();
        }

        this.theProfiler.endSection();
    }

    /**
     * Determine if a specific AI Task should continue being executed.
     */
    private boolean canContinue(EntityAITasks.EntityAITaskEntry p_75773_1_)
    {
        boolean flag = p_75773_1_.action.continueExecuting();
        return flag;
    }

    /**
     * Determine if a specific AI Task can be executed, which means that all running higher (= lower int value) priority
     * tasks are compatible with it or all lower priority tasks can be interrupted.
     */
    private boolean canUse(EntityAITasks.EntityAITaskEntry p_75775_1_)
    {
        Iterator iterator = this.taskEntries.iterator();

        while (iterator.hasNext())
        {
            EntityAITasks.EntityAITaskEntry entityaitaskentry = (EntityAITasks.EntityAITaskEntry)iterator.next();

            if (entityaitaskentry != p_75775_1_)
            {
                if (p_75775_1_.priority >= entityaitaskentry.priority)
                {
                    if (!this.areTasksCompatible(p_75775_1_, entityaitaskentry) && this.executingTaskEntries.contains(entityaitaskentry))
                    {
                        return false;
                    }
                }
                else if (!entityaitaskentry.action.isInterruptible() && this.executingTaskEntries.contains(entityaitaskentry))
                {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Returns whether two EntityAITaskEntries can be executed concurrently
     */
    private boolean areTasksCompatible(EntityAITasks.EntityAITaskEntry p_75777_1_, EntityAITasks.EntityAITaskEntry p_75777_2_)
    {
        return (p_75777_1_.action.getMutexBits() & p_75777_2_.action.getMutexBits()) == 0;
    }

    public class EntityAITaskEntry
    {
        /** The EntityAIBase object. */
        public EntityAIBase action;
        /** Priority of the EntityAIBase */
        public int priority;
        private static final String __OBFID = "CL_00001589";

        public EntityAITaskEntry(int p_i1627_2_, EntityAIBase p_i1627_3_)
        {
            this.priority = p_i1627_2_;
            this.action = p_i1627_3_;
        }
    }
}