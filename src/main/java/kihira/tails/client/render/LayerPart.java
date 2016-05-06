package kihira.tails.client.render;

import kihira.tails.client.PartRegistry;
import kihira.tails.common.PartInfo;
import kihira.tails.common.PartsData;
import kihira.tails.common.Tails;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.UUID;

@SideOnly(Side.CLIENT)
public class LayerPart implements LayerRenderer<AbstractClientPlayer> {

    private final ModelRenderer modelRenderer;
    private final PartsData.PartType partType;

    public LayerPart(ModelRenderer modelRenderer, PartsData.PartType partType) {
        this.modelRenderer = modelRenderer;
        this.partType = partType;
    }

    @Override
    public void doRenderLayer(AbstractClientPlayer entity, float p_177141_2_, float p_177141_3_, float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale) {
        UUID uuid = EntityPlayer.getUUID(entity.getGameProfile());
        if (Tails.proxy.hasPartsData(uuid)) {
            PartsData partsData = Tails.proxy.getPartsData(uuid);
            if (partsData.hasPartInfo(partType) && partsData.getPartInfo(partType).hasPart) {

                GlStateManager.pushMatrix();
                if (partType == PartsData.PartType.EARS && entity.isSneaking()) GlStateManager.translate(0f, 0.2F, 0f);
                modelRenderer.postRender(0.0625F);

                PartInfo tailInfo = partsData.getPartInfo(partType);
                PartRegistry.getRenderPart(tailInfo.partType, tailInfo.typeid).render(entity, tailInfo, 0, 0, 0, partialTicks);
                GlStateManager.popMatrix();
            }
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
