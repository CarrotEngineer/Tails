/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014
 *
 * See LICENSE for full License
 */

package kihira.tails.client.render.helper;

import kihira.tails.api.IRenderHelper;
import kihira.tails.client.model.tail.ModelCatTail;
import kihira.tails.client.model.tail.ModelDevilTail;
import kihira.tails.client.model.tail.ModelDragonTail;
import kihira.tails.client.render.RenderPart;
import kihira.tails.common.PartInfo;
import kihira.tails.common.PartsData;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.opengl.GL11;

public class PlayerRenderHelper implements IRenderHelper {

    @Override
    public void addModelHelpers(RenderLiving renderLiving) {

    }

    @Override
    public void onPreRenderPart(EntityLivingBase entity, RenderPart tail, PartInfo info, double x, double y, double z) {
        if (info.partType == PartsData.PartType.EARS) return;
        if (info.partType == PartsData.PartType.WINGS) return;
        if (tail.modelPart instanceof ModelDragonTail) {
            GL11.glTranslatef(0F, 0.68F, 0.1F);
            GL11.glScalef(0.8F, 0.8F, 0.8F);
        }
        else if (tail.modelPart instanceof ModelCatTail || tail.modelPart instanceof ModelDevilTail) {
            GL11.glTranslatef(0F, 0.65F, 0.1F);
            GL11.glScalef(0.9F, 0.9F, 0.9F);
        }
        else {
            GL11.glTranslatef(0F, 0.65F, 0.1F);
            GL11.glScalef(0.8F, 0.8F, 0.8F);
        }
    }
}
