package com.sotti.watch.movies.data

import com.sotti.watch.movies.data.MoviesDataFakes.fightClubAM
import com.sotti.watch.movies.data.MoviesDataFakes.fightClubDM
import com.sotti.watch.movies.data.MoviesDataFakes.theMatrixAM
import com.sotti.watch.movies.data.MoviesDataFakes.theMatrixDM
import com.sotti.watch.movies.data.datasources.toDM
import org.junit.Test

internal class MoviesDataMapperTests {

    @Test
    fun movieOverView_toDM() {
        theMatrixAM.toDM() == theMatrixDM

    }

    @Test
    fun movieOverViewSet_toDM() {
        setOf(theMatrixAM, fightClubAM).toDM() == setOf(theMatrixDM, fightClubDM)
    }

}