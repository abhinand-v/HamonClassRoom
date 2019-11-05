package tech.codewithabhi.hamonclassroom.util.bindingUtils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule


@GlideModule
class CustomeGlideModule : AppGlideModule()

@BindingAdapter("LoadSubjectIcons")
fun loadSubjectIcons(imageView: ImageView, subject: String) {

    val drawable = when (subject) {
        "History" -> imageView.context.getDrawable(tech.codewithabhi.hamonclassroom.R.drawable.ic_history)
        "Physics" -> imageView.context.getDrawable(tech.codewithabhi.hamonclassroom.R.drawable.ic_physics)
        "Chemistry" -> imageView.context.getDrawable(tech.codewithabhi.hamonclassroom.R.drawable.ic_chemistry)
        "Social Science" -> imageView.context.getDrawable(tech.codewithabhi.hamonclassroom.R.drawable.ic_socailscience)
        "Biology" -> imageView.context.getDrawable(tech.codewithabhi.hamonclassroom.R.drawable.ic_biology)
        "Mathematics" -> imageView.context.getDrawable(tech.codewithabhi.hamonclassroom.R.drawable.ic_maths)
        else -> imageView.context.getDrawable(tech.codewithabhi.hamonclassroom.R.drawable.ic_subject_default)
    }

    imageView.setImageDrawable(drawable)
}

@BindingAdapter("LoadUserImages")
fun loadUserImages(imageView: ImageView, url: String) {
    GlideApp.with(imageView.context)
        .load("https://randomuser.me/api/portraits/med/men/${url}.jpg")
        .into(imageView)
}