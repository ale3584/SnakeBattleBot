package com.codenjoy.dojo.snake.battle.client;

import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Search
{
    private LinkedList<NodeAstar> closed;
    private LinkedList<NodeAstar> solution;
    private Board env;
    private boolean canEatStones;

    private NodeAstar[][] nodes;

    public Search(Board env ) {
        this.closed = new LinkedList<NodeAstar>();
        this.solution = new LinkedList<NodeAstar>();
        this.env = env;

        nodes = new NodeAstar[env.size()][env.size()];
        for (int x=0;x<env.size();x++) {
            for (int y=0;y<env.size();y++) {
                nodes[x][y] = new NodeAstar(new PointImpl(x,y),0,0);
            }
        }
    }

    public LinkedList<NodeAstar> aStarSearch(PointImpl start, PointImpl goal){

        LinkedList<NodeAstar> openlist = new LinkedList<NodeAstar>();
        LinkedList<NodeAstar> closedlist = new LinkedList<NodeAstar>();
        NodeAstar current = new NodeAstar(start,null,0,getDistance(start,goal));
        openlist.add(current);

        while (!openlist.isEmpty()){
            Collections.sort(openlist,nodeSorter);
            current = openlist.getFirst();
            if(current.tile.equals(goal)){
            LinkedList<NodeAstar> path = new LinkedList<NodeAstar>();
                while (current.parent != null){
                    path.push(current);
                    current = current.parent;
                }
                openlist.clear();
                closedlist.clear();
                return path;
            }

            for (NodeAstar n:openlist) {
                if(n.fCost < current.fCost){current = n;}

            }
            openlist.remove(current);
            closedlist.push(current);

            for (int x = -1; x < 2; x++) {
                for (int y = -1; y < 2 ; y++) {
                    if ((x == 0) && (y == 0)) {
                        continue;
                    }

                    // if we're not allowing diaganol movement then only
                    if ((x != 0) && (y != 0)) {
                        continue;
                    }
                    // one of x or y can be set




                    // determine the location of the neighbour and evaluate it

                    int xp = x + current.tile.getX();
                    int yp = y + current.tile.getY();

                    if(xp == 0 || yp == 0){continue;}

                    PointImpl tile = new PointImpl(xp,yp);

                    if(tile.isOutOf(env.size())) continue;

                    if(!goal.equals(tile)){
                        if (env.isBarrierAt(tile)) continue;
                    }

                    double gMath = Math.sqrt(2);

                    if((xp - current.tile.getX() == 0 || yp - current.tile.getY() == 0)){
                        gMath = 1;
                    }
                    double gCost = current.gCost + gMath;

                    NodeAstar neighbour = nodes[xp][yp];

                    // if the new cost we've determined for this node is lower than
                    // it has been previously makes sure the node hasn't been discarded. We've
                    // determined that there might have been a better path to get to
                    // this node so it needs to be re-evaluated
                    if (gCost < neighbour.gCost) {
                        if (vecInList(openlist,neighbour.tile)) {
                            openlist.remove(neighbour);
                        }
                        if (vecInList(closedlist,neighbour.tile)) {
                            closedlist.remove(neighbour);
                        }
                    }

                    // if the node hasn't already been processed and discarded then
                    // reset it's cost to our current cost and add it as a next possible
                    // step (i.e. to the open list)
                    if (!vecInList(openlist,neighbour.tile) && !vecInList(closedlist,neighbour.tile)) {
                        neighbour.gCost = gCost;
                        neighbour.hCost = getDistance(neighbour.tile,goal);
                        neighbour.fCost = neighbour.gCost + neighbour.hCost;
                        neighbour.parent = current;
                        openlist.push(neighbour);
                    }
                }
            }
        }
        closedlist.clear();
        openlist.clear();
        return null;
    }

    private double getDistance(Point start, Point goal){
        double dx = start.getX() - goal.getX();
        double dy = start.getY() - goal.getY();

        return Math.sqrt(dx*dx+dy*dy);
    }

    private Comparator<NodeAstar> nodeSorter = new Comparator<NodeAstar>() {

        @Override
        public int compare(NodeAstar o1, NodeAstar o2) {
            if(o2.fCost < o1.fCost) return 1;
            if(o2.fCost > o1.fCost) return -1;
            return 0;
        }
    };

    private boolean vecInList(LinkedList<NodeAstar> node, PointImpl tile){
        for (NodeAstar n:node) {
            if (n.tile.equals(tile)) return true;
        }
        return false;
    }

    public void setCanEatStones(boolean canEatStones) {
        this.canEatStones = canEatStones;
    }

    public boolean isCanEatStones() {
        return canEatStones;
    }
}
