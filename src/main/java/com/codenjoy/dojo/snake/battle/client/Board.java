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


import com.codenjoy.dojo.client.AbstractBoard;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;
import com.codenjoy.dojo.snake.battle.model.Elements;

import java.util.List;

import static com.codenjoy.dojo.snake.battle.model.Elements.*;

/**
 * Класс, обрабатывающий строковое представление доски.
 * Содержит ряд унаследованных методов {@see AbstractBoard},
 * но ты можешь добавить сюда любые свои методы на их основе.
 */
public class Board extends AbstractBoard<Elements> {

    @Override
    public Elements valueOf(char ch) {
        return Elements.valueOf(ch);
    }

    public boolean isBarrierAt(int x, int y) {
        return isAt(x, y, WALL, STONE, START_FLOOR, ENEMY_HEAD_SLEEP, ENEMY_TAIL_INACTIVE,
                TAIL_INACTIVE,ENEMY_HEAD_DOWN,ENEMY_HEAD_LEFT,ENEMY_HEAD_RIGHT,ENEMY_HEAD_UP,ENEMY_HEAD_DEAD,
                ENEMY_HEAD_EVIL,ENEMY_HEAD_FLY,ENEMY_HEAD_SLEEP,ENEMY_TAIL_END_DOWN,ENEMY_TAIL_END_LEFT,
                ENEMY_TAIL_END_UP,ENEMY_TAIL_END_RIGHT,ENEMY_TAIL_INACTIVE,ENEMY_BODY_HORIZONTAL,ENEMY_BODY_VERTICAL,
                ENEMY_BODY_LEFT_DOWN,ENEMY_BODY_LEFT_UP,ENEMY_BODY_RIGHT_DOWN,ENEMY_BODY_RIGHT_UP,HEAD_DOWN,HEAD_LEFT,
                HEAD_RIGHT,HEAD_UP,HEAD_DEAD,HEAD_EVIL,HEAD_FLY,HEAD_SLEEP,TAIL_END_DOWN,TAIL_END_LEFT,TAIL_END_UP,
                TAIL_END_RIGHT,TAIL_INACTIVE,BODY_HORIZONTAL,BODY_VERTICAL,BODY_LEFT_DOWN,BODY_LEFT_UP,BODY_RIGHT_DOWN,
                BODY_RIGHT_UP);
    }

    public boolean isBarrierAt(PointImpl point) {
        return isAt(point.getX(), point.getY(), WALL, STONE, START_FLOOR, ENEMY_HEAD_SLEEP, ENEMY_TAIL_INACTIVE,
                TAIL_INACTIVE,ENEMY_HEAD_DOWN,ENEMY_HEAD_LEFT,ENEMY_HEAD_RIGHT,ENEMY_HEAD_UP,ENEMY_HEAD_DEAD,
                ENEMY_HEAD_EVIL,ENEMY_HEAD_FLY,ENEMY_HEAD_SLEEP,ENEMY_TAIL_END_DOWN,ENEMY_TAIL_END_LEFT,
                ENEMY_TAIL_END_UP,ENEMY_TAIL_END_RIGHT,ENEMY_TAIL_INACTIVE,ENEMY_BODY_HORIZONTAL,ENEMY_BODY_VERTICAL,
                ENEMY_BODY_LEFT_DOWN,ENEMY_BODY_LEFT_UP,ENEMY_BODY_RIGHT_DOWN,ENEMY_BODY_RIGHT_UP,HEAD_DOWN,HEAD_LEFT,
                HEAD_RIGHT,HEAD_UP,HEAD_DEAD,HEAD_EVIL,HEAD_FLY,HEAD_SLEEP,TAIL_END_DOWN,TAIL_END_LEFT,TAIL_END_UP,
                TAIL_END_RIGHT,TAIL_INACTIVE,BODY_HORIZONTAL,BODY_VERTICAL,BODY_LEFT_DOWN,BODY_LEFT_UP,BODY_RIGHT_DOWN,
                BODY_RIGHT_UP);
    }

    public Point getMe() {
        return getMyHead().get(0);
    }

    public boolean isGameOver() {
        return getMyHead().isEmpty();
    }

    private List<Point> getMyHead() {
        return get(HEAD_DOWN, HEAD_LEFT, HEAD_RIGHT, HEAD_UP, HEAD_SLEEP, HEAD_EVIL, HEAD_FLY);
    }

    public boolean isStoneAt(int x, int y) {
        return isAt(x, y, STONE);
    }
}