package tech.codewithabhi.hamonclassroom.ui.home.classroom

import com.xwray.groupie.databinding.BindableItem
import tech.codewithabhi.hamonclassroom.R
import tech.codewithabhi.hamonclassroom.data.network.models.Classroom
import tech.codewithabhi.hamonclassroom.databinding.ItemClassroomBinding

class ListItem(
    private val classroom: Classroom
) : BindableItem<ItemClassroomBinding>() {
    override fun getLayout() = R.layout.item_classroom

    override fun bind(viewBinding: ItemClassroomBinding, position: Int) {
        viewBinding.classroom = classroom
    }

}