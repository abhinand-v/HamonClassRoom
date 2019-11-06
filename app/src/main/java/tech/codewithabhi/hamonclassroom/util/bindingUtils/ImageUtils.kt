package tech.codewithabhi.hamonclassroom.util.bindingUtils

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import tech.codewithabhi.hamonclassroom.R


@GlideModule
class CustomGlideModule : AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        builder.apply {
            RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .signature(ObjectKey(System.currentTimeMillis().toShort()))
        }
    }
}

@BindingAdapter("LoadSubjectIcons")
fun loadSubjectIcons(imageView: ImageView, subject: String) {

    val drawable = when (subject) {
        "History" -> imageView.context.getDrawable(R.drawable.ic_history)
        "Physics" -> imageView.context.getDrawable(R.drawable.ic_physics)
        "Chemistry" -> imageView.context.getDrawable(R.drawable.ic_chemistry)
        "Social Science" -> imageView.context.getDrawable(R.drawable.ic_socailscience)
        "Biology" -> imageView.context.getDrawable(R.drawable.ic_biology)
        "Mathematics" -> imageView.context.getDrawable(R.drawable.ic_maths)
        else -> imageView.context.getDrawable(R.drawable.ic_subject_default)
    }

    imageView.setImageDrawable(drawable)
}

@BindingAdapter("LoadRoomIcons")
fun loadRoomIcons(imageView: ImageView, layout: String) {

    val drawable = when (layout) {
        "conference" -> imageView.context.getDrawable(R.drawable.ic_room_conf)
        "classroom" -> imageView.context.getDrawable(R.drawable.ic_room_clsr)
        else -> imageView.context.getDrawable(R.drawable.ic_room_clsr)
    }

    imageView.setImageDrawable(drawable)
}

@BindingAdapter("LoadUserImages")
fun loadUserImages(imageView: ImageView, url: String) {
    GlideApp
        .with(imageView.context)
        .load("https://randomuser.me/api/portraits/med/men/${url}.jpg")
        .placeholder(R.drawable.ic_sand_clock)
        .into(imageView)
}