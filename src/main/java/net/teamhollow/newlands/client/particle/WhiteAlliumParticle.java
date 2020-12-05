package net.teamhollow.newlands.client.particle;

import java.util.Random;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.AscendingParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(EnvType.CLIENT)
public class WhiteAlliumParticle extends AscendingParticle {
    protected WhiteAlliumParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, float scaleMultiplier, SpriteProvider spriteProvider) {
        super(world, x, y, z, 0.1F, -0.1F, 0.1F, velocityX, velocityY, velocityZ, scaleMultiplier, spriteProvider, 0.0F, 20, -5.0E-4D, false);
        this.colorAlpha = 1.0F;
        this.colorRed = 1.0F;
        this.colorGreen = 1.0F;
        this.colorBlue = 1.0F;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            Random random = clientWorld.random;
            double velX = (-0.4D + (random.nextFloat() / 2)) * 0.5D;
            double velY = (-1.0D + (random.nextFloat() / 2)) * 0.5D;
            double velZ = (-0.4D + (random.nextFloat() / 2)) * 0.5D;
            return new WhiteAlliumParticle(clientWorld, x, y, z, velX, velY, velZ, 1.0F + (random.nextFloat() / 3), this.spriteProvider);
        }
    }
}
