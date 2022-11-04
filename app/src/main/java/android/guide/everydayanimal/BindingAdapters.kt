package android.guide.everydayanimal

import android.guide.everydayanimal.network.Animal
import android.guide.everydayanimal.ui.AnimalApiStatus
import android.guide.everydayanimal.ui.AnimalListAdapter
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
/**
attributes of views in  [list_view_item]
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) { // coil library
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}


/**
 * Updates the data shown in the [RecyclerView]
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Animal>?) {
    val adapter = recyclerView.adapter as AnimalListAdapter
    adapter.submitList(data)
}



/**
 * This binding adapter displays the [AmphibianApiStatus] of the network request in an image view.
 * When the request is loading, it displays a loading_animation.  If the request has an error, it
 * displays a broken image to reflect the connection error.  When the request is finished, it
 * hides the image view.
 *
 *
 * if there's an error the appropriate image will displayed
 */
@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: AnimalApiStatus?) {
    when(status) {
        AnimalApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        AnimalApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        AnimalApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        null -> TODO()
    }
}