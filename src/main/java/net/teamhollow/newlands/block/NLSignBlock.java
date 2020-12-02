package net.teamhollow.newlands.block;

import net.minecraft.block.SignBlock;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;

public class NLSignBlock extends SignBlock implements NLSign {
    private final Identifier texture;

    public NLSignBlock(Identifier texture, Settings settings) {
        super(settings, SignType.OAK);
        this.texture = texture;
    }

    @Override
    public Identifier getTexture() {
        return texture;
    }
}
