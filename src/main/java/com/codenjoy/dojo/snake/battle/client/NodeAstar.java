package com.codenjoy.dojo.snake.battle.client;

import com.codenjoy.dojo.services.PointImpl;

/**
 * Created by ALEXANDER on 01.04.2016.
 */
public class NodeAstar {
    public PointImpl tile;
    public NodeAstar parent;
    public double fCost, hCost, gCost;

    public NodeAstar(PointImpl tile, NodeAstar parent, double hCost, double gCost) {
        this.tile = tile;
        this.parent = parent;
        this.hCost = hCost;
        this.gCost = gCost;
        this.fCost = this.gCost + this.hCost;
    }

    public NodeAstar(PointImpl tile, double hCost, double gCost) {
        this.tile = tile;
        this.parent = null;
        this.hCost = hCost;
        this.gCost = gCost;
        this.fCost = this.gCost + this.hCost;
    }
}
