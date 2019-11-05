package tech.codewithabhi.hamonclassroom.ui.home.subject

import com.xwray.groupie.databinding.BindableItem
import tech.codewithabhi.hamonclassroom.R
import tech.codewithabhi.hamonclassroom.data.network.models.Subject
import tech.codewithabhi.hamonclassroom.databinding.ItemSubjectBinding

class ListItem(
    private val subject: Subject
) : BindableItem<ItemSubjectBinding>() {
    override fun getLayout() = R.layout.item_subject

    override fun bind(viewBinding: ItemSubjectBinding, position: Int) {
        viewBinding.subject = subject
    }

}