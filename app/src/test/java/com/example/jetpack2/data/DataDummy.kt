package com.example.jetpack2.data

object DataDummy {
    fun getDummyRemoteMovie(): List<FilmList> =
        arrayListOf(
            FilmList(
                590706,
                "/eLT8Cu357VOwBVTitkmlDEg32Fs.jpg",
                "/jeAQdDX9nguP6YOX6QSWKDPkbBo.jpg",
                "Jiu Jitsu",
                "From the darkness, the ultimate fighter rises.",
                "Every six years, an ancient order of jiu-jitsu fighters joins forces to " +
                        "battle a vicious race of alien invaders. But when a celebrated war hero goes " +
                        "down in defeat, the fate of the planet and mankind hangs in the balance.",
                "2020-11-20",
                5.7,
                ""),
            FilmList(1, "", "", "", "", "", "", 0.0, ""),
            FilmList(2, "", "", "", "", "", "", 0.0, ""),
            FilmList(3, "", "", "", "", "", "", 0.0, ""),
            FilmList(4, "", "", "", "", "", "", 0.0, ""),
            FilmList(5, "", "", "", "", "", "", 0.0, ""),
            FilmList(6, "", "", "", "", "", "", 0.0, ""),
            FilmList(7, "", "", "", "", "", "", 0.0, ""),
            FilmList(8, "", "", "", "", "", "", 0.0, ""),
            FilmList(9, "", "", "", "", "", "", 0.0, ""),
            FilmList(10, "", "", "", "", "", "", 0.0, ""),
            FilmList(11, "", "", "", "", "", "", 0.0, ""),
            FilmList(12, "", "", "", "", "", "", 0.0, ""),
            FilmList(13, "", "", "", "", "", "", 0.0, ""),
            FilmList(14, "", "", "", "", "", "", 0.0, ""),
            FilmList(15, "", "", "", "", "", "", 0.0, ""),
            FilmList(16, "", "", "", "", "", "", 0.0, ""),
            FilmList(17, "", "", "", "", "", "", 0.0, ""),
            FilmList(18, "", "", "", "", "", "", 0.0, ""),
            FilmList(19, "", "", "", "", "", "", 0.0, "")
        )


    fun getDummyRemoteTvShows(): List<FilmList> =
        arrayListOf(
            FilmList(
                82856,
                "/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
                "/9ijMGlJKqcslswWUzTEwScm82Gs.jpg",
                "",
                "",
                "After the fall of the Galactic Empire, lawlessness has spread throughout " +
                        "the galaxy. A lone gunfighter makes his way through the outer reaches, earning " +
                        "his keep as a bounty hunter.",
                "",
                8.5,
                "The Mandalorian"
            ),
            FilmList(0, "", "", "", "", "", "", 0.0, ""),
            FilmList(0, "", "", "", "", "", "", 0.0, ""),
            FilmList(0, "", "", "", "", "", "", 0.0, ""),
            FilmList(0, "", "", "", "", "", "", 0.0, ""),
            FilmList(0, "", "", "", "", "", "", 0.0, ""),
            FilmList(0, "", "", "", "", "", "", 0.0, ""),
            FilmList(0, "", "", "", "", "", "", 0.0, ""),
            FilmList(0, "", "", "", "", "", "", 0.0, ""),
            FilmList(0, "", "", "", "", "", "", 0.0, ""),
            FilmList(0, "", "", "", "", "", "", 0.0, ""),
            FilmList(0, "", "", "", "", "", "", 0.0, ""),
            FilmList(0, "", "", "", "", "", "", 0.0, ""),
            FilmList(0, "", "", "", "", "", "", 0.0, ""),
            FilmList(0, "", "", "", "", "", "", 0.0, ""),
            FilmList(0, "", "", "", "", "", "", 0.0, ""),
            FilmList(0, "", "", "", "", "", "", 0.0, ""),
            FilmList(0, "", "", "", "", "", "", 0.0, ""),
            FilmList(0, "", "", "", "", "", "", 0.0, ""),
            FilmList(0, "", "", "", "", "", "", 0.0, "")
        )

    fun getTvShowDetail(): TvShowDetail =
        TvShowDetail(82856,
            "/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
            "/9ijMGlJKqcslswWUzTEwScm82Gs.jpg",
            "The Mandalorian",
            "The Mandalorian",
            "2019-11-12",
            "After the fall of the Galactic Empire, lawlessness has spread throughout the " +
                    "galaxy. A lone gunfighter makes his way through the outer reaches, earning his " +
                    "keep as a bounty hunter.",
            8.5
        )
}