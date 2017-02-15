package com.codenjoy.dojo.snake.battle.client;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2016 Codenjoy
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import com.codenjoy.dojo.client.Direction;
import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.client.WebSocketRunner.Host;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;
import com.codenjoy.dojo.services.RandomDice;
import com.codenjoy.dojo.services.algs.DeikstraFindWay;
import com.codenjoy.dojo.snake.battle.model.Elements;
import com.sun.xml.internal.bind.v2.TODO;

import java.util.LinkedList;
import java.util.List;

/**
 * User: your name
 * Это твой алгоритм AI для игры. Реализуй его на свое усмотрение.
 * Обрати внимание на {@see YourSolverTest} - там приготовлен тестовый
 * фреймворк для тебя.
 */
public class YourSolver implements Solver<Board> {

    private static final String USER_NAME = "a.petrovskii@gmail.com"; // TODO вписать свой ник (с которым регистрировался)

    DeikstraFindWay way; // TODO можно воспользоваться нахождением кратчайшего пути при помощи getShortestWay()
    // или использовать любой другой свой способ.

    private Dice dice;
    private Board board;
    private Point currentGoal;
    private LinkedList<NodeAstar> currentPathApple;

    YourSolver(Dice dice) {
        this.dice = dice;
       // way = new DeikstraFindWay();
    }

    // TODO Необходимо изменить данный метод. Он должен возвращать осмысленное направление для дальнейшего движения змейки.
    @Override
    public String get(Board board) {
        this.board = board;
        if (board.isGameOver()) return "";

        // return Direction.RIGHT.toString();
        // return Direction.random().toString();
        return aStarSearch();
    }

    public static void main(String[] args) {
        start(USER_NAME, Host.REMOTE);
    }

    static void start(String name, Host server) {
        try {
            WebSocketRunner.run(server, name,
                    new YourSolver(new RandomDice()),
                    new Board());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String aStarSearch(){
        //TODO: сформировать список целей для змеи
        //TODO: отсортировать список по самой ближней цели к голове
        PointImpl gold;
        PointImpl start = new PointImpl(board.getMe());// Hачальная точка
        Search Sol = new Search(board);
        List<Point> ListApples = board.get(Elements.APPLE);
        List<Point> ListGolds = board.get(Elements.GOLD);
        if (!ListGolds.isEmpty()){
            currentGoal = ListGolds.get(0);
            gold = new PointImpl(currentGoal);//Конечная точка
            currentPathApple = Sol.aStarSearch(start,gold);
        }else {
            if (!ListApples.contains(currentGoal)){
                currentGoal = ListApples.get(0);
                gold = new PointImpl(currentGoal);//Конечная точка
                currentPathApple = Sol.aStarSearch(start,gold);
            }else{
                gold = new PointImpl(currentGoal);//Конечная точка
            }
        }

        System.out.println(gold);

        if (currentPathApple != null){
            return getDirection(start,  currentPathApple.pop().tile).toString();
        }



        return "";
    }

    public Direction getDirection(Point head, Point apple) {
        int xApple = apple.getX();
        int yApple = apple.getY();
        int x = head.getX();
        int y = head.getY();
        int dx = x - xApple;
        int dy = y - yApple;

        LinkedList directions = getDirections(dx, dy);
        Direction direction;

        if(directions.size() == 1){
            direction = (Direction)directions.getFirst();
        }
        else{
            direction = (Direction)directions.getLast();
        }

        for (int i = 0; i < 4; i++) {
            Point point = direction.change(head);
            if(board.isBarrierAt(point.getX(),point.getY())){
                direction = direction.clockwise();
            }
        }

        return direction;
    }

    public static LinkedList getDirections(int dx, int dy) {
        LinkedList result = new LinkedList();
        if (dx < 0) {
            result.add(Direction.RIGHT);
        }
        if (dx > 0) {
            result.add(Direction.LEFT);
        }
        if (dy > 0) {
            result.add(Direction.UP);
        }
        if (dy < 0) {
            result.add(Direction.DOWN);
        }
        return result;
    }


}
