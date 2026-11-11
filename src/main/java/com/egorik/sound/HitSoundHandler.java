package com.egorik.sound;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import com.egorik.config.EgorikConfig;

public class HitSoundHandler {
    public static void init() {
        AttackEntityCallback.EVENT.register((PlayerEntity player, World world, Hand hand, Entity entity, net.minecraft.util.hit.EntityHitResult hitResult) -> {
            if (world.isClient()) {
                // ЗВУК
                int s = EgorikConfig.hitSoundMode;
                if (s > 0) {
                    var sound = switch (s) {
                        case 1 -> SoundEvents.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT; // пузыри
                        case 2 -> SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP;       // опыт
                        case 3 -> SoundEvents.BLOCK_NOTE_BLOCK_BELL.value();      // колокольчик
                        default -> null;
                    };
                    if (sound != null) {
                        world.playSound(player, entity.getX(), entity.getY(), entity.getZ(), sound, SoundCategory.PLAYERS, 1f, 1f);
                    }
                }

                // ЧАСТИЦЫ
                int p = EgorikConfig.hitParticleMode;
                if (p > 0) {
                    double x = entity.getX(), y = entity.getY() + entity.getHeight() * 0.5, z = entity.getZ();
                    switch (p) {
                        case 1 -> {
                            for (int i = 0; i < 8; i++) {
                                world.addParticle(ParticleTypes.BUBBLE_POP,
                                    x + (world.random.nextDouble() - 0.5) * 0.5,
                                    y + (world.random.nextDouble() - 0.5) * 0.5,
                                    z + (world.random.nextDouble() - 0.5) * 0.5,
                                    0, 0.1, 0);
                            }
                        }
                        case 2 -> {
                            for (int i = 0; i < 6; i++) {
                                world.addParticle(ParticleTypes.HEART,
                                    x + (world.random.nextDouble() - 0.5) * 0.5,
                                    y + (world.random.nextDouble() - 0.5) * 0.5,
                                    z + (world.random.nextDouble() - 0.5) * 0.5,
                                    0, 0.1, 0);
                            }
                        }
                        case 3 -> {
                            for (int i = 0; i < 10; i++) {
                                world.addParticle(ParticleTypes.CRIT,
                                    x, y, z,
                                    (world.random.nextDouble() - 0.5) * 0.4,
                                    (world.random.nextDouble() - 0.5) * 0.4,
                                    (world.random.nextDouble() - 0.5) * 0.4);
                            }
                        }
                    }
                }
            }
            return ActionResult.PASS;
        });
    }
}
