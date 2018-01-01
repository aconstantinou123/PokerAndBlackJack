package com.musicarray.codeclan.blackjack;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by user on 1/1/18.
 */

public class ScoreTest {
    Score score;

    @Before
    public void before(){
        score = new Score();
    }

    @Test
    public void canGetComputerScore(){
        assertEquals(0, score.getComputerScore(), 0.1);
    }

    @Test
    public void canAddScoreToPlayer(){
        score.addPointPlayer();
        score.addPointPlayer();
        assertEquals(2, score.getPlayerScore(), 0.1);
    }
}
