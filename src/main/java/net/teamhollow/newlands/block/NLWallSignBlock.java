package net.teamhollow.newlands.block;

import net.minecraft.block.WallSignBlock;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;

public class NLWallSignBlock extends WallSignBlock implements NLSign {
    private final Identifier texture;

    public NLWallSignBlock(Identifier texture, Settings settings) {
        super(settings, SignType.OAK);
        this.texture = texture;
    }

    @Override
    public Identifier getTexture() {
        return texture;
    }
}
