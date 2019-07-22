package com.sotti.watch.movies.data

import com.sotti.watch.movies.data.MoviesDataFakes.fightClubAM
import com.sotti.watch.movies.data.MoviesDataFakes.fightClubDM
import com.sotti.watch.movies.data.MoviesDataFakes.theMatrixAM
import com.sotti.watch.movies.data.MoviesDataFakes.theMatrixDM
import com.sotti.watch.movies.data.datasources.toDM
import org.junit.Assert
import org.junit.Test

internal class MoviesDataMapperTests {

    @Test
    fun movieOverView_toDM() {
        Assert.assertEquals(theMatrixAM.toDM(), theMatrixDM)

    }

    @Test
    fun movieOverViewSet_toDM() {
        Assert.assertEquals(setOf(theMatrixAM, fightClubAM).toDM(), setOf(theMatrixDM, fightClubDM))
    }

}