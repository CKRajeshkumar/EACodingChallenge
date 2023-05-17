package com.app.eacodingchallenge.data

import com.app.eacodingchallenge.models.Band
import com.app.eacodingchallenge.models.Root

object FestivalMockData {
    /*[{"name":"LOL-palooza","bands":[{"name":"Werewolf Weekday","recordLabel":"XS Recordings"},{"name":"Winter Primates","recordLabel":""},{"name":"Jill Black","recordLabel":"Fourth Woman Records"},{"name":"Frank Jupiter","recordLabel":"Pacific Records"}]},{"name":"Small Night In","bands":[{"name":"Green Mild Cold Capsicum","recordLabel":"Marner Sis. Recording"},{"name":"The Black Dashes","recordLabel":"Fourth Woman Records"},{"name":"Wild Antelope","recordLabel":"Marner Sis. Recording"},{"name":"Squint-281","recordLabel":"Outerscope"},{"name":"Yanke East","recordLabel":"MEDIOCRE Music"}]},{"name":"Trainerella","bands":[{"name":"Wild Antelope","recordLabel":"Still Bottom Records"},{"name":"Manish Ditch","recordLabel":"ACR"},{"name":"Adrian Venti","recordLabel":"Monocracy Records"},{"name":"YOUKRANE","recordLabel":"Anti Records"}]},{"name":"Twisted Tour","bands":[{"name":"Summon","recordLabel":"Outerscope"},{"name":"Auditones","recordLabel":"Marner Sis. Recording"},{"name":"Squint-281"}]},{"bands":[{"name":"Critter Girls","recordLabel":"ACR"},{"name":"Propeller","recordLabel":"Pacific Records"}]}]*/

    val positiveList = listOf(
        Root(viewType = 1, bands = arrayListOf(Band(name = "Winter Primates"))),
        Root(
            viewType = 2,
            name = "LOL-palooza",
            bands = arrayListOf(Band(name = "Winter Primates"))
        ),

        Root(
            viewType = 1,
            name = "Fourth Woman Record",
            bands = arrayListOf(Band(name = "Jill Black", recordLabel = "Fourth Woman Record"))
        ),
        Root(
            viewType = 2,
            name = "LOL-palooza",
            bands = arrayListOf(Band(name = "Jill Black", recordLabel = "Fourth Woman Record"))
        ),

        Root(
            viewType = 1,
            name = "Pacific Records",
            bands = arrayListOf(Band(name = "Frank Jupiter", recordLabel = "Pacific Records"))
        ),
        Root(
            viewType = 2,
            name = "LOL-palooza",
            bands = arrayListOf(Band(name = "Frank Jupiter", recordLabel = "Pacific Records"))
        ),
        Root(
            viewType = 1,
            name = "XS Recordings",
            bands = arrayListOf(Band(name = "Werewolf Weekday", recordLabel = "XS Recordings"))
        ),
        Root(
            viewType = 2,
            name = "LOL-palooza",
            bands = arrayListOf(Band(name = "Werewolf Weekday", recordLabel = "XS Recordings"))
        )
    )

    val emptyListItem = listOf<Root>()
}