package net.ldm.collar_cutting.event;

import com.mojang.logging.LogUtils;
import net.ldm.collar_cutting.config.Config;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CutCollarEvent {
    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        if (event != null && event.getEntity() != null && event.getItemStack().getItem() == Items.SHEARS &&
                event.getTarget().getType() == EntityType.WOLF && !event.getLevel().isClientSide()) {
            Wolf wolf = (Wolf) event.getTarget();
            if (!wolf.isTame()) return;
            if (Config.cut_unowned_wolves.get()) {
                untameWolf(wolf, event);
            } else {
                if (wolf.isOwnedBy(event.getEntity())) {
                    untameWolf(wolf, event);
                }
            }
        }
    }

    static void untameWolf(Wolf wolf, PlayerInteractEvent.EntityInteract event) {
        Player player = event.getEntity();
        InteractionHand hand = event.getHand();
        Vec3 pos = player.position();
        ItemStack usedItemStack = event.getItemStack();

        wolf.setTame(false);
        wolf.setInSittingPose(false);

        wolf.getLevel().playLocalSound(pos.x, pos.y, pos.z, SoundEvents.SNOW_GOLEM_SHEAR, SoundSource.PLAYERS, 1f, 1f, false);
        usedItemStack.hurtAndBreak(Config.durability.get(), player, e -> e.broadcastBreakEvent(hand));
        event.getLevel().addFreshEntity(new ItemEntity(event.getLevel(), pos.x, pos.y, pos.z, new ItemStack(Items.STRING, (int)(Math.random() * 2 + 1))));
    }
}
