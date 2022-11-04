package android.guide.everydayanimal.network

data class Animal(
    val id: Int,
    val name: String,
    val animal_type: String, // [Bird, Reptile,  Mammal]
    val active_time: String, //(Diurnal = day , Nocturnal = night)
    val length_min: String,
    val length_max: String,
    val weight_min: String,
    val weight_max: String,
    val lifespan: String,
    val habitat: String, // بيئة
    val diet: String, // تغذية
    val geo_range: String, // موطن
    val image_link: String
)



