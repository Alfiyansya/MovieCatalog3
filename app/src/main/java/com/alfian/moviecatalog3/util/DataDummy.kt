package com.alfian.moviecatalog3.util

import com.alfian.moviecatalog3.R
import com.alfian.moviecatalog3.data.source.local.entity.MovieEntity
import com.alfian.moviecatalog3.data.source.local.entity.TvShowEntity


object DataDummy {
    const val MOVIE = "Movie"
    const val TVSHOW = "Tv Show"
    const val IMAGE_ENDPOINT = "https://image.tmdb.org/t/p/w500/"

    fun generateDummyMovies(): List<MovieEntity> {
        val image0: Int = R.drawable.poster_aquaman
        val image1: Int = R.drawable.poster_bohemian
        val image2: Int = R.drawable.poster_ralph
        val image3: Int = R.drawable.poster_serenity
        val image4: Int = R.drawable.poster_alita
        val image5: Int = R.drawable.poster_infinity_war
        val image6: Int = R.drawable.poster_glass
        val image7: Int = R.drawable.poster_how_to_train
        val image8: Int = R.drawable.poster_spiderman
        val image9: Int = R.drawable.poster_robin_hood
        val movies = ArrayList<MovieEntity>()
        movies.add(
            MovieEntity(
                "0",
                "Aquaman",
                "Action,Adventure,Fantasy",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                image0.toString(),
                image0.toString(),
                6.9,
                false
            )
        )
        movies.add(
            MovieEntity(
                "1",
                "Bohemian Rhapsody",
                "Music,Drama,History",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                image1.toString(),
                image1.toString(),
                8.0,
                false
            )
        )
        movies.add(
            MovieEntity(
                "2",
                "Ralph Breaks the Internet",
                "Family,Animation,Comedy,Adventure",
                "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope's video game, Sugar Rush. In way over their heads, Ralph and Vanellope rely on the citizens of the internet — the netizens — to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.",
                image2.toString(),
                image2.toString(),
                7.2,
                false
            )
        )
        movies.add(
            MovieEntity(
                "3",
                "Serenity",
                "Thriller,Mystery,Drama",
                "The quiet life of Baker Dill, a fishing boat captain who lives on the isolated Plymouth Island, where he spends his days obsessed with capturing an elusive tuna while fighting his personal demons, is interrupted when someone from his past comes to him searching for help.",
                image3.toString(),
                image3.toString(),
                5.4,
                false
            )
        )
        movies.add(
            MovieEntity(
                "4",
                "Alita",
                "Adventure,Action,fiction",
                "When Alita awakens with no memory of who she is in an unknown future world, she is captured by Ido, a compassionate doctor who realizes that somewhere within this abandoned cyborg shell is the heart and soul of an extraordinary young woman. then.",
                image4.toString(),
                image4.toString(),
                7.2,
                false
            )
        )
        movies.add(
            MovieEntity(
                "5",
                "Infinity War",
                "Adventure,Action,fiction",
                "As the Avengers and their allies continue to protect the world from threats too great for a single hero to handle, a new danger has emerged from the cosmic shadow: Thanos. An intergalactic despot of blasphemy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict a twisted will on all reality. Everything the Avengers have fought for has evolved to this point - the fate of Earth and its very existence has never been more certain.",
                image5.toString(),
                image5.toString(),
                8.3,
                false
            )
        )

        movies.add(
            MovieEntity(
                "6",
                "Glass",
                "Thriller,Drama,Fiction",
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                image6.toString(),
                image6.toString(),
                6.7,
                false
            )
        )
        movies.add(
            MovieEntity(
                "7",
                "How To Train your Dragon",
                "Animation,Family,Adventure",
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless' discovery of an untested and elusive pair drives the Night Fury away. As danger escalates at home and Hiccup's reign as village chief is put to the test, both dragon and rider must make the impossible decision to save their kind.",
                image7.toString(),
                image7.toString(),
                7.8,
                false
            )
        )
        movies.add(
            MovieEntity(
                "8",
                "Spider-Man : Into Spider-Verse",
                "Action,Adventure,Animation,Fiction,Comedy",
                "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                image8.toString(),
                image8.toString(),
                8.4,
                false
            )
        )
        movies.add(
            MovieEntity(
                "9",
                "Robin Hood",
                "Adventure,Action,Thriller",
                "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.",
                image9.toString(),
                image9.toString(),
                5.9,
                false
            )
        )
        return movies
    }

    fun generateDummyTvShows(): List<TvShowEntity> {
//      key 1 = TvShow

        val image0: Int = R.drawable.poster_flash
        val image1: Int = R.drawable.poster_the_umbrella
        val image2: Int = R.drawable.poster_ncis
        val image3: Int = R.drawable.poster_arrow
        val image4: Int = R.drawable.poster_fairytail
        val image5: Int = R.drawable.poster_supergirl
        val image6: Int = R.drawable.poster_gotham
        val image7: Int = R.drawable.poster_supernatural
        val image8: Int = R.drawable.poster_hanna
        val image9: Int = R.drawable.poster_shameless
        val tvShow = ArrayList<TvShowEntity>()
        tvShow.add(
            TvShowEntity(
                "0",
                "The Flash",
                "Drama, Sci-Fi & Fantasy",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                image0.toString(),
                image0.toString(),
                7.8,
                false
            )
        )
        tvShow.add(
            TvShowEntity(
                "1",
                "The Umbrella Academy",
                "Aksi & Petualangan",
                "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                image1.toString(),
                image1.toString(),
                8.7,
                false

            )
        )
        tvShow.add(
            TvShowEntity(
                "2",
                "NCIS",
                "Crime, Action & Adventure",
                "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
                image2.toString(),
                image2.toString(),
                7.4,
                false
            )
        )
        tvShow.add(
            TvShowEntity(
                "3",
                "Arrow",
                "Crime, Drama, Mystery, Action & Adventure",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                image3.toString(),
                image3.toString(),
                6.7,
                false

            )
        )
        tvShow.add(
            TvShowEntity(
                "4",
                "Fairy tail",
                "Action & Adventure, Comedy, Fantasy",
                "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",
                image4.toString(),
                image4.toString(),
                7.8,
                false
            )
        )

        tvShow.add(
            TvShowEntity(
                "5",
                "SuperGirl",
                "Drama, Sci-Fi & Fantasy",
                "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
                image5.toString(),
                image5.toString(),
                7.3,
                false
            )
        )

        tvShow.add(
            TvShowEntity(
                "6",
                "Gotham",
                "Drama, Crime, Sci-Fi & Fantasy",
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                image6.toString(),
                image6.toString(),
                7.6,
                false
            )
        )
        tvShow.add(
            TvShowEntity(
                "7",
                "Supernatural",
                "Drama, Mystery",
                "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way.",
                image7.toString(),
                image7.toString(),
                8.2,
                false
            )
        )
        tvShow.add(
            TvShowEntity(
                "8",
                "Hanna",
                "Adventure,Action,Drama",
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                image8.toString(),
                image8.toString(),
                7.5,
                false
            )
        )
        tvShow.add(
            TvShowEntity(
                "9",
                "Shameless",
                "Drama,Comedy",
                "Chicagoan Frank Gallagher is the proud single dad of six smart, industrious, independent kids, who without him would be... perhaps better off. When Frank's not at the bar spending what little money they have, he's passed out on the floor. But the kids have found ways to grow up in spite of him. They may not be like any family you know, but they make no apologies for being exactly who they are.",
                image9.toString(),
                image9.toString(),
                8.0,
                false

            )
        )
        return tvShow
    }

}
