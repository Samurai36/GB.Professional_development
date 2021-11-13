package viktor.khlebnikov.gb.history.description

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import coil.request.LoadRequest
import coil.transform.CircleCropTransformation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import viktor.khlebnikov.gb.history.R
import viktor.khlebnikov.gb.history.databinding.ActivityDescriptionBinding
import viktor.khlebnikov.gb.utils.isOnline
import viktor.khlebnikov.gb.utils.ui.AlertDialogFragment

class DescriptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDescriptionBinding
    private val imageLoader by lazy {
        ImageLoader(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setActionbarHomeButtonAsUp()
        binding.root.setOnRefreshListener {
            startLoadingOrShowError()
        }
        setData()
    }

    override fun onDestroy() {
        imageLoader.shutdown()
        super.onDestroy()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setData() {
        val bundle = intent.extras
        binding.descriptionHeader.text = bundle?.getString(WORD_EXTRA)
        binding.descriptionTextview.text = bundle?.getString(DESCRIPTION_EXTRA)
        val imageUrl = bundle?.getString(IMAGE_URL_EXTRA)
        if (imageUrl.isNullOrBlank()) {
            stopRefreshAnimationIfNeeded()
        } else {
            // usePicassoToLoadPhoto(binding.descriptionImageview, imageUrl)
            // useCoilToLoadPhoto(binding.descriptionImageview, imageUrl)
            useGlideToLoadPhoto(binding.descriptionImageview, imageUrl)
        }
    }

    private fun useCoilToLoadPhoto(imageView: ImageView, imageUrl: String) {
        val request = LoadRequest.Builder(this)
            .data("https:$imageUrl")
            .target(
                onStart = {},
                onSuccess = { result ->
                    imageView.setImageDrawable(result)
                    stopRefreshAnimationIfNeeded()
                },
                onError = {
                    stopRefreshAnimationIfNeeded()
                    imageView.setImageResource(R.drawable.ic_load_error_vector)
                }
            )
            .placeholder(R.drawable.ic_no_photo_vector)
            .transformations(
                CircleCropTransformation(),
            )
            .build()

        imageLoader.execute(request)
    }

    private fun useGlideToLoadPhoto(imageview: ImageView, imageUrl: String) {
        Glide.with(imageview)
            .load("https:$imageUrl")
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    stopRefreshAnimationIfNeeded()
                    imageview.setImageResource(R.drawable.ic_load_error_vector)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    stopRefreshAnimationIfNeeded()
                    return false
                }
            }).apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_no_photo_vector)
                    .centerCrop()
            ).into(imageview)
    }

    private fun usePicassoToLoadPhoto(imageview: ImageView, imageUrl: String) {
        Picasso.get().load("https:$imageUrl")
            .placeholder(R.drawable.ic_no_photo_vector).fit().centerCrop()
            .into(imageview, object : Callback {
                override fun onSuccess() {
                    stopRefreshAnimationIfNeeded()
                }

                override fun onError(e: Exception?) {
                    stopRefreshAnimationIfNeeded()
                    imageview.setImageResource(R.drawable.ic_load_error_vector)
                }
            })
    }

    private fun stopRefreshAnimationIfNeeded() {
        if (binding.descriptionScreenSwipeRefreshLayout.isRefreshing) {
            binding.descriptionScreenSwipeRefreshLayout.isRefreshing = false
        }
    }

    private fun startLoadingOrShowError() {
        if (isOnline(applicationContext)) {
            setData()
        } else {
            AlertDialogFragment.newInstance(
                getString(R.string.dialog_title_device_is_offline),
                getString(R.string.dialog_message_device_is_offline)
            ).show(
                supportFragmentManager,
                DIALOG_FRAGMENT_TAG
            )
        }
    }

    private fun setActionbarHomeButtonAsUp() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {

        private const val DIALOG_FRAGMENT_TAG = "8c7dff51-9769-4f6d-bbee-a3896085e76e"

        private const val WORD_EXTRA = "f76a288a-5dcc-43f1-ba89-7fe1d53f63b0"
        private const val DESCRIPTION_EXTRA = "0eeb92aa-520b-4fd1-bb4b-027fbf963d9a"
        private const val IMAGE_URL_EXTRA = "6e4b154d-e01f-4953-a404-639fb3bf7281"

        fun getIntent(
            context: Context,
            word: String,
            description: String,
            image_url: String?
        ): Intent = Intent(context, DescriptionActivity::class.java).apply {
            putExtra(WORD_EXTRA, word)
            putExtra(DESCRIPTION_EXTRA, description)
            putExtra(IMAGE_URL_EXTRA, image_url)
        }
    }

}